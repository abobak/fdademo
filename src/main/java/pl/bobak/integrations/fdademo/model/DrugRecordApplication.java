package pl.bobak.integrations.fdademo.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DrugRecordApplication {

    @Id
    private String applicationNumber;

    @ElementCollection
    private List<String> manufacturerNames;

    @ElementCollection
    private List<String> substanceNames;

    @ElementCollection
    private List<String> productNumbers;

}
