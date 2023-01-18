package pl.bobak.integrations.fdademo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DrugRecordApplicationDto {

    @JsonProperty("application_number")
    private List<String> applicationNumber;

    @JsonProperty("manufacturer_name")
    private List<String> manufacturerNames;

    @JsonProperty("substance_name")
    private List<String> substanceNames;

    private List<String> productNumbers = new LinkedList<>();

}
