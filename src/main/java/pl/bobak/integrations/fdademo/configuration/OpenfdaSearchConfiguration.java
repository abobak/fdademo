package pl.bobak.integrations.fdademo.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openfda.api")
@EnableConfigurationProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenfdaSearchConfiguration {
    private String searchUrl;
}
