package pl.bobak.integrations.fdademo.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.bobak.integrations.fdademo.exception.NotFoundException;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplicationDto;
import pl.bobak.integrations.fdademo.repository.UserSubmittedDrugRecordApplicationRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserSubmittedDrugApplicationServiceTest {

    @Autowired
    private UserSubmittedDrugRecordApplicationService service;

    @Autowired
    private UserSubmittedDrugRecordApplicationRepository repository;

    @Test
    @Transactional
    void shouldPersistUserSubmittedApplication() {
        // given
        var dto = new UserSubmittedDrugRecordApplicationDto("1","anything", "anything", List.of());

        // when
        var entity = service.createUserSubmittedDrugApplication(dto);

        // then
        var fromDatabase = repository.getReferenceById(entity.getApplicationNumber());
        assertEquals(entity, fromDatabase);

    }

    @Test
    @Transactional
    void shouldReturnAllPersistUserSubmittedApplications() {
        // given
        var fixedObject = new UserSubmittedDrugRecordApplication("1","anything", "anything", List.of());
        var fixedObject2 = new UserSubmittedDrugRecordApplication("2","anything", "anything", List.of());
        repository.saveAll(List.of(fixedObject, fixedObject2));

        // when
        var result = service.getAllApplications();

        // then
        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(fixedObject, fixedObject2)));

    }

    @Test
    @Transactional
    void shouldReturnUserSubmittedApplicationById() {
        // given
        String fixedId = "1";
        var fixedObject = new UserSubmittedDrugRecordApplication(fixedId,"anything", "anything", List.of());
        repository.save(fixedObject);

        // when
        var result = service.getUserSubmittedDrugApplication(fixedId);

        // then
        assertEquals(fixedObject, result);
    }

    @Test
    void shouldThrowBadRequestWhenApplicationWithGivenIdDoesNotExist() {
        // given
        String nonExistentId = "1";

        // when & then
        assertThrows(NotFoundException.class, () -> service.getUserSubmittedDrugApplication(nonExistentId));


    }

}
