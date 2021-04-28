package ru.djonikga.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreContainer extends PostgreSQLContainer<PostgreContainer> {
  private static final String IMAGE_VERSION = "postgres:13";
  private static PostgreContainer container;

  private PostgreContainer() {
    super(IMAGE_VERSION);
  }

  public static PostgreContainer getInstance() {
    if (container == null) {
      container = new PostgreContainer();
    }
    container.withInitScript("data.sql");
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
    //do nothing, JVM handles shut down
  }
}
