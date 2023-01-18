package pl.bobak.integrations.fdademo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.bobak.integrations.fdademo.configuration.OpenfdaSearchConfiguration;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OpenfdaSearchService {

    private final OpenfdaSearchConfiguration openfdaSearchConfiguration;

    private final WebClient webClient;

    private static final String BY_MANUFACTURER_PREDICATE = "openfda.manufacturer_name:";

    private static final String AND_BY_BRAND_NAME_PREDICATE = "\"+AND+openfda.brand_name:\"";

    public List<String> getApplications(String manufacturer, String brandName, Integer limit) throws JSONException {
        String data = getApplicationsData(manufacturer, brandName, limit);
        return getApplicationNumbersFromResult(data);
    }

    public String getApplicationsData(String manufacturer, String brandName, Integer limit) {
        return webClient
                .get()
                .uri(createUrl(manufacturer, brandName, limit))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    String createUrl(String manufacturer, String brandName, Integer limit) {
        String uri = openfdaSearchConfiguration.getSearchUrl() + BY_MANUFACTURER_PREDICATE + "\"" + manufacturer + "\"";
        if (nonNull(brandName)) {
            uri += AND_BY_BRAND_NAME_PREDICATE + "\"" + brandName + "\"";
        }
        if (nonNull(limit)) {
            uri += "&limit=" + limit;
        }
        return uri;
    }

    List<String> getApplicationNumbersFromResult(String result) throws JSONException {
        List<String> applicationNumbers = new LinkedList<>();
        JSONObject jo = new JSONObject(result);
        JSONArray ja = jo.getJSONArray("results");
        for (int i = 0; i< ja.length(); i++) {
            JSONObject obj = ja.getJSONObject(i);
            applicationNumbers.add((String)obj.get("application_number"));
        }
        return applicationNumbers;
    }
}
