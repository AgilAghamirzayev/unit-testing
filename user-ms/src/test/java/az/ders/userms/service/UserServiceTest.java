package az.ders.userms.service;

import static az.ders.userms.mock.MockUser.USER_1;
import static az.ders.userms.mock.MockUser.USER_1_ID;
import static az.ders.userms.mock.MockUser.USER_LIST;
import static az.ders.userms.mock.MockUser.USER_REQUEST_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import az.ders.userms.exception.AppException;
import az.ders.userms.model.response.UserResponse;
import az.ders.userms.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  void testGetAllUsers() {
    when(userRepository.findAll()).thenReturn(USER_LIST);

    List<UserResponse> users = userService.getAllUsers();
    assertEquals(2, users.size());
  }

  @Test
  void testGetUserById_UserExists() {
    when(userRepository.findById(USER_1_ID)).thenReturn(Optional.of(USER_1));

    UserResponse foundUser = userService.getUserById(USER_1_ID);
    assertEquals("Ali", foundUser.getName());
  }

  @Test
  void testGetUserById_UserNotFound() {
    when(userRepository.findById(USER_1_ID)).thenReturn(Optional.empty());

    Exception exception =
        assertThrows(AppException.class, () -> userService.getUserById(USER_1_ID));
    assertEquals("Not found by id: 1", exception.getMessage());
  }

  @Test
  void testCreateUser() {
    when(userRepository.save(USER_1)).thenReturn(USER_1);

    UserResponse savedUser = userService.createUser(USER_REQUEST_1);
    assertEquals("Ali", savedUser.getName());
  }

  @Test
  void testDeleteUser() {
    doNothing().when(userRepository).deleteById(USER_1_ID);
    userService.deleteUser(USER_1_ID);
    verify(userRepository, times(1)).deleteById(USER_1_ID);
  }

}