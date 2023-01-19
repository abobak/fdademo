package pl.bobak.integrations.fdademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;

@Repository
public interface UserSubmittedDrugRecordApplicationRepository
        extends JpaRepository<UserSubmittedDrugRecordApplication, String> {
}
