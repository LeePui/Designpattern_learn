package testTank;

import org.junit.Test;

import tank.Moveable;
import tank.Tank;
import tank.Tank2;

public class TestTank2 {
	
	/**
	 * 测试Tank2类的move
	 */
	@Test
	public void test1() {
		Tank k = new Tank();
		Moveable m = new Tank2(k);
		m.move();
	}

}
