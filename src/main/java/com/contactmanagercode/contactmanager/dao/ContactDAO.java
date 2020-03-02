package com.contactmanagercode.contactmanager.dao;

import com.contactmanagercode.contactmanager.exception.DuplicatePhoneException;
import com.contactmanagercode.contactmanager.model.Contact;
import com.contactmanagercode.contactmanager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactDAO {

  @Autowired
  ContactRepository contactRepository;

  // save a contact
  public Contact save(Contact contact) {
    return contactRepository.save(contact);
  }

  // retrieve all contacts
  public List<Contact> findAll(){
    return contactRepository.findAll();
  }

  // get a contact by id
  public Contact getOne(Integer id) {
    return contactRepository.getOne(id);
  }

  // delete a contact
  public void delete(Contact contact) {
    contactRepository.delete(contact);
  }

  // adds new contact or update existing contact
  public void addOrUpdateContact(Contact contact) throws DuplicatePhoneException {

    Contact existingContact = contactRepository.getOne(contact.getId());

    if (existingContact == null) {

      if (contactRepository.findByPhone(contact.getPhone()) != null) {
        throw new DuplicatePhoneException();
      }

      contactRepository.save(contact);

    } else {
      existingContact.setFirstName(contact.getFirstName());
      existingContact.setLastName(contact.getLastName());
      existingContact.setGender(contact.getGender());
      existingContact.setPhone(contact.getPhone());
    }
  }

}
