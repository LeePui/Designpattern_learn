package testHandle;

import org.junit.Test;

import handle.Proxy;
import handle.TimeHandle;
import tank.Moveable;
import tank.Tank;

public class TestProxy {
	
	@Test
	public void test1() throws Exception {
		Tank t = new Tank();
		TimeHandle timeHandle = new TimeHandle(t);
		Moveable m = (Moveable)Proxy.getInstance(Moveable.class, timeHandle);
		m.move();
		   
	}

}
