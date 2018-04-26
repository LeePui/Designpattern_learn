package testTank2;

import org.junit.Test;

import tank.Moveable;
import tank.Tank;
import tank2.Proxy;

public class TestProxy {
	
	@Test
	public void Test1() throws Exception {
		Moveable m = (Moveable)Proxy.getInstance(Moveable.class);
		m.move();
	}
	
	public static void main(String[] args) throws Exception {
		Moveable m = (Moveable)Proxy.getInstance(Moveable.class);
		m.move();
	}

}
 