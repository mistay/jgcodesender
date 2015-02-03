package gcode;

import main.Main;

public class Gcodesender extends Thread {

	public boolean enabled = false;

	int linenumber = 1;

	private Commands currentCommand;

	public Commands getCurrentCommand() {
		return currentCommand;
	}

	@Override
	public void run() {
		while (true) {
			// if (enabled) {

			System.out.println("Gcodesender::run()");

			Gcodereader gcodereader = Main.getInstance()._gcodereader;

			do {
				if (enabled) {
					System.out.println("Gcodesender: <- enabled");
					currentCommand = gcodereader.gCodeCommands.get(linenumber);
					currentCommand.linenumber = linenumber;
					Main.getInstance()._test.sendCommand(currentCommand);
					// Main.getInstance()._test.queryposition();
					linenumber++;
					System.out.println("size: "
							+ gcodereader.gCodeCommands.size());
					System.out.println("linenumber: " + linenumber);

					Main.getInstance()._mainform.setProgress(linenumber,
							gcodereader.gCodeCommands.size());
				} else {
					System.out.println("Gcodesender::skipping (not enabled)");
				}

				if (linenumber >= gcodereader.gCodeCommands.size()) {
					System.out.println("finished gcodes. disabling myself.");
					enabled = false;
					linenumber = 1;
				}

			} while (enabled && currentCommand != null
					&& currentCommand.executedSuccessfully);

			synchronized (this) {
				try {
					System.out.println("trying to wait()");
					wait();

					System.out.println("after wait()");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// System.out.println("wait() ing gcodesender: not");
			// wait();
		}
	}

	public synchronized void handleCommandResult() {
		System.out.println("handleCommandResult()");
		// if (currentCommand.executedSuccessfully) {
		System.out.println("executed successfully, next command ...");
		notify();
		// } else {
		// System.out.println("did not execute successfully");
		// }

	}
}
