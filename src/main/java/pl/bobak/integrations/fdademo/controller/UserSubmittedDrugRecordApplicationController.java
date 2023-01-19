package pl.bobak.integrations.fdademo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplicationDto;
import pl.bobak.integrations.fdademo.service.UserSubmittedDrugRecordApplicationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserSubmittedDrugRecordApplicationController {

    private final UserSubmittedDrugRecordApplicationService userSubmittedDrugRecordApplicationService;


    @PostMapping("/applications")
    public UserSubmittedDrugRecordApplication createUserSubmittedDrugApplication(
            @RequestBody UserSubmittedDrugRecordApplicationDto input) {
        return userSubmittedDrugRecordApplicationService.createUserSubmittedDrugApplication(input);
    }

    @GetMapping("/applications/{applicationId}")
    public UserSubmittedDrugRecordApplication getApplication(@PathVariable String applicationId) {
        return userSubmittedDrugRecordApplicationService.getUserSubmittedDrugApplication(applicationId);
    }

    @GetMapping("/applications")
    public List<UserSubmittedDrugRecordApplication> getApplications() {
        return userSubmittedDrugRecordApplicationService.getAllApplications();
    }

}
