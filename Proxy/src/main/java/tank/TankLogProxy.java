package tank;

/**
 * 该类想实现以下计算Tank的move方法使用的时间
 * 这种方式叫做聚合
 * @author LeePui
 *
 */
public class TankLogProxy implements Moveable{

	public Moveable t;
	
	public TankLogProxy(Moveable t) {
		super();
		this.t= t;
	}

	@Override
	public void move() {
		System.out.println("开始记录日志");
		t.move();
		System.out.println("结束记录日志");
	}

}
