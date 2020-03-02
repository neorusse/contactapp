package com.contactmanagercode.contactmanager.repository;

import com.contactmanagercode.contactmanager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

  List<Contact> findByPhone(String phone);
}
