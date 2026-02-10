package gr.aueb.cf.teeapp.core.filters;


import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ConsultantFilters extends GenericFilters {

    @Nullable
    private String uuid;

    @Nullable
    private String userVat;

    @Nullable
    private Boolean active;
}
