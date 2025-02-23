package az.ders.userms.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserResponse {
  private Long id;
  private String name;
  private String email;
}
