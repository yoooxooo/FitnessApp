package app.it_academy.fitnessAppAudit.exceptions.handler;

import app.it_academy.fitnessAppAudit.exceptions.MultipleErrorResponse;
import app.it_academy.fitnessAppAudit.exceptions.SingleErrorResponse;
import app.it_academy.fitnessAppAudit.exceptions.custom.DeniedAccessException;
import app.it_academy.fitnessAppAudit.exceptions.custom.FieldValidationException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String ERROR = "error";
    private static final String STRUCTURED_ERROR = "structured error";

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleMissingPathVariableException(
            MissingPathVariableException ex) {
        String message = "The required path variable '" + ex.getVariableName()
                + "' is missing from the request URL.";
        SingleErrorResponse errorResponse = new SingleErrorResponse(ERROR, message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<MultipleErrorResponse> handleFieldValidationException(
            FieldValidationException ex) {
        MultipleErrorResponse errorResponse = new MultipleErrorResponse(STRUCTURED_ERROR, ex.getErrors());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({IllegalArgumentException.class, OptimisticLockException.class})
    public ResponseEntity<List<SingleErrorResponse>> handleIllegalArgumentException(
            RuntimeException ex) {
        SingleErrorResponse errorResponse = new SingleErrorResponse(ERROR, ex.getMessage());
        return ResponseEntity.badRequest().body(List.of(errorResponse));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleNoElementException(
            NoSuchElementException ex) {
        SingleErrorResponse errorResponse = new SingleErrorResponse(ERROR, "Id не существует");
        return ResponseEntity.badRequest().body(List.of(errorResponse));
    }

    @ExceptionHandler(DeniedAccessException.class)
    public ResponseEntity<List<SingleErrorResponse>> handleDeniedAccessException(
            DeniedAccessException ex) {
        SingleErrorResponse errorResponse = new SingleErrorResponse(ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of(errorResponse));
    }

}
