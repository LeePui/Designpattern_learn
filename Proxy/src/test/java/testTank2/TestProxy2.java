package testTank2;

import org.junit.Test;

import tank.Moveable;
import tank.Tank;
import tank2.Proxy2;

public class TestProxy2 {
	
	@Test
	public void test1() throws Exception {
		Moveable m = (Moveable)Proxy2.getInstance(Moveable.class);
		m.move();
	}

}
