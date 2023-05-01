package das.spring.security.app.controller.handlers;

import das.spring.security.app.models.exceptions.EmptyBodyException;
import das.spring.security.app.models.exceptions.ErrorResponse;
import das.spring.security.app.models.exceptions.UnAuthorizedRequest;
import das.spring.security.app.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyBodyException.class)
    public ResponseEntity<ErrorResponse> emptyBodyExceptionHandler(EmptyBodyException ex){
        ErrorResponse errorResponse = new ErrorResponse(500, CommonUtil.generateUUID(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(UnAuthorizedRequest.class)
//    public ResponseEntity<ErrorResponse> unAuthorizedRequestException(UnAuthorizedRequest ex){
//        ErrorResponse errorResponse = new ErrorResponse(401,CommonUtil.generateUUID(),ex.getMessage());
//        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
//    }
}
