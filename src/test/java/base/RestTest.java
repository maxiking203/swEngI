package base;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import base.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RestTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private CodeRepositroy repo;

  @Test
  public void checkControllerAndRepo() {
    try {
      
      mockMvc.perform(post("/board/ABCD1234").content(
          "{\"emailAddr\":\"test@hm.edu\",\"title\":\"Testtile\","
          + "\"postText\":\"Weiter Test über Postman, um die Response zu prüfen\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
      
      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
      .andExpect(content().string(equalTo("[]")));
      
      mockMvc.perform(put("/board/getCode/test@hm.edu").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
      .andExpect(content().string(equalTo("true")));
      
      mockMvc.perform(put("/board/getCode/test@nix.edu").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
      .andExpect(content().string(equalTo("false")));
      
      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo("[]")));
      
      Code code = new Code("test@hm.edu");
      code.setCode("ABCD1234");
      repo.save(code);
      
      mockMvc.perform(post("/board/ABCD1234").content(
          "{\"emailAddr\":\"test@hm.edu\",\"title\":\"Testtile\","
          + "\"postText\":\"Weiter Test über Postman, um die Response zu prüfen\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

      code = new Code("test@hm.edu");
      code.setCode("ABCD1234");
      repo.save(code);
      
      mockMvc.perform(post("/board/ABCD1234").content(
          "{\"emailAddr\":\"test@hm.edu\",\"title\":\"Testtile2\","
          + "\"postText\":\"Zweiter Test über Postman, um die Response zu prüfen\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo(
              "[{\"listIndex\":1,\"emailAddr\":\"test@hm.edu\",\"title\":\"Testtile\","
              + "\"postText\":\"Weiter Test über Postman, um die Response zu prüfen\",\"tagList\":[\"#Test\"],\"category\":\"Test\"},"
                  + "{\"listIndex\":2,\"emailAddr\":\"test@hm.edu\",\"title\":\"Testtile2\","
                  + "\"postText\":\"Zweiter Test über Postman, um die Response zu prüfen\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}]")));

      mockMvc.perform(put("/board/1")
          .content(
              "{\"emailAddr\":\"testC@hm.edu\",\"title\":\"TesttileChanged\",\"postText\":\"Changed\",\"tagList\":[\"#Changed\"],\"category\":\"Test\"}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
      
      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo(
              "[{\"listIndex\":1,\"emailAddr\":\"testC@hm.edu\",\"title\":\"TesttileChanged\","
              + "\"postText\":\"Changed\",\"tagList\":[\"#Changed\"],\"category\":\"Test\"},"
                  + "{\"listIndex\":2,\"emailAddr\":\"test@hm.edu\",\"title\":\"Testtile2\","
                  + "\"postText\":\"Zweiter Test über Postman, um die Response zu prüfen\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}]")));
      
      mockMvc.perform(delete("/board/2"));

      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo(
              "[{\"listIndex\":1,\"emailAddr\":\"testC@hm.edu\",\"title\":\"TesttileChanged\","
              + "\"postText\":\"Changed\",\"tagList\":[\"#Changed\"],\"category\":\"Test\"}]")));

      mockMvc.perform(put("/board/2")
          .content(
              "{\"emailAddr\":\"test@hm.de\",\"postText\":\"Changed\",\"tagList\":[\"#Changed\"]}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo(
              "[{\"listIndex\":1,\"emailAddr\":\"testC@hm.edu\",\"title\":\"TesttileChanged\","
              + "\"postText\":\"Changed\",\"tagList\":[\"#Changed\"],\"category\":\"Test\"}]")));
      
      mockMvc.perform(delete("/board/deleteAll"));

      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo("[]")));

      code = new Code("test@hm.edu");
      code.setCode("ABCD1234");
      repo.save(code);
      
      mockMvc.perform(post("/board/ABCD1234").content(
          "{\"emailAddr\":\"test@test.de\",\"title\":\"TesttileChanged\",\"postText\":\"Falsche Email. Post wird nicht erstellt\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo("[]")));

      code = new Code("test@calpoly.de");
      code.setCode("ABCD1234");
      repo.save(code);
      
      mockMvc.perform(post("/board/ABCD1234").content(
          "{\"emailAddr\":\"test@calpoly.de\",\"title\":\"TesttileChanged\",\"postText\":\"Weiter Test für calpoly Mail adresse\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}")
          .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

      mockMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
          .andExpect(content().string(equalTo(
              "[{\"listIndex\":3,\"emailAddr\":\"test@calpoly.de\",\"title\":\"TesttileChanged\",\"postText\":\"Weiter Test für calpoly Mail adresse\",\"tagList\":[\"#Test\"],\"category\":\"Test\"}]")));

      mockMvc.perform(delete("/board/deleteAll"));
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
