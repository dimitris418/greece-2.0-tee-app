package gr.aueb.cf.teeapp.dto;

import lombok.Builder;

@Builder
public record ConsultantReadOnlyDTO(

        Long id,
        String uuid,
        Boolean isActive,
        UserReadOnlyDTO userReadOnlyDTO,
        PersonalInfoReadOnlyDTO personalInfoReadOnlyDTO) {}
