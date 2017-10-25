package hello.remoting.server.exporter;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.client.RestTemplate;

import hello.remoting.server.Account;

/**
 * restTemplate配置
 * @author Administrator
 *
 */
public class RestTemplateConfigure {
	
	private RestTemplate rt;

	@Bean
	public RestTemplate getRestTemplate() {
		rt = new RestTemplate();
		
		return rt;
	}
	
	/**
	 * exChange可以用来添加头信息和获取头信息，利用httpEntity
	 */
	public void testExchange() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> exchange = rt.exchange("", HttpMethod.GET, requestEntity, String.class);
		exchange.getHeaders();
		String body = exchange.getBody();
	}
	
	@Test
	public void testJson() {
		Account account = new Account();
		MappingJacksonValue value = new MappingJacksonValue(account);
		System.out.println(value.getValue());
	}
}
