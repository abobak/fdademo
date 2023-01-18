package pl.bobak.integrations.fdademo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class DrugRecordApplication {

    private String applicationNumber;

    private List<String> manufacturerNames;

    private List<String> substanceNames;

    private List<String> productNumbers;

}
