package gr.aueb.cf.teeapp.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record AuthenticationRequestDTO(@NotNull String username, @NotNull String password)
{}
