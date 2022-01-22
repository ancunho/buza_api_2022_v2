package com.buza.server.handler;

import cn.hutool.json.JSONUtil;
import com.buza.server.common.ResponseCode;
import com.buza.server.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 自定义无权访问处理类
 * @author anchunhao
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("----------> 权限不足");
        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream outputStream = response.getOutputStream();
        ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDesc());
        outputStream.write(JSONUtil.toJsonStr(serverResponse).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }

}
