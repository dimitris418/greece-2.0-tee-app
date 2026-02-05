package gr.aueb.cf.teeapp.core.exceptions;

public class AppObjectInvalidArgumentException extends AppGenericException {

    private static final String DEFAULT_CODE = "Invalid Argument";

    public AppObjectInvalidArgumentException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
