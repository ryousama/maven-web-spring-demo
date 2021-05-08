package com.study.aop;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
 * @author sakur
 *
 */
//@Service
@Component(value = "calcService")
public class CalcServiceImpl implements CalcService{

	public int div(int x, int y) {
		int result = x/y;
		System.out.println("===========>CalcServiceImpl被调用了，计算结果："+result);
		return result;
	}

}
