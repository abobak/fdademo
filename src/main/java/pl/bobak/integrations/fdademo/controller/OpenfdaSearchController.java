package pl.bobak.integrations.fdademo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bobak.integrations.fdademo.exception.BadRequestException;
import pl.bobak.integrations.fdademo.mapper.DrugRecordApplicationMapper;
import pl.bobak.integrations.fdademo.model.DrugRecordApplication;
import pl.bobak.integrations.fdademo.service.OpenfdaSearchService;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
public class OpenfdaSearchController {

    private final OpenfdaSearchService searchService;

    @GetMapping("/search")
    public List<DrugRecordApplication> getApplications(
            @RequestParam String manufacturer,
            @RequestParam(required = false, name = "brand_name") String brandName,
            @RequestParam(required = false, name = "page_size") Integer limit,
            @RequestParam(required = false, name = "page") Integer page) throws JSONException, JsonProcessingException {
        if (nonNull(limit) && (limit > 1000)) {
            throw new BadRequestException("Open FDA api limit of 1000 results exceeded, please use limit lower than 1000");
        }
        return DrugRecordApplicationMapper.dtosToEntities(
                searchService.getApplications(manufacturer, brandName, limit, page));
    }

}
