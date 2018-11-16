package pl.piotrjaniszewski.crudrest.exceptionHandlers;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(BadCredentialsException.class)
//    public BasicErrorMsg handleUsernameNotFoundException(BadCredentialsException ex){
//        return new BasicErrorMsg(HttpStatus.UNAUTHORIZED,"Username not found",ex);
//    }

}
