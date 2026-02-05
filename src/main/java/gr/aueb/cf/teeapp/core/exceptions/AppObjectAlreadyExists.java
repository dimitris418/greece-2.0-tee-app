package gr.aueb.cf.teeapp.core.exceptions;

public class AppObjectAlreadyExists extends AppGenericException {

    private static final String DEFAULT_CODE = "Already Exists";

    public AppObjectAlreadyExists(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
