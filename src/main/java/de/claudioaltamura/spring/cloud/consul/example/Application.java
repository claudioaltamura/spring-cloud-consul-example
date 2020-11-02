package de.claudioaltamura.spring.cloud.consul.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

  public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
    importKV2Consul();

    SpringApplication.run(de.claudioaltamura.spring.cloud.consul.example.Application.class, args);
  }

  private static void importKV2Consul() throws URISyntaxException, IOException, InterruptedException {
    var value = "buonasera";
    var keyValuePath = "/config/consul-example/greetings/message";
    var resourceUrl = "http://127.0.0.1:8500/v1/kv" + keyValuePath;

    HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI(resourceUrl))
      .PUT(HttpRequest.BodyPublishers.ofString(value))
      .build();

    HttpClient
      .newBuilder()
      .build()
      .send(request, HttpResponse.BodyHandlers.ofString());
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
