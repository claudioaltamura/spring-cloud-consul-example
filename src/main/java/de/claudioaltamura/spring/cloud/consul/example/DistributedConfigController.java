package de.claudioaltamura.spring.cloud.consul.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistributedConfigController {

    @Value("${greetings.message}")
    String value;

    private ExampleProperties exampleProperties;

    public DistributedConfigController(ExampleProperties blueprintProperties) {
        this.exampleProperties = blueprintProperties;
    }

    /**
     * Returns a config value through value annotation.
     * Be careful! config value doesn't get updated!
     * @return value
     */
    @GetMapping("/getConfigFromValue")
    public String getConfigFromValue() {
        return value;
    }

    @GetMapping("/getConfigFromProperty")
    public String getConfigFromProperty() {
        return exampleProperties.getMessage();
    }

}
