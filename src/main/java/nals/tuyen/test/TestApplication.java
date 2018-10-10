package nals.tuyen.test;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class TestApplication {
  @PostConstruct
  void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));
  }
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
