package pl.bobak.integrations.fdademo.controller;

import org.springframework.web.bind.annotation.*;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplicationDto;

@RestController
public class UserSubmittedDrugRecordApplicationController {

    @PostMapping("/applications")
    public UserSubmittedDrugRecordApplicationDto createUserSubmittedDrugApplication(
            @RequestBody UserSubmittedDrugRecordApplicationDto input) {
        return input;
    }

    @GetMapping("/applications/{applicationId}")
    public UserSubmittedDrugRecordApplication getApplication(@PathVariable String applicationId) {
        return new UserSubmittedDrugRecordApplication(applicationId, null, null, null);
    }

}
