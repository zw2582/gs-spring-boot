package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import hello.model.Book;
import hello.model.Person;
import hello.model.SpringUtil;
import hello.repository.PersonRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
    
    @RequestMapping("/test-aop")
    public void testAop() {
    	Book book = SpringUtil.getBean(Book.class);
    	/*String result = book.toString();
    	System.out.println(result);*/
    	book.go();
    	System.out.println();
    	book.toString();
    	/*Book book2 = new Book();
    	BeanWrapperImpl beanWrapperImpl = new BeanWrapperImpl(book2);
    	
    	HashMap<String, String> data = new HashMap<>();
    	data.put("id", "1");
    	data.put("name", "caca");
    	beanWrapperImpl.setPropertyValues(data);
    	System.out.println(book2);*/
    }
    
    @RequestMapping("/test-exception")
    public void testException() {
//    	throw new RestClientException("caca");
    	throw new IllegalArgumentException("没参数");
    }
    
}
