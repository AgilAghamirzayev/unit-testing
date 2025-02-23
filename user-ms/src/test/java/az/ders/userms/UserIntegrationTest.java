package az.ders.userms;

import static az.ders.userms.mock.MockUser.USER_1_JSON;
import static az.ders.userms.mock.MockUser.USER_2_JSON;
import static az.ders.userms.mock.MockUser.USER_3_JSON;
import static az.ders.userms.mock.MockUser.USER_4_JSON;
import static az.ders.userms.mock.MockUser.USER_5_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import az.ders.userms.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class UserIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testGetAllUsers() throws Exception {
    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_1_JSON))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_2_JSON))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  void testGetUserById() throws Exception {
    String response = mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_3_JSON))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    User createdUser = objectMapper.readValue(response, User.class);

    mockMvc.perform(get("/api/v1/users/" + createdUser.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(createdUser.getId()))
        .andExpect(jsonPath("$.name").value("Aysu"))
        .andExpect(jsonPath("$.email").value("aysu@example.com"));
  }

  @Test
  void testCreateUser() throws Exception {
    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_4_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Lale"))
        .andExpect(jsonPath("$.email").value("lale@example.com"));
  }

  @Test
  void testDeleteUser() throws Exception {
    String response = mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_5_JSON))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    User createdUser = objectMapper.readValue(response, User.class);

    mockMvc.perform(delete("/api/v1/users/" + createdUser.getId()))
        .andExpect(status().isNoContent());

    mockMvc.perform(get("/api/v1/users/" + createdUser.getId()))
        .andExpect(status().isNotFound());
  }

}
