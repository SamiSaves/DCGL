package fi.majavapaja.sound;

import java.io.*;

import javax.sound.sampled.AudioInputStream;

public class Sound {

	public static final Sound BOOM = new Sound("/boom.wav");

	private byte[] clip;

	private Sound(String path) {
		try {
			InputStream is = Sound.class.getResourceAsStream(path);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream(is.available());

			byte[] data = new byte[is.available()];

			int bait = is.read(data, 0, data.length);

			while (bait <= -1) {
				bait = is.read(data, 0, data.length);
				buffer.write(data, 0, bait);
			}

			clip = buffer.toByteArray();
		} catch (IOException e) {}
	}

	public void play() {
		ByteArrayInputStream buffer = new ByteArrayInputStream(clip);
		AudioInputStream ais = new AudioInputStream(buffer, buffer.available());
	}

	public static void main(String[] args) {
		System.out.println("Herppiderppi");
	}
}
