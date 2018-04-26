package handle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandle implements InvocationHandle{

	@Override
	public void invoke(Object o, Method m) {
		
		long start = System.currentTimeMillis();
		System.out.println("开始时间: " + start);
		try {
			m.invoke(o, new Object[] {});
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("总花费时间: " + (end-start));
		
	}

}
