package de.claudioaltamura.spring.cloud.consul.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @RequestMapping("/helloworld")
  public String helloworld() {
    return "Hello World!";
  }

}
