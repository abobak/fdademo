package pl.bobak.integrations.fdademo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.bobak.integrations.fdademo.service.OpenfdaSearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenfdaSearchController {

    private final OpenfdaSearchService searchService;

    @GetMapping("/search/{manufacturer}")
    public List<String> getApplicationsByManufacturer(@PathVariable String manufacturer) {
        return List.of(searchService.getApplicationsByManufacturer(manufacturer));
    }

}
