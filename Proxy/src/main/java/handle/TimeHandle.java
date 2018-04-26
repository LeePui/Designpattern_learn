package handle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandle implements InvocationHandle{

	private Object target;

	public TimeHandle(Object target) {
		this.target = target;
	}

	/**
	 * 这个是代理的类，所以应该先加代理的逻辑语句，然后调用被代理的类的方法。
	 */
	@Override
	public void invoke(Object o, Method m) {
		
		long start = System.currentTimeMillis();
		System.out.println("开始时间: " + start);
		try {
			m.invoke(target);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("总花费时间: " + (end-start));
		
	}

}
