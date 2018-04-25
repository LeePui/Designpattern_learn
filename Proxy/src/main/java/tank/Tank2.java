package tank;

/**
 * 该类想实现以下计算Tank的move方法使用的时间
 * 这种方式叫做聚合
 * @author LeePui
 *
 */
public class Tank2 implements Moveable{

	public Tank t;
	
	public Tank2(Tank t) {
		super();
		this.t= t;
	}

	@Override
	public void move() {
		long start = System.currentTimeMillis();
		t.move();
		long end = System.currentTimeMillis();
		System.out.println("Tank2 time: " + (end-start));
	}

}
