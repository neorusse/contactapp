package com.contactmanagercode.contactmanager.test;

import com.contactmanagercode.contactmanager.model.Contact;
import com.contactmanagercode.contactmanager.repository.ContactRepository;
import com.contactmanagercode.contactmanager.service.ContactService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

  @Mock
  private ContactRepository contactRepositoryMock;

  @InjectMocks
  private ContactService contactService;


  @Test
  public void testGetContactsReturnsAllItemsFromRepository() {

    // Arrange
    Contact firstContact = new Contact("Russell", "Nyorere", "M" , "08068908853" );
    Contact secondContact = new Contact("Rose", "Kupa", "F" , "08020751187");

    List<Contact> mockContacts = Arrays.asList(firstContact, secondContact);

    when(contactRepositoryMock.findAll()).thenReturn(mockContacts);

    // Act
    List<Contact> result = contactService.getAllContacts();

    // Assert
    Assert.assertEquals(2, result.size());
    Assert.assertEquals("Russell", result.get(0).getFirstName());
    Assert.assertEquals("Rose", result.get(1).getFirstName());
  }

}
