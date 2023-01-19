package pl.bobak.integrations.fdademo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.bobak.integrations.fdademo.configuration.OpenfdaSearchConfiguration;
import pl.bobak.integrations.fdademo.mapper.DrugRecordApplicationMapper;
import pl.bobak.integrations.fdademo.model.DrugRecordApplicationDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OpenfdaSearchService {

    private final OpenfdaSearchConfiguration openfdaSearchConfiguration;

    private final WebClient webClient;

    private static final String BY_MANUFACTURER_PREDICATE = "openfda.manufacturer_name:";

    private static final String AND_BY_BRAND_NAME_PREDICATE = "+AND+openfda.brand_name:";

    public List<DrugRecordApplicationDto> getApplications(
            String manufacturer, String brandName, Integer limit, Integer page) throws JSONException, JsonProcessingException {
        String data = getApplicationsData(manufacturer, brandName, limit, page);
        return getApplicationDataFromResult(data);
    }

    public String getApplicationsData(String manufacturer, String brandName, Integer limit, Integer page) {
        return webClient
                .get()
                .uri(createUrl(manufacturer, brandName, limit, page))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    String createUrl(String manufacturer, String brandName, Integer limit, Integer page) {
        String uri = openfdaSearchConfiguration.getSearchUrl() + BY_MANUFACTURER_PREDICATE + "\"" + manufacturer + "\"";
        if (nonNull(brandName)) {
            uri += AND_BY_BRAND_NAME_PREDICATE + "\"" + brandName + "\"";
        }
        if (nonNull(limit)) {
            uri += "&limit=" + limit;
        }
        if (nonNull(limit) && nonNull(page) && page > 1) {
            uri += "&skip=" + (page - 1) * limit;
        }
        return uri;
    }

    List<DrugRecordApplicationDto> getApplicationDataFromResult(String result) throws JSONException, JsonProcessingException {
        List<DrugRecordApplicationDto> applicationNumbers = new LinkedList<>();
        JSONArray results = new JSONObject(result).getJSONArray("results");
        for (int i = 0; i< results.length(); i++) {
            JSONObject obj = results.getJSONObject(i);
            DrugRecordApplicationDto dto = DrugRecordApplicationMapper.jsonObjectToDto(obj.getJSONObject("openfda"));
            dto = enrichDtoWithProductNumbers(dto, obj.getJSONArray("products"));
            applicationNumbers.add(dto);
        }
        return applicationNumbers;
    }

    DrugRecordApplicationDto enrichDtoWithProductNumbers(DrugRecordApplicationDto dto, JSONArray products) throws JSONException {
        if (nonNull(products)) {
            for (int i = 0; i < products.length(); i++) {
                dto.getProductNumbers().add((String) products.getJSONObject(i).get("product_number"));
            }
        }
        return dto;
    }
}
