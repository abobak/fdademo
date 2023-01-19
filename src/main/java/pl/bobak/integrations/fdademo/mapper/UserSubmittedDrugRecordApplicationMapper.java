package pl.bobak.integrations.fdademo.mapper;

import org.mapstruct.Mapper;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplication;
import pl.bobak.integrations.fdademo.model.UserSubmittedDrugRecordApplicationDto;

@Mapper(componentModel = "spring")
public interface UserSubmittedDrugRecordApplicationMapper {

    UserSubmittedDrugRecordApplication dtoToEntity(UserSubmittedDrugRecordApplicationDto input);

}
