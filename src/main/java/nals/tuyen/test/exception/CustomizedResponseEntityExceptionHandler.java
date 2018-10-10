package nals.tuyen.test.exception;

import javassist.tools.web.BadHttpRequest;
import nals.tuyen.test.dto.APIResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<APIResponseDTO> handleAllException(Exception ex, WebRequest request) {
    //check web or api
    APIResponseDTO errorDetails = new APIResponseDTO(500, "INTERNAL SERVER ERROR", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<APIResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(403, "Access Denied Exception", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(MethodNotAllowedException.class)
  public final ResponseEntity<APIResponseDTO> handleMethodNotAllowedException(MethodNotAllowedException ex, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(405, "Method not allowed", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(BadHttpRequest.class)
  public final ResponseEntity<APIResponseDTO> handleBadHttpRequest(BadHttpRequest ex, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(400, "BAD REQUEST", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<APIResponseDTO> handleUserCustomException(CustomException cx, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(cx.getHttpStatus(), cx.getMessage(), null);
    switch (cx.getHttpStatus()) {
      case 422:
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
      case 403:
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
      case 404:
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
      case 500:
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
      default:
        return new ResponseEntity<>(new APIResponseDTO(400, cx.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
  }


}