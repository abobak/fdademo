package pl.bobak.integrations.fdademo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import pl.bobak.integrations.fdademo.model.DrugRecordApplication;
import pl.bobak.integrations.fdademo.model.DrugRecordApplicationDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrugRecordApplicationMapperTest {

    String validJsonPayload = """
            {
                 "submissions": [
                   {
                     "submission_type": "ORIG",
                     "submission_number": "1",
                     "submission_status": "AP",
                     "submission_status_date": "20171004",
                     "review_priority": "STANDARD",
                     "submission_class_code": "UNKNOWN"
                   }
                 ],
                 "application_number": "ANDA206242",
                 "sponsor_name": "EUGIA PHARMA",
                 "openfda": {
                   "application_number": [
                     "ANDA206242"
                   ],
                   "brand_name": [
                     "MOXIFLOXACIN"
                   ],
                   "generic_name": [
                     "MOXIFLOXACIN"
                   ],
                   "manufacturer_name": [
                     "Aurobindo Pharma Limited",
                     "NorthStar Rx LLC"
                   ],
                   "product_ndc": [
                     "65862-840",
                     "16714-643"
                   ],
                   "product_type": [
                     "HUMAN PRESCRIPTION DRUG"
                   ],
                   "route": [
                     "OPHTHALMIC"
                   ],
                   "substance_name": [
                     "MOXIFLOXACIN HYDROCHLORIDE"
                   ],
                   "rxcui": [
                     "403818"
                   ],
                   "spl_id": [
                     "bdb0fe50-978d-4a1b-a3be-9567d7375ad8",
                     "c11c970c-ffa6-4661-b515-708bae4eddd6"
                   ],
                   "spl_set_id": [
                     "8877013d-9b49-40f7-a2c4-99bb519e104e",
                     "cdc0b0eb-5f56-45c7-b9fd-364752a95ff1"
                   ],
                   "package_ndc": [
                     "65862-840-03",
                     "16714-643-01"
                   ],
                   "unii": [
                     "C53598599T"
                   ]
                 },
                 "products": [
                   {
                     "product_number": "001",
                     "reference_drug": "No",
                     "brand_name": "MOXIFLOXACIN HYDROCHLORIDE",
                     "active_ingredients": [
                       {
                         "name": "MOXIFLOXACIN HYDROCHLORIDE",
                         "strength": "EQ 0.5% BASE"
                       }
                     ],
                     "reference_standard": "No",
                     "dosage_form": "SOLUTION/DROPS",
                     "route": "OPHTHALMIC",
                     "marketing_status": "Prescription",
                     "te_code": "AT1"
                   }
                 ]
               }""".indent(1);

    @Test
    void shouldMapJsonPayloadToDto() throws JSONException, JsonProcessingException {
        // given
        JSONObject jsonObject = new JSONObject(validJsonPayload).getJSONObject("openfda");
        String expectedApplicationNumber = "ANDA206242";

        // when
        DrugRecordApplicationDto dto = DrugRecordApplicationMapper.jsonObjectToDto(jsonObject);

        // then
        assertEquals(expectedApplicationNumber, dto.getApplicationNumber().get(0));

    }

    @Test
    void shouldMapDtoToEntity() throws JSONException, JsonProcessingException {
        // given
        JSONObject jsonObject = new JSONObject(validJsonPayload).getJSONObject("openfda");
        DrugRecordApplicationDto dto = DrugRecordApplicationMapper.jsonObjectToDto(jsonObject);

        // when
        DrugRecordApplication entity = DrugRecordApplicationMapper.dtoToEntity(dto);

        // then
        assertEquals(dto.getApplicationNumber().get(0), entity.getApplicationNumber());
        assertEquals(dto.getManufacturerNames(), entity.getManufacturerNames());
        assertEquals(dto.getProductNumbers(), entity.getProductNumbers());
        assertEquals(dto.getSubstanceNames(), entity.getSubstanceNames());
    }

    @Test
    void shouldMapListsOfDtosToListsOfEntities() throws JSONException, JsonProcessingException {
        // given
        JSONObject jsonObject = new JSONObject(validJsonPayload).getJSONObject("openfda");
        DrugRecordApplicationDto dto = DrugRecordApplicationMapper.jsonObjectToDto(jsonObject);

        // when
        List<DrugRecordApplication> entities = DrugRecordApplicationMapper.dtosToEntities(List.of(dto));

        // then
        assertEquals(1, entities.size());

    }


}
