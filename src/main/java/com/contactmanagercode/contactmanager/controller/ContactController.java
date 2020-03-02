package com.contactmanagercode.contactmanager.controller;

import com.contactmanagercode.contactmanager.dao.ContactDAO;
import com.contactmanagercode.contactmanager.exception.DuplicatePhoneException;
import com.contactmanagercode.contactmanager.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {

  private final Logger logger = LoggerFactory.getLogger(ContactController.class);

  @Autowired
  private ContactDAO contactDao;

  // Get - Display all contacts
  @RequestMapping(value="/viewcontacts")
  public ModelAndView showAllContacts() {

    logger.info("HTTP GET request received at /viewcontacts url");

    List<Contact> contactlist = contactDao.findAll();
    return new ModelAndView("viewcontacts","list", contactlist);
  }

  // GET - display add contact form
  @RequestMapping(value="/addcontactform",method= RequestMethod.GET)
  public String showContacts(ModelMap model) {

    logger.info("HTTP GET request received at /addcontactform url");

    Contact contact = new Contact();
    model.addAttribute("contact", contact);
    return "addcontactform";
  }

  // POST - save a contact
  @RequestMapping(value="/savecontact",method=RequestMethod.POST)
  public ModelAndView saveRegistration(@Valid Contact contact, BindingResult bindingResult) {

    logger.info("HTTP POST request received at /savecontact url");

    if(bindingResult.hasErrors()) {
      return new ModelAndView("addcontactform");
    }

    try {
      contactDao.addOrUpdateContact(contact);
    } catch (DuplicatePhoneException e) {
      bindingResult.rejectValue("phone", "", "This phone number already exist");
      return new ModelAndView("addcontactform");
    }

    RedirectView redirect = new RedirectView();
    redirect.setUrl("/viewcontacts");

    return new ModelAndView(redirect);

  }

  // delete a contact
  @RequestMapping(value="/deletecontact/{id}",method=RequestMethod.GET)
  public ModelAndView delete(@PathVariable int id) {

    logger.info("HTTP DELETE request received at /deletecontact url");

    Contact contact = contactDao.getOne(id);
    contactDao.delete(contact);
    return new ModelAndView("redirect:/viewcontacts");
  }

}
