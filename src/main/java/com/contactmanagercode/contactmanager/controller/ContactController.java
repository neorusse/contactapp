package com.contactmanagercode.contactmanager.controller;

import com.contactmanagercode.contactmanager.model.Contact;
import com.contactmanagercode.contactmanager.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ContactController {

  private final Logger logger = LoggerFactory.getLogger(ContactController.class);

  @Autowired
  private ContactService contactService;

  // Get - Display all contacts
  @RequestMapping(value="/viewcontacts", method = RequestMethod.GET)
  public String showAllContacts(ModelMap model) {

    logger.info("HTTP GET request received at /viewcontacts url");

    model.put("contacts", contactService.getAllContacts());

    return "viewcontacts";
  }

  // GET - display add contact form
  @RequestMapping(value="/contact", method = RequestMethod.GET)
  public String showContactPage(Model model) {

    logger.info("HTTP GET request received at /contact url");

    model.addAttribute("contact", new Contact());

    return "contact";
  }

  // POST - save a contact
  @RequestMapping(value="/contact", method=RequestMethod.POST)
  public String saveRegistration(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) {

    logger.info("HTTP POST request received at /contact url");

    if(bindingResult.hasErrors()) {
      return "contact";
    }

    contactService.saveContact(contact);

    return "redirect:/viewcontacts";
  }

  // Display Update Contact Page
  @RequestMapping(value = "/update-contact/{id}", method = RequestMethod.GET)
  public String showUpdateTodoPage(@PathVariable Integer id, ModelMap model) {
    Contact contact = contactService.getContact(id);
    model.put("contact", contact);
    return "contact";
  }

  // Update Contact
  @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
  public String updateContact(ModelMap model, @Valid Contact contact, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "contact";
    }

    contactService.updateContact(contact);
    return "redirect:/viewcontacts";
  }

  // Delete contact route
  @RequestMapping(value = "/delete-contact/{id}", method = RequestMethod.GET)
  public String deleteTodo(@PathVariable Integer id) {
    Contact contact = contactService.getContact(id);
    contactService.deleteContact(contact);

    return "redirect:/viewcontacts";
  }

}
