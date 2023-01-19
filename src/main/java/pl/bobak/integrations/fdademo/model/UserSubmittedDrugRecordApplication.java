package pl.bobak.integrations.fdademo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserSubmittedDrugRecordApplication {

    @Id
    private String applicationNumber;


    private String manufacturerName;


    private String substanceName;

    @ElementCollection
    private List<String> productNumbers;

}
