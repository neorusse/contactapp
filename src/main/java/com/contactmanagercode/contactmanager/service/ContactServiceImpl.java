package com.contactmanagercode.contactmanager.service;

import com.contactmanagercode.contactmanager.exception.ResourceNotFoundException;
import com.contactmanagercode.contactmanager.model.Contact;
import com.contactmanagercode.contactmanager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

  @Autowired
  private ContactRepository contactRepository;


  @Override
  public Contact getContact(Integer id) {
    Optional<Contact> userOptional = contactRepository.findById(id);
    if (userOptional == null) {
      throw new ResourceNotFoundException("User with id - " + id + "  not found");
    }
    return userOptional.get();
  }

  @Override
  public List<Contact> getAllContacts() {
    return contactRepository.findAll();
  }

  @Override
  public void updateContact(Contact contact) {
    contactRepository.save(contact);
  }

  @Override
  public void addContact(String firstName, String lastName, String gender, String phone) {
    contactRepository.save(new Contact(firstName, lastName, gender, phone));
  }

  @Override
  public void saveContact(Contact contact) {
    contactRepository.save(contact);
  }

  @Override
  public void deleteContact(Contact contact) {
      contactRepository.delete(contact);
  }
}
