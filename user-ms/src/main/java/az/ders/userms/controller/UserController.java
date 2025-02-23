package az.ders.userms.controller;

import az.ders.userms.entity.User;
import az.ders.userms.model.request.UserRequest;
import az.ders.userms.model.response.UserResponse;
import az.ders.userms.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UserResponse> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@RequestBody UserRequest user) {
    return userService.createUser(user);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

}