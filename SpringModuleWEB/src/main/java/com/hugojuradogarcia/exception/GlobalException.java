package com.hugojuradogarcia.exception;

import com.hugojuradogarcia.model.output.Response;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;

@ControllerAdvice
@PropertySource("classpath:application.properties")
public class GlobalException {

    @Resource
    Environment environment;

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity handlerNotFound(GenericNotFoundException e) {
        e.printStackTrace();
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .statusText(environment.getProperty("httpStatus.badRequest.statusText"))
                .message(environment.getProperty("httpStatus.notFound.messages"))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ArgumentTypeMismatch
    @ResponseBody
    @ExceptionHandler
    public Response handleArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        return Response.builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .statusText(environment.getProperty("httpStatus.badRequest.statusText"))
                .message(environment.getProperty("httpStatus.badRequest.messages"))
                .build();
    }

    // Bad Request
    @ExceptionHandler
    public ResponseEntity<Response> handlerBadRequest(HttpMessageNotReadableException e){
        Response responseError = Response.builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .statusText(environment.getProperty("httpStatus.badRequest.messages"))
                .message(environment.getProperty("httpStatus.badRequest.messages"))
                .build();

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
    @ResponseBody
    @ExceptionHandler
    public Response handlerException(Exception e) {
        e.printStackTrace();
        return Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .statusText(environment.getProperty("httpStatus.badRequest.statusText"))
                .message(environment.getProperty("httpStatus.ioException.messages"))
                .build();
    }
}
