package testTank;

import org.junit.Test;

import tank.Moveable;
import tank.Tank;
import tank.TankLogProxy;
import tank.TankTimeProxy;

public class TestTimeLogProxy {
	
	/**
	 * 测试使用实现接口的方式嵌套记录时间与日志的方法
	 * move方法外面嵌套了一层日志，日志外面嵌套了一层时间
	 */
	@Test
	public void Test1() {
		Moveable k  =new Tank();
		TankLogProxy tlp = new TankLogProxy(k);
		TankTimeProxy ttp = new TankTimeProxy(tlp);
		ttp.move();
	}
	
	/**
	 * move方法外面嵌套了一层时间，时间外面嵌套日志
	 */
	@Test
	public void Test2() {
		Moveable k  =new Tank();
		TankTimeProxy ttp = new TankTimeProxy(k);
		TankLogProxy tlp = new TankLogProxy(ttp);
		tlp.move();
		
		
	}

}
