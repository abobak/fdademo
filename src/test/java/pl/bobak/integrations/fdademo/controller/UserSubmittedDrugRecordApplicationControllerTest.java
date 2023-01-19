package pl.bobak.integrations.fdademo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplicationDto;
import pl.bobak.integrations.fdademo.service.UserSubmittedDrugRecordApplicationService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserSubmittedDrugRecordApplicationController.class)
class UserSubmittedDrugRecordApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSubmittedDrugRecordApplicationService mockedService;

    @Test
    void shouldReturn200WhenUserSubmittedNewDrugApplicationFromData() throws Exception {
        // given
        when(mockedService.createUserSubmittedDrugApplication(any())).thenReturn(any());
        UserSubmittedDrugRecordApplicationDto dto = new UserSubmittedDrugRecordApplicationDto("any", null, null, null);
        ObjectMapper mapper = new ObjectMapper();

        // when & then
        mockMvc.perform(post("/applications")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto))
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenUserDidntSubmitNewDrugApplicationFromData() throws Exception {
        // when & then
        mockMvc.perform(post("/applications")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnDrugApplicationDataWhenUserSubmitsValidApplicationId() throws Exception {
        // given
        String fixedId = "ABC123";
        UserSubmittedDrugRecordApplication fixedObject = new UserSubmittedDrugRecordApplication(fixedId,"anything", "anything", List.of());
        when(mockedService.getUserSubmittedDrugApplication(fixedId)).thenReturn(fixedObject);
        ObjectMapper mapper = new ObjectMapper();

        // when & then
        mockMvc.perform(get("/applications/"+fixedId)
                .accept((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fixedObject)));
    }

    @Test
    void shouldReturnDrugApplicationsData() throws Exception {
        // given
        UserSubmittedDrugRecordApplication fixedObject = new UserSubmittedDrugRecordApplication("1","anything", "anything", List.of());
        UserSubmittedDrugRecordApplication fixedObject2 = new UserSubmittedDrugRecordApplication("2","anything", "anything", List.of());
        List<UserSubmittedDrugRecordApplication> expectedList = List.of(fixedObject, fixedObject2);
        when(mockedService.getAllApplications()).thenReturn(expectedList);
        ObjectMapper mapper = new ObjectMapper();

        // when & then
        mockMvc.perform(get("/applications")
                        .accept((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedList)));
    }

}
