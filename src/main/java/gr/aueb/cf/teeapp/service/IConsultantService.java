package gr.aueb.cf.teeapp.service;

import gr.aueb.cf.teeapp.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.teeapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.teeapp.core.filters.ConsultantFilters;
import gr.aueb.cf.teeapp.core.filters.Paginated;
import gr.aueb.cf.teeapp.dto.ConsultantInsertDTO;
import gr.aueb.cf.teeapp.dto.ConsultantReadOnlyDTO;
import gr.aueb.cf.teeapp.dto.ConsultantUpdateDTO;

public interface IConsultantService {

    ConsultantReadOnlyDTO saveConsultant(ConsultantInsertDTO consultantInsertDTO)
            throws AppObjectAlreadyExists;

    ConsultantReadOnlyDTO updateConsultant(ConsultantUpdateDTO consultantUpdateDTO)
            throws AppObjectAlreadyExists, AppObjectNotFoundException;

    ConsultantReadOnlyDTO getOneConsultant(String uuid) throws AppObjectNotFoundException;

    Paginated<ConsultantReadOnlyDTO> getPaginatedConsultants(int page, int size);

    Paginated<ConsultantReadOnlyDTO> getConsultantsFilteredPaginated(ConsultantFilters consultantFilters);
}
