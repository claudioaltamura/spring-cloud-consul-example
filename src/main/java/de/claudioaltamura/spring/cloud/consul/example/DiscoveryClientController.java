package de.claudioaltamura.spring.cloud.consul.example;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.Optional;

@RestController
public class DiscoveryClientController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public DiscoveryClientController(RestTemplate restTemplate,DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    private Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("consul-example")
            .stream()
            .findFirst()
            .map(si -> si.getUri());
    }

    @GetMapping("/discoveryHost")
    public String discovery() throws RestClientException,
        ServiceUnavailableException {
        return serviceUrl()
            .map(s -> s.getHost())
            .orElseThrow(ServiceUnavailableException::new);
    }

    @GetMapping("/discoveryExample")
    public String discoveryExample() throws RestClientException,
      ServiceUnavailableException {
      URI service = serviceUrl()
        .map(s -> s.resolve("/helloworld"))
        .orElseThrow(ServiceUnavailableException::new);
      return restTemplate.getForEntity(service, String.class)
        .getBody();
    }

}
