package djordje.wap3m.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import djordje.wap3m.model.Entry;
import djordje.wap3m.model.EntryDAO;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author djordje
 */

@Controller
public class MyController {

    private static EntryDAO entryDao = new EntryDAO();
    private static ObjectMapper om = new ObjectMapper();
    
    // landing page
    @RequestMapping("/")
    public String landingPage() {
        return "index";
    }
    
    // init GET
    @RequestMapping(value="entries", method=RequestMethod.GET)
    @ResponseBody
    public String doGet() throws JsonProcessingException {
        return (om.writeValueAsString(entryDao.getAllEntries()));        
    }
    
    // POST - add new entry
    @RequestMapping(value="new_entry", method=RequestMethod.POST)
    @ResponseBody
    public void doPost(@RequestParam(value="title", required=true) String title,@RequestParam(value="txt", required=false) String text) {        
         if (title != null) {
            entryDao.addNewEntry(title, text);
        } 
    }
    
    // DELETE selected entry
    @RequestMapping(value="delete", method=RequestMethod.DELETE)
    @ResponseBody
    public void doDelete(@RequestParam(value="entryId", required=true) String id) {      
        if (id != null) {
            entryDao.removeEntry(id);
        }      
    }
    
}
