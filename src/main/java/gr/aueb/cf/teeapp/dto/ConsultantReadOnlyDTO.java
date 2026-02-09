package gr.aueb.cf.teeapp.dto;

public record ConsultantReadOnlyDTO(
        Long id,
        String uuid,
        Boolean isActive,
        UserReadOnlyDTO userReadOnlyDTO,
        PersonalInfoReadOnlyDTO personalInfoReadOnlyDTO
) {}
