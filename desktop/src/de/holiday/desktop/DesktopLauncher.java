package de.holiday.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.holiday.dropgame.core.Drop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Drop HD remastered";
		config.width = 1360;
		config.height = 768;
		config.addIcon("icons/drop_16x16.png", Files.FileType.Internal);
		config.addIcon("icons/drop_32x32.png", Files.FileType.Internal);
		config.addIcon("icons/drop_128x128.png", Files.FileType.Internal);
		new LwjglApplication(new Drop(), config);
	}
}
