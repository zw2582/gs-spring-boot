package hello.remoting.server.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import hello.remoting.server.AccountService;

/**
 * Rmi远程调用服务配置
 */
@Configuration
public class RmiExporter {
	
	@Autowired
	AccountService accountService;

	/**
	 * 服务端暴露接口
	 * @return
	 */
	@Bean
	public RmiServiceExporter getRmiServiceExporter() {
		RmiServiceExporter rmi = new RmiServiceExporter();
		rmi.setServiceName("AccountService");
		rmi.setService(accountService);
		rmi.setServiceInterface(AccountService.class);
		rmi.setRegistryPort(1199);
		return rmi;
	}
	
	/**
	 * 客户端调用
	 * @return
	 */
	@Bean
	public AccountService getAccountServiceProxy() {
		RmiProxyFactoryBean rmiproxy = new RmiProxyFactoryBean();
		rmiproxy.setServiceUrl("rmi://localhost:1199/AccountService");
		rmiproxy.setServiceInterface(AccountService.class);
		
		return (AccountService) rmiproxy;
	}
}
