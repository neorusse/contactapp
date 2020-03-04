package com.contactmanagercode.contactmanager.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.contactmanagercode.contactmanager.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
@RunWith(SpringRunner.class)
public class ContactControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ContactService contactService;

  // unit testing get all contacts route
  @Test
  public void testShowAllContacts() throws Exception {

    mockMvc.perform(get("/viewcontacts"))
      .andExpect(status().is2xxSuccessful())
      .andExpect(view().name("viewcontacts"))
      .andExpect(model().attributeExists("contacts"));
  }

  // unit testing show add contact form route
  @Test
  public void testShowContactPage() throws Exception {

    mockMvc.perform(get("/contact"))
      .andExpect(status().is2xxSuccessful())
      .andExpect(view().name("contact"))
      .andExpect(model().attributeExists("contact"));
  }

  // unit testing register a contact route
  @Test
  public void testSaveRegistration() throws Exception {
    mockMvc.perform(post("/contact")
      .param("firstName", "Russell")
      .param("lastName", "Nyorere")
      .param("gender", "M")
      .param("phone", "08068908852"))
      .andExpect(status().is3xxRedirection())
      .andExpect(redirectedUrl("/viewcontacts"));
  }

}
