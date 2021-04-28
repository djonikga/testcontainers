package ru.djonikga.testcontainers.controller;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.djonikga.testcontainers.dto.UserDto;
import ru.djonikga.testcontainers.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping("/user")
  public ResponseEntity<?> getByEmail(@RequestParam(name = "email") String email) {
    return ResponseEntity.ok(userService.getByEmail(email));
  }

  @GetMapping("/csv")
  public ResponseEntity<StreamingResponseBody> exampleCsv() {
    StreamingResponseBody stream = output -> {
      Writer writer = new BufferedWriter(new OutputStreamWriter(output));
      writer.write("name" + "," + "email" + "\n");
      List<UserDto> users = userService.getAll();
      for (UserDto user : users) {
        writer.write(user.getName() + "," + user.getEmail() + "\n");
        writer.flush();
      }
    };
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.csv")
        .contentType(MediaType.TEXT_PLAIN)
        .body(stream);
  }
}
