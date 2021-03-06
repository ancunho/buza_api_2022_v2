package com.buza.server.config;

import com.buza.server.filter.CaptchaFilter;
import com.buza.server.filter.JwtAuthenticationTokenFilter;
import com.buza.server.handler.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    CaptchaFilter captchaFilter;

    @Resource
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter(authenticationManagerBean());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        //config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8089", "http://127.0.0.1:9090"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setMaxAge((long) 3600);
        config.setAllowCredentials(false);
        config.addExposedHeader("token");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Content-Type");
        config.addExposedHeader("Content-Disposition");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                //??????????????????????????????
                .successHandler(jwtAuthenticationSuccessHandler)
                // ????????????????????????
                .failureHandler(loginFailureHandler)
                // ?????????????????????URI
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSeccussHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
                //token??????????????????????????????csrf?????????
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/captcha").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/file/handle/").permitAll()
                .antMatchers("/file/handle/**").permitAll()
                .antMatchers("/miniapp/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // ????????????????????????
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //                // ??????????????????????????????
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                //????????????????????????,????????????session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // ??????????????????????????????  ???????????????????????????????????????????????????
        httpSecurity.cors().configurationSource(corsConfigurationSource()).and().csrf().disable();


        //???????????????jwt???????????????
        httpSecurity
                .addFilter(authenticationTokenFilterBean())
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);


        // disable page caching
        httpSecurity.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/webjars/**"
                ,"/swagger-resources"
                ,"/swagger-resources/**"
                ,"/v2/api-docs/**"
                ,"/favicon.ico"
                ,"/static/**"
                ,"/system/file/handle"
                ,"/system/file/handle/**"
                ,"/wechat/**"
//                ,"/favicon.ico"
//                ,"/webjars/**"
        );
    }

}
