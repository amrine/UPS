package com.logisim.ui;
import com.cburch.logisim.LogisimVersion;
import com.cburch.logisim.gui.start.Startup;

public class Main {
	public static final LogisimVersion VERSION = LogisimVersion.get(2, 7, 2);
	public static final String VERSION_NAME = VERSION.toString();
	public static final int COPYRIGHT_YEAR = 2011;

	public static void main(String[] args) {
		Startup startup = Startup.parseArgs(args);
		if (startup == null) {
			System.exit(0);
		} else {
			startup.run();
		}
	}
}
