package gr.aueb.cf.teeapp.service;

import gr.aueb.cf.teeapp.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.teeapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.teeapp.core.filters.ConsultantFilters;
import gr.aueb.cf.teeapp.core.filters.Paginated;
import gr.aueb.cf.teeapp.core.specifications.ConsultantSpecification;
import gr.aueb.cf.teeapp.dto.ConsultantInsertDTO;
import gr.aueb.cf.teeapp.dto.ConsultantReadOnlyDTO;
import gr.aueb.cf.teeapp.dto.ConsultantUpdateDTO;
import gr.aueb.cf.teeapp.mapper.Mapper;
import gr.aueb.cf.teeapp.model.Consultant;
import gr.aueb.cf.teeapp.repository.ConsultantRepository;
import gr.aueb.cf.teeapp.repository.PersonalInfoRepository;
import gr.aueb.cf.teeapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsultantService implements IConsultantService {

    private final ConsultantRepository consultantRepository;
    private final UserRepository userRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final Mapper mapper;


    @Autowired
    public ConsultantService(ConsultantRepository consultantRepository, UserRepository userRepository,
                          PersonalInfoRepository personalInfoRepository, Mapper mapper) {
        this.consultantRepository = consultantRepository;
        this.userRepository = userRepository;
        this.personalInfoRepository = personalInfoRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ConsultantReadOnlyDTO saveConsultant(ConsultantInsertDTO consultantInsertDTO) throws AppObjectAlreadyExists {

        if (userRepository.findByVat(consultantInsertDTO.userInsertDTO().vat()).isPresent()) {
            throw new AppObjectAlreadyExists("VAT", "User with vat " + consultantInsertDTO.userInsertDTO().vat() + " already exists");
        }

        if (userRepository.findByUsername(consultantInsertDTO.userInsertDTO().username()).isPresent()) {
            throw new AppObjectAlreadyExists("Username", "User with username " + consultantInsertDTO.userInsertDTO().username() + " already exists");
        }

        if (personalInfoRepository.findByIdentityNumber(consultantInsertDTO.personalInfoInsertDTO().identityNumber()).isPresent()) {
            throw new AppObjectAlreadyExists("IdentityNumber", "User with identity number " + consultantInsertDTO.personalInfoInsertDTO().identityNumber() + " already exists");
        }

        Consultant consultant = mapper.mapToConsultantEntity(consultantInsertDTO);

        Consultant savedConsultant = consultantRepository.save(consultant);


        log.info("Consultant with vat={} saved.", consultantInsertDTO.userInsertDTO().vat());
        return mapper.mapToConsultantReadOnlyDTO(savedConsultant);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ConsultantReadOnlyDTO updateConsultant(ConsultantUpdateDTO consultantUpdateDTO) throws AppObjectAlreadyExists, AppObjectNotFoundException {

        if (consultantRepository.findById(consultantUpdateDTO.id()).isEmpty()) {
            throw new AppObjectNotFoundException("Consultant", "Consultant with id " + consultantUpdateDTO.id() + " not found");
        }

        Consultant existingConsultant = consultantRepository.findById(consultantUpdateDTO.id()).orElse(null);
        if (existingConsultant == null) throw new AppObjectNotFoundException("Consultant", "Consultant with id=" + consultantUpdateDTO.id() + " not found");

        if (!existingConsultant.getUser().getVat().equals(consultantUpdateDTO.userUpdateDTO().vat()) &&
                userRepository.findByVat(consultantUpdateDTO.userUpdateDTO().vat()).isPresent()) {
            throw new AppObjectAlreadyExists("Consultant", "Consultant with vat " + consultantUpdateDTO.userUpdateDTO().vat() + " already exists");
        }

        if (!existingConsultant.getPersonalInfo().getIdentityNumber().equals(consultantUpdateDTO.personalInfoUpdateDTO().identityNumber()) &&
                personalInfoRepository.findByIdentityNumber(consultantUpdateDTO.personalInfoUpdateDTO().identityNumber()).isPresent()) {
            throw new AppObjectAlreadyExists("Consultant", "Consultant with identity number " + consultantUpdateDTO.personalInfoUpdateDTO().identityNumber() + " already exists");
        }

        Consultant consultantToUpdate = mapper.mapToConsultantEntity(consultantUpdateDTO);

        Consultant updatedConsultant = consultantRepository.save(consultantToUpdate);
        log.info("Consultant with id={} saved.", consultantUpdateDTO.personalInfoUpdateDTO().id());
        return mapper.mapToConsultantReadOnlyDTO(updatedConsultant);
    }

    @Override
    public ConsultantReadOnlyDTO getOneConsultant(String uuid) throws AppObjectNotFoundException {
        return consultantRepository
                .findByUuid(uuid)
                .map(mapper::mapToConsultantReadOnlyDTO)
                .orElseThrow(() ->
                        new AppObjectNotFoundException("Consultant", "Consultant with uuid:" + uuid + " not found"));
    }

    @Override
    public Paginated<ConsultantReadOnlyDTO> getPaginatedConsultants(int page, int size) {
        String defaultSort = "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(defaultSort).ascending());
        log.debug("Paginated consultants were returned successfully with page={} and size={}", page, size);
        var paginatedConsultants = consultantRepository.findAll(pageable);
        return Paginated.fromPage(paginatedConsultants.map(mapper::mapToConsultantReadOnlyDTO));
    }

    @Override
    public Paginated<ConsultantReadOnlyDTO> getConsultantsFilteredPaginated(ConsultantFilters consultantFilters) {
            var filtered = consultantRepository.findAll(getSpecsFromFilters(consultantFilters), consultantFilters.getPageable());
            log.debug("Filtered and paginated consultants were returned successfully with page={} and size={}", consultantFilters.getPage(),
                    consultantFilters.getPageSize());
            return Paginated.fromPage(filtered.map(mapper::mapToConsultantReadOnlyDTO));
        }

    private Specification<Consultant> getSpecsFromFilters(ConsultantFilters consultantFilters) {
        return ConsultantSpecification.consultantStringFieldLike("uuid", consultantFilters.getUuid())
                .and(ConsultantSpecification.consultantUserVatIs(consultantFilters.getUserVat()))
                .and(ConsultantSpecification.consultantUserIsActive(consultantFilters.getActive()));
    }
}
