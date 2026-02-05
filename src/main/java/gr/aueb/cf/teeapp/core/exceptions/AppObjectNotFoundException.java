package gr.aueb.cf.teeapp.core.exceptions;

public class AppObjectNotFoundException extends AppGenericException {

    private static final String DEFAULT_CODE = "Not Found";

    public AppObjectNotFoundException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
