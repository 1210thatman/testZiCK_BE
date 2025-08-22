package org.example.zick.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.zick.global.error.entity.ErrorResponseEntity;
import org.example.zick.global.error.exception.ErrorCode;
import org.example.zick.global.error.exception.ZickException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        try{
            filterChain.doFilter(request,response);
        } catch(ZickException e){
            sendErrorMessage(response, e.getErrorCode());
        } catch(Exception e){
            logger.error(e);
            sendErrorMessage(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendErrorMessage(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponseEntity errorResponse = ErrorResponseEntity.builder()
                .type(errorCode.getType())
                .statusCode(errorCode.getStatusCode())
                .build();
        String errorResponseJson = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(errorCode.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(errorResponseJson);
    }
}