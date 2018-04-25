package tank;

/**
 * 该类想实现以下计算Tank的move方法使用的时间
 * 这种方式叫做聚合
 * @author LeePui
 *
 */
public class TankTimeProxy implements Moveable{

	public Moveable t;
	
	public TankTimeProxy(Moveable t) {
		super();
		this.t= t;
	}

	@Override
	public void move() {
		long start = System.currentTimeMillis();
		System.out.println("开始时间: " + start);
		t.move();
		long end = System.currentTimeMillis();
		System.out.println("总花费时间: " + (end-start));
	}

}
