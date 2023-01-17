package pl.bobak.integrations.fdademo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bobak.integrations.fdademo.configuration.OpenfdaSearchConfiguration;

@Service
@RequiredArgsConstructor
public class OpenfdaSearchService {

    private final OpenfdaSearchConfiguration openfdaSearchConfiguration;

    private static final String BY_MANUFACTURER_PREDICATE = "openfda.manufacturer_name:";

    public String getApplicationsByManufacturer(String manufacturer) {
        return createUrl(manufacturer);
    }

    String createUrl(String manufacturer) {
        return openfdaSearchConfiguration.getSearchUrl() + BY_MANUFACTURER_PREDICATE + "\"" + manufacturer + "\"";
    }
}
