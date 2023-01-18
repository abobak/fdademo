package pl.bobak.integrations.fdademo.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmittedDrugRecordApplication {

    @Id
    private String applicationNumber;


    private String manufacturerName;


    private String substanceName;

    @ElementCollection
    private List<String> productNumbers;

}
