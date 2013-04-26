package fi.majavapaja.image;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound playerHurt = new Sound("/boom.wav");

	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
			try {
				playerHurt.play();
				Thread.sleep(10);
				playerHurt.play();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}