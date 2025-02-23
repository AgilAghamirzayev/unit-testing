package az.ders.userms.controller;

import static az.ders.userms.exception.constants.ExceptionConstants.NOT_FOUND_EXCEPTION;
import static az.ders.userms.mock.MockUser.INVALID_ID;
import static az.ders.userms.mock.MockUser.USER_1_JSON;
import static az.ders.userms.mock.MockUser.USER_ID_1;
import static az.ders.userms.mock.MockUser.USER_RESPONSE_1;
import static az.ders.userms.mock.MockUser.USER_RESPONSE_LIST;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import az.ders.userms.exception.AppException;
import az.ders.userms.model.request.UserRequest;
import az.ders.userms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  @Test
  void testGetAllUsers() throws Exception {
    when(userService.getAllUsers()).thenReturn(USER_RESPONSE_LIST);

    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(2)))
        .andExpect(jsonPath("$[0].name", is("Ali")))
        .andExpect(jsonPath("$[1].name", is("Vali")));
  }

  @Test
  void testGetUserById() throws Exception {
    when(userService.getUserById(USER_ID_1)).thenReturn(USER_RESPONSE_1);

    mockMvc.perform(get("/api/v1/users/{id}", USER_ID_1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Ali")));
  }

  @Test
  void getUserById_NotFound() throws Exception {
    when(userService.getUserById(INVALID_ID))
        .thenThrow(new AppException("by id: " + INVALID_ID, NOT_FOUND_EXCEPTION));

    mockMvc.perform(get("/api/v1/users/{id}", INVALID_ID))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorMessage").value("Not found by id: " + INVALID_ID));
  }

  @Test
  void testCreateUser() throws Exception {
    when(userService.createUser(any(UserRequest.class))).thenReturn(USER_RESPONSE_1);

    mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(USER_1_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Ali")))
        .andExpect(jsonPath("$.email", is("ali@example.com")));
  }

  @Test
  void testDeleteUser() throws Exception {
    doNothing().when(userService).deleteUser(USER_ID_1);

    mockMvc.perform(delete("/api/v1/users/{id}", USER_ID_1))
        .andExpect(status().isNoContent());
  }
}