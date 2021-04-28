package ru.djonikga.testcontainers.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.djonikga.testcontainers.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
  Optional<UserModel> findByEmail(String email);

}
