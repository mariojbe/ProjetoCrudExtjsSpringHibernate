package br.org.abmnet.test;

import br.org.abmnet.dao.ContactDAO;
import br.org.abmnet.model.Contact;
import javax.transaction.Transactional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Mário Jorge
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file*:src/main/webapp/WEB-INF/spring/app-config*.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ContatoTest {
    
    private HibernateTemplate hibernateTemplate;

    @Autowired(required = false)
    private ContactDAO contactDAO;

    @Test
    public void testSave() {
        /*
         Contact contact = new Contact();
         contact.setName("test");
         contact.setPhone("(00)9999-9999");
         contact.setEmail("test@test.com");
         contactDAO.saveContact(contact);
         */
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testUpdate() {
    }
}
