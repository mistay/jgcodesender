package millingmachines;

import communication.Serial;

public class Shapeoko2 {

	private Serial serial = null;

	public Shapeoko2() {
		serial = new Serial();
	}

	public void G0(int x, int y) {
		serial.enqueue("G0 X" + x + " Y" + y);
	}

}
