package app.it_academy.fitnessAppMail.core.exeptions.handler;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String ERROR = "error";
    private static final String STRUCTURED_ERROR = "structured error";

    @ExceptionHandler(MessagingException.class)
    public void handleMessagingException(
            MessagingException ex) {
        //Так как пароль высылается только при регистрации, при чем асинхронно, выкидывание ошибки уже никак не повлияет на ход процесса
    }



}
