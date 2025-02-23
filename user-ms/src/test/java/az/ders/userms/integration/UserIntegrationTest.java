package az.ders.userms.integration;

import static az.ders.userms.mock.MockUser.INVALID_ID;
import static az.ders.userms.mock.MockUser.USER_1_JSON;
import static az.ders.userms.mock.MockUser.USER_2_JSON;
import static az.ders.userms.mock.MockUser.USER_ID_1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import az.ders.userms.entity.User;
import az.ders.userms.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

  @Autowired
  private UserRepository userRepository;


  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

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
            .content(USER_1_JSON))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    User createdUser = objectMapper.readValue(response, User.class);

    mockMvc.perform(get("/api/v1/users/" + createdUser.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(createdUser.getId()))
        .andExpect(jsonPath("$.name").value("Ali"))
        .andExpect(jsonPath("$.email").value("ali@example.com"));
  }

  @Test
  void testGetUserByInvalidId() throws Exception {
    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_1_JSON))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/v1/users/" + INVALID_ID))
        .andExpect(status().isNotFound());
  }

  @Test
  void testCreateUser() throws Exception {
    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_1_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Ali"))
        .andExpect(jsonPath("$.email").value("ali@example.com"));
  }

  @Test
  void testDeleteUser() throws Exception {
    String response = mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_1_JSON))
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
