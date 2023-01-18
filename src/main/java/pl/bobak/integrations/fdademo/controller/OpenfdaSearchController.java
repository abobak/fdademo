package pl.bobak.integrations.fdademo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bobak.integrations.fdademo.exception.BadRequestException;
import pl.bobak.integrations.fdademo.service.OpenfdaSearchService;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
public class OpenfdaSearchController {

    private final OpenfdaSearchService searchService;

    @GetMapping("/search")
    public List<String> getApplications(
            @RequestParam String manufacturer,
            @RequestParam(required = false, name = "brand_name") String brandName,
            @RequestParam(required = false) Integer limit) throws JSONException {
        if (nonNull(limit) && (limit > 1000)) {
            throw new BadRequestException("Open FDA api limit of 1000 results exceeded, please use limit lower than 1000");
        }
        return searchService.getApplications(manufacturer, brandName, limit);
    }

}
