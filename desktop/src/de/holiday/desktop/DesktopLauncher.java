package de.holiday.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.holiday.dropgame.core.Drop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Drop HD remastered");
		config.setWindowedMode(1360, 768);
		config.setWindowIcon("icons/drop_16x16.png", "icons/drop_32x32.png", "icons/drop_128x128.png");
		new Lwjgl3Application(new Drop(), config);
	}
}
