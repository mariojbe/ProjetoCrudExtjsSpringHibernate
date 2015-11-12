package br.org.abmnet.web;

import br.org.abmnet.dao.ContactDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.abmnet.model.Contact;
import br.org.abmnet.service.ContactService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller - Spring
 *
 * @author Mário Jorge
 *
 *
 */
@Controller
public class ContactController {

    private ContactService contactService;
    private ContactDAO contactDAO;

    @RequestMapping(value = "/contact/view.action")
    public @ResponseBody
    Map<String, ? extends Object> view() throws Exception {

        try {

            List<Contact> contacts = contactService.getContactList();

            return getMap(contacts);

        } catch (Exception e) {

            return getModelMapError("Erro ao recuperar contatos do banco de dados.");
        }
    }

    @RequestMapping(value = "/contact/create.action")
    public @ResponseBody
    Map<String, ? extends Object> create(@RequestParam Object data) throws Exception {

        try {

            List<Contact> contacts = contactService.create(data);

            return getMap(contacts);

        } catch (Exception e) {

            return getModelMapError("Erro ao tentar criar contato.");
        }
    }

    @RequestMapping(value = "/contact/update.action")
    public @ResponseBody
    Map<String, ? extends Object> update(@RequestParam Object data) throws Exception {
        try {

            List<Contact> contacts = contactService.update(data);

            return getMap(contacts);

        } catch (Exception e) {

            return getModelMapError("Erro ao tentar atualizar contato.");
        }
    }

    public ModelAndView delete(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        try {

            String data = request.getParameter("data");

            contactService.delete(data);

            Map<String, Object> modelMap = new HashMap<String, Object>(3);
            modelMap.put("success", true);

            return new ModelAndView("jsonView", modelMap);

        } catch (Exception e) {

            return (ModelAndView) getModelMapError("Error trying to delete contact.");
        }
    }
    /*
     @RequestMapping(value = "/contact/delete.action", method = RequestMethod.POST)
     public @ResponseBody
     Map<String, ? extends Object> delete(@RequestParam Object data) throws Exception {

     try {

     contactService.delete(data);;

     Map<String, Object> modelMap = new HashMap<>(3);
     modelMap.put("success", true);

     return modelMap;

     } catch (Exception e) {

     return getModelMapError("Erro ao tentar excluir contatos.");
     }
     }*/

    /**
     * Generates modelMap to return in the modelAndView
     *
     * @param contacts
     * @return
     */
    private Map<String, Object> getMap(List<Contact> contacts) {

        Map<String, Object> modelMap = new HashMap<String, Object>(3);
        modelMap.put("total", contacts.size());
        modelMap.put("data", contacts);
        modelMap.put("success", true);

        return modelMap;
    }

    /**
     * Generates modelMap to return in the modelAndView in case of exception
     *
     * @param msg message
     * @return
     */
    private Map<String, Object> getModelMapError(String msg) {

        Map<String, Object> modelMap = new HashMap<String, Object>(2);
        modelMap.put("message", msg);
        modelMap.put("success", false);

        return modelMap;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

}
