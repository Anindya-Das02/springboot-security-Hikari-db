package das.spring.security.app.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import das.spring.security.app.models.exceptions.ErrorResponse;
import das.spring.security.app.models.exceptions.UnAuthorizedRequest;
import das.spring.security.app.util.CommonUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

@Component
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

//        log.info("checking authorization..");
//        if(!validateAuthorization(cachingRequestWrapper.getHeader("x-auth-token"))){
//            log.error("UnAuthorised Access! Exiting...");
//            handleExceptionResponse(response);
//            return;
//        }
//        log.info("authorization success");

        long startTime = System.currentTimeMillis();

        filterChain.doFilter(cachingRequestWrapper,cachingResponseWrapper);

        String responseBody = getStringValue(cachingResponseWrapper.getContentAsByteArray(),cachingResponseWrapper.getCharacterEncoding());
        String requestBody = getStringValue(cachingRequestWrapper.getContentAsByteArray(), cachingRequestWrapper.getCharacterEncoding());
        log.info("Request Body: {}", requestBody);
        log.info("Response Body: {}", responseBody);
        cachingResponseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray,0,contentAsByteArray.length,characterEncoding);
        } catch (UnsupportedEncodingException e) {
            log.error("An Exception occurred while converting contentAsByteArray to String");
            e.printStackTrace();
        }
        return "";
    }

    private boolean validateAuthorization(String authToken){
        // fetch tokens from db
        return "1000".equals(authToken);
    }

    private void handleExceptionResponse(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(401,CommonUtil.generateUUID(),"UnAuthorised Request");


        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}
