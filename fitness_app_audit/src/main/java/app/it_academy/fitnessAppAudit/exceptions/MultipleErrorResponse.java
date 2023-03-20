package app.it_academy.fitnessAppAudit.exceptions;

import java.util.List;

public class MultipleErrorResponse {

    private String logref;
    private List<ErrorObject> errors;

    public MultipleErrorResponse() {
    }

    public MultipleErrorResponse(String logref, List<ErrorObject> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorObject> errors) {
        this.errors = errors;
    }
}
