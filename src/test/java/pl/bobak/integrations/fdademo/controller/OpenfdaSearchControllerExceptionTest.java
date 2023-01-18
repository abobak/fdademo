package pl.bobak.integrations.fdademo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.bobak.integrations.fdademo.service.OpenfdaSearchService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OpenfdaSearchController.class)
public class OpenfdaSearchControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenfdaSearchService openfdaSearchService;

    @Test
    void shouldThrowExceptionWhenManufacturerIsNotProvided() throws Exception {
        mockMvc.perform(get("/search?brand_name=\"anything\"")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowExceptionWhenLimitProvidedIsGreaterThan1000() throws Exception {
        mockMvc.perform(get("/search?manufacturer=\"anything\"&limit=1001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInputParametersAreCorrectThenSearchServiceIsInvoked() throws Exception {
        Mockito.when(openfdaSearchService.getApplications(any(), any(), any())).thenReturn(List.of());
        mockMvc.perform(get("/search?manufacturer=\"anything\"&limit=100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(openfdaSearchService, Mockito.times(1)).getApplications(any(), any(), any());
    }

}
