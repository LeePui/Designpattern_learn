package handle;

import java.lang.reflect.Method;
/**
 * 用来实现具体方法的接口，给定一个类，与方法，具体实现。
 * @author LeePui
 *
 */
public interface InvocationHandle {
	
	public void invoke(Object o, Method m);
	
}
