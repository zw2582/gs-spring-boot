package hello;

import org.springframework.web.bind.annotation.RestController;

import hello.model.Person;
import hello.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	@Autowired
	MessageConfigProperties messageProperties;
	
	@Autowired
	PersonRepository personRepository;
    
    @RequestMapping("/message")
    public String index() {
    	String message = messageProperties.getGreetings();
    	
        return message;
    }
    
    @RequestMapping("/test-msg")
    public String testMsg(@RequestBody String msg) {
    	System.out.println(msg);
    	return msg;
    }
    
    @RequestMapping("/test-person")
    public Person testPerson(@RequestBody Person person) {
    	System.out.println(person);
    	return person;
    }
    
    @RequestMapping("/test-get-person")
    public void testGetPerson() {
    	Person person = personRepository.findOne(1L);
    	System.out.println(person.getParent());
//    	System.out.println(person.getParent());
//    	return person;
    }
    
}
