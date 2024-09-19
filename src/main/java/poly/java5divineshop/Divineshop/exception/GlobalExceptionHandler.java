package poly.java5divineshop.Divineshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import poly.java5divineshop.Divineshop.constant.ResponseObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseObject<?>> handleCustomException(CustomException ex) {
        ResponseObject<?> response = ResponseObject.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, ex.getStatus());
    }
}
