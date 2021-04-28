package ru.djonikga.testcontainers.service;

import java.util.List;
import ru.djonikga.testcontainers.dto.UserDto;

public interface UserService {
  List<UserDto> getAll();
  UserDto getByEmail(String email);
}
