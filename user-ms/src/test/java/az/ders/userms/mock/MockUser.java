package az.ders.userms.mock;

import az.ders.userms.entity.User;
import az.ders.userms.model.request.UserRequest;
import az.ders.userms.model.response.UserResponse;
import java.util.List;

public interface MockUser {

  Long INVALID_ID = -1L;
  Long USER_ID_1 = 1L;
  Long USER_ID_2 = 2L;

  User USER_1 = User.of(null, "Ali", "ali@example.com", null, null);
  User USER_2 = User.of(null, "Vali", "vali@example.com", null, null);

  UserResponse USER_RESPONSE_1 = UserResponse.of(USER_ID_1, "Ali", "ali@example.com");
  UserResponse USER_RESPONSE_2 = UserResponse.of(USER_ID_2, "Vali", "vali@example.com");

  UserRequest USER_REQUEST_1 = UserRequest.of("Ali", "ali@example.com");

  List<User> USER_LIST = List.of(USER_1, USER_2);
  List<UserResponse> USER_RESPONSE_LIST = List.of(USER_RESPONSE_1, USER_RESPONSE_2);

  String USER_1_JSON = """ 
      {
        "name": "Ali",
        "email": "ali@example.com"
      }
      """;

  String USER_2_JSON = """ 
      {
        "name": "Vali",
        "email": "vali@example.com"
      }
      """;

}
