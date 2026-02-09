package gr.aueb.cf.teeapp.dto;

import lombok.Builder;

@Builder
public record UserReadOnlyDTO(String firstname, String lastname, String vat) {}


