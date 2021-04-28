package ru.djonikga.testcontainers.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.djonikga.testcontainers.PostgreContainer;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = UserServiceTest.Initializer.class)
class UserServiceTest {

  @Autowired
  UserService userService;

  @Container
  public static PostgreSQLContainer<?> postgreSQLContainer = PostgreContainer.getInstance();

  static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
          "spring.datasource.username=" + postgreSQLContainer.getUsername(),
          "spring.datasource.password=" + postgreSQLContainer.getPassword()

      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  void connect() {
    assertTrue(postgreSQLContainer.isRunning());
  }

  @Test
  public void findAll() {
    Assertions.assertEquals(3, userService.getAll().size());
  }

  @Test
  public void getByEmail() {
    String email = "i.ivanov@vtb.ru";
    Assertions.assertEquals(email, userService.getByEmail(email).getEmail());
  }
}