package br.org.abmnet.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.org.abmnet.model.Contact;
import java.util.ArrayList;

/**
 * Contact DAO class.
 *
 * @author Mário Jorge
 *
 *
 */
@Repository
public class ContactDAO implements IContactDAO {

    private HibernateTemplate hibernateTemplate;
    private static List<Contact> contacts = new ArrayList<Contact>();

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
     * Get List of contacts from database
     *
     * @return list of all contacts
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> getContacts() {
        return hibernateTemplate.find("from Contact");
    }

    /**
     * Delete a contact with the id passed as parameter
     *
     * @param id
     */
    @Override
    public void deleteContact(int id) {
        Object data = hibernateTemplate.load(Contact.class, id);
        hibernateTemplate.delete(data);
    }

    /**
     * Create a new Contact on the database or Update contact
     *
     * @param contact
     * @return contact added or updated in DB
     */
    @Override
    public Contact saveContact(Contact contact) {
        hibernateTemplate.saveOrUpdate(contact);
        return contact;
    }

    @Override
    public boolean removeContact(Contact contact) {
        return contacts.remove(contact);
    }

}
