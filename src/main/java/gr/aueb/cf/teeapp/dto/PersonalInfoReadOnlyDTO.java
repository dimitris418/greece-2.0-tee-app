package gr.aueb.cf.teeapp.dto;

import lombok.Builder;

@Builder
public record PersonalInfoReadOnlyDTO(String identityNumber) {}
