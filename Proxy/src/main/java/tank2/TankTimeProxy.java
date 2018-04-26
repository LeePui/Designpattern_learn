package tank2;

import tank.Moveable;
import java.lang.reflect.Method;

/**
 * 该类想实现以下计算Tank的move方法使用的时间
 * 这种方式叫做聚合
 * @author LeePui
 *
 */
public class TankTimeProxy implements tank.Moveable {

   handle.InvocationHandle h;
	
	public TankTimeProxy(handle.InvocationHandle h) {
		this.h= h;
	}

	@Override
	public void move(){
		try{
			Method m = tank.Moveable.class.getMethod("move");
			h.invoke(this, m);
		}catch(Exception e){e.printStackTrace();}
	}
}