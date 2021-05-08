package com.study.aop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class TestAop {
	@Resource
	private CalcService calcService;

	/**
	 * Spring4
	 * 正常结果：
	 * 我是环绕通知之前AAA
	 * *********** @Before我是前置通知MyAspect
	 * ===========>CalcServiceImpl被调用了，计算结果：5
	 * 我是环绕通知之后BBB
	 * *********** @After我是后置通知MyAspect
	 * *********** @AfterReturning我是返回后通知MyAspect
	 *
	 * 异常结果：
	 * 我是环绕通知之前AAA
	 * *********** @Before我是前置通知MyAspect
	 * *********** @After我是后置通知MyAspect
	 * *********** @AfterThrowing我是异常通知MyAspect
	 * java.lang.ArithmeticException: / by zero
	 */
	@Test
	public void testAop4() {

		System.out.println("spring版本："+SpringVersion.getVersion()+"\t\t"+"springboot版本："+SpringBootVersion.getVersion());
		//calcService.div(10, 2);
		calcService.div(10, 0);
	}

	/**
	 *
	 * 正常通知：
	 * 我是环绕通知之前AAA
	 * *********** @Before我是前置通知MyAspect
	 * ===========>CalcServiceImpl被调用了，计算结果：5
	 * *********** @AfterReturning我是返回后通知MyAspect
	 * *********** @After我是后置通知MyAspect
	 * 我是环绕通知之后BBB
	 *
	 * 异常通知：
	 * 我是环绕通知之前AAA
	 * *********** @Before我是前置通知MyAspect
	 * *********** @AfterThrowing我是异常通知MyAspect
	 * *********** @After我是后置通知MyAspect
	 *
	 * java.lang.ArithmeticException: / by zero
	 */
	@Test
	public void testAop5() {
		System.out.println("spring版本："+SpringVersion.getVersion()+"\t\t"+"springboot版本："+SpringBootVersion.getVersion());
		// calcService.div(10, 2);
		calcService.div(10, 0);

	}
}




