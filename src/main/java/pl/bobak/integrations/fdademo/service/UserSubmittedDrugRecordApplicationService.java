package pl.bobak.integrations.fdademo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bobak.integrations.fdademo.exception.NotFoundException;
import pl.bobak.integrations.fdademo.mapper.UserSubmittedDrugRecordApplicationMapper;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplicationDto;
import pl.bobak.integrations.fdademo.repository.UserSubmittedDrugRecordApplicationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSubmittedDrugRecordApplicationService {

    private final UserSubmittedDrugRecordApplicationRepository userSubmittedDrugRecordApplicationRepository;

    private final UserSubmittedDrugRecordApplicationMapper mapper;

    public UserSubmittedDrugRecordApplication getUserSubmittedDrugApplication(String id) {
        if (userSubmittedDrugRecordApplicationRepository.existsById(id)) {
            return userSubmittedDrugRecordApplicationRepository.getReferenceById(id);
        } else {
            throw new NotFoundException("Application with id: " + id + " does not exist");
        }
    }

    @Transactional
    public UserSubmittedDrugRecordApplication createUserSubmittedDrugApplication(
            UserSubmittedDrugRecordApplicationDto input) {
        return userSubmittedDrugRecordApplicationRepository.save(mapper.dtoToEntity(input));
    }

    public List<UserSubmittedDrugRecordApplication> getAllApplications() {
        return userSubmittedDrugRecordApplicationRepository.findAll();
    }

}
