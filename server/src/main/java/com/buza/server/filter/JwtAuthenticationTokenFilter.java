package com.buza.server.filter;


import com.buza.server.service.impl.UserPrincipalDetailService;
import com.buza.server.util.JwtProperties;
import com.buza.server.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {

    @Resource
    private UserPrincipalDetailService userPrincipalDetailService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private JwtProperties jwtProperties;

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        log.info("requestUrl>>>>> {}", requestUrl);
        String authToken = request.getHeader(jwtProperties.getHeader());
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        if (request.getMethod().equals("OPTIONS")) {

        }

        log.info("进入自定义过滤器, 自定义过滤器获得用户名为: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userPrincipalDetailService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}
