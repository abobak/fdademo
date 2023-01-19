package pl.bobak.integrations.fdademo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.json.JsonObject;
import pl.bobak.integrations.fdademo.model.DrugRecordApplication;
import pl.bobak.integrations.fdademo.model.DrugRecordApplicationDto;

import java.util.LinkedList;
import java.util.List;

public class DrugRecordApplicationMapper {

    public static DrugRecordApplicationDto jsonObjectToDto(JsonObject obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(obj.toString(), DrugRecordApplicationDto.class);
    }

    public static DrugRecordApplication dtoToEntity(DrugRecordApplicationDto dto) {
        DrugRecordApplication application = new DrugRecordApplication();
        application.setApplicationNumber(dto.getApplicationNumber().get(0));
        application.setProductNumbers(dto.getProductNumbers());
        application.setManufacturerNames(dto.getManufacturerNames());
        application.setSubstanceNames(dto.getSubstanceNames());
        return application;
    }

    public static List<DrugRecordApplication> dtosToEntities(List<DrugRecordApplicationDto> dtos) {
        List<DrugRecordApplication> result = new LinkedList<>();
        for (DrugRecordApplicationDto dto : dtos) {
            result.add(dtoToEntity(dto));
        }
        return result;
    }

}
