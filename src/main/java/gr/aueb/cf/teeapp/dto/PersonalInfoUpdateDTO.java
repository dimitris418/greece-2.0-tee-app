package gr.aueb.cf.teeapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PersonalInfoUpdateDTO(

        @NotNull
        Long id,

        @NotEmpty(message = "Identity number is required")
        String identityNumber,

        @NotEmpty(message = "Place of birth is required")
        String placeOfBirth,

        @NotEmpty(message = "Municipality of registration is required")
        String municipalityOfRegistration
) {}
