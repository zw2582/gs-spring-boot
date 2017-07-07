package hello.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Advices {
	
	@Before("execution(* hello.model.Book.*(..))")
    public void before(JoinPoint jp){
        System.out.println("----------前置通知----------");
//        System.out.println(jp.getSignature().getName());
    }
    
    @After("execution(* hello.model.Book.*(..))")
    public void after(JoinPoint jp){
        System.out.println("----------最终通知----------");
    }
    
    @AfterReturning(pointcut="execution(* hello.model.Book.*(..))",
    		returning="retVal")
    public void afterReturn(String retVal) {
    	System.out.println("----------返回正常结果-------");
    }
}
