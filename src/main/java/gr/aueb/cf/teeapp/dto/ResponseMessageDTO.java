package gr.aueb.cf.teeapp.dto;

public record ResponseMessageDTO(String code, String description) {

    public ResponseMessageDTO(String code) {
        this(code, "");     // Calls the canonical constructor
    }
}
