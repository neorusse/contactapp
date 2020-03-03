package com.contactmanagercode.contactmanager.service;

import com.contactmanagercode.contactmanager.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

  Contact getContact(Integer id);

  List<Contact> getAllContacts();

  void updateContact(Contact contact);

  void addContact(String firstName, String lastName, String gender, String phone);

  // String deleteContact(Integer id);

  void saveContact(Contact contact);
}
