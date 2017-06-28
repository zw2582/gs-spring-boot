package hello;

import org.springframework.web.bind.annotation.RestController;

import com.model.PUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	@Autowired
	MessageConfigProperties messageProperties;
    
    @RequestMapping("/")
    public String index() {
    	String message = messageProperties.getGreetings();
    	
        return message;
    }
    
    @RequestMapping("/userid")
    public int userId() {
    	PUser user = new PUser();
    	return user.id;
    }
    
}
