package gr.aueb.cf.teeapp.core.exceptions;

public class AppObjectNotAuthorizedException extends AppGenericException {

    private static final String DEFAULT_CODE = "Not Authorized";

    public AppObjectNotAuthorizedException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
