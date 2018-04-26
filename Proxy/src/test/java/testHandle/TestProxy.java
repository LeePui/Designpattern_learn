package testHandle;

import org.junit.Test;

import handle.Proxy;
import handle.TimeHandle;
import tank.Moveable;

public class TestProxy {
	
	@Test
	public void test1() throws Exception {
		Moveable m = (Moveable)Proxy.getInstance(Moveable.class, new TimeHandle());
	}

}
