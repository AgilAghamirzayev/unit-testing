package az.ders.userms.service;

import az.ders.userms.entity.User;
import az.ders.userms.exception.AppException;
import az.ders.userms.exception.constants.ExceptionConstants;
import az.ders.userms.mapper.UserMapper;
import az.ders.userms.model.request.UserRequest;
import az.ders.userms.model.response.UserResponse;
import az.ders.userms.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<UserResponse> getAllUsers() {
    log.info("Getting all users");
    return UserMapper.mapToRequest(userRepository.findAll());
  }

  public UserResponse getUserById(Long id) {
    log.info("Getting user by id: {}", id);
    User user = userRepository.findById(id).orElseThrow(() ->
        new AppException("by id: " + id, ExceptionConstants.NOT_FOUND_EXCEPTION));
    return UserMapper.mapToResponse(user);
  }

  public UserResponse createUser(UserRequest user) {
    log.info("Creating user: {}", user);
    return UserMapper.mapToResponse(userRepository.save(UserMapper.mapToEntity(user)));
  }

  public void deleteUser(Long id) {
    log.info("Deleting user by id: {}", id);
    userRepository.deleteById(id);
  }

}