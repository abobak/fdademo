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
public class UserSubmittedDrugRecordApplicationDto {

    @JsonProperty("application_number")
    private String applicationNumber;

    @JsonProperty("manufacturer_name")
    private String manufacturerName;

    @JsonProperty("substance_name")
    private String substanceName;

    @JsonProperty("product_numbers")
    private List<String> productNumbers = new LinkedList<>();

}
