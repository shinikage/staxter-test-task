package com.staxter.entrypoint;

import java.io.Serializable;
import java.util.function.BiConsumer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.staxter.model.api.CodeDescriptionResponseModel;
import com.staxter.util.ResponseCode;
import com.staxter.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869927953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        BiConsumer<HttpStatus, CodeDescriptionResponseModel> handler = ResponseUtil.errorHandler(response);

        if (authException instanceof BadCredentialsException) {
            handler.accept(HttpStatus.BAD_REQUEST, new CodeDescriptionResponseModel(
                    ResponseCode.BAD_CREDENTIALS.getValue(),
                    ResponseCode.BAD_CREDENTIALS_DESCRIPTION.getValue()
            ));
            return;
        }

        handler.accept(HttpStatus.UNAUTHORIZED, new CodeDescriptionResponseModel(
                ResponseCode.UNAUTHORIZED.getValue(),
                ResponseCode.UNAUTHORIZED_DESCRIPTION.getValue()
        ));
    }
}
