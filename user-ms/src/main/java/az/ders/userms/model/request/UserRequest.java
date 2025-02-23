package az.ders.userms.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserRequest {
  private String name;
  private String email;
}
