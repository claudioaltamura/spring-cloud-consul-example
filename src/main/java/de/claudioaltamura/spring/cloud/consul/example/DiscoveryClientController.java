package de.claudioaltamura.spring.cloud.consul.example;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.Optional;

@RestController
public class DiscoveryClientController {

    private DiscoveryClient discoveryClient;

    public DiscoveryClientController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("consul-example")
            .stream()
            .findFirst()
            .map(si -> si.getUri());
    }

    @GetMapping("/discovery")
    public String discovery() throws RestClientException,
        ServiceUnavailableException {
        return serviceUrl()
            .map(s -> s.getHost())
            .orElseThrow(ServiceUnavailableException::new);
    }

}
