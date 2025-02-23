package az.ders.userms.mapper;

import az.ders.userms.entity.User;
import az.ders.userms.model.request.UserRequest;
import az.ders.userms.model.response.UserResponse;
import java.util.List;

public interface UserMapper {

  static List<UserResponse> mapToRequest(List<User> users) {
    return users.stream()
        .map(UserMapper::mapToResponse)
        .toList();
  }

  static UserResponse mapToResponse(User user) {
    return UserResponse.of(user.getId(), user.getName(), user.getEmail());
  }

  static User mapToEntity(UserRequest user) {
    return User.builder()
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }
}
