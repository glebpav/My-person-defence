package ru.mephi.lab;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mephi.lab.MyGdxGame;

import static ru.mephi.lab.GameSettings.SCREEN_HEIGHT;
import static ru.mephi.lab.GameSettings.SCREEN_WIDTH;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
		config.setTitle("My person defense");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
