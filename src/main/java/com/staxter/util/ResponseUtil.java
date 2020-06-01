package com.staxter.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.model.api.CodeDescriptionResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiConsumer;

@Slf4j
public final class ResponseUtil {

    public static BiConsumer<HttpStatus, CodeDescriptionResponseModel> errorHandler(ServletResponse response) {
        return (status, codeResponseModel) -> {
            try {
                byte[] responseToSend = getBytes(codeResponseModel);

                ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
                ((HttpServletResponse) response).setStatus(status.value());
                response.getOutputStream().write(responseToSend);
            } catch (IOException ex) {
                log.warn(ex.getMessage(), ex);
            }
        };
    }

    private static byte[] getBytes(CodeDescriptionResponseModel eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
}
