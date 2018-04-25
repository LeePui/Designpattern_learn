package tank;

import java.util.Random;

public class Tank implements Moveable{

	@Override
	public void move() {
		System.out.println("Tank Class move Method in...");
		try {
			Thread.sleep(new Random().nextInt(10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
