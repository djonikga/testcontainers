package ru.djonikga.testcontainers.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.djonikga.testcontainers.dto.UserDto;
import ru.djonikga.testcontainers.error.exception.UserNotFoundException;
import ru.djonikga.testcontainers.model.UserModel;
import ru.djonikga.testcontainers.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceIml implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  @Override
  public List<UserDto> getAll() {
    return userRepository.findAll()
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto getByEmail(String email) {
    UserModel user = userRepository
        .findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Пользователь не найден!"));;
    return toDto(user);
  }

  private UserDto toDto(UserModel user) {
    return modelMapper.map(user, UserDto.class);
  }
}
