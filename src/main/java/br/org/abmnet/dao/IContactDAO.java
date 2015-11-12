package br.org.abmnet.dao;

import java.util.List;

import br.org.abmnet.model.Contact;

public interface IContactDAO {

	List<Contact> getContacts();
	
	void deleteContact(int id);
	
	Contact saveContact(Contact contact);
	
}
