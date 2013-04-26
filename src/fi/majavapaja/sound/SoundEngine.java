package fi.majavapaja.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing codes from the game codes. 1. Define all your sound effect names and the associated wave file. 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play(). 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the sound files, so that the play is not paused while loading the file for the first time. 4. You can use the static variable SoundEffect.volume to mute the sound.
 */

// TODO READ THIS OOVARR
public enum SoundEngine {
	EAT("/boom.wav"), // explosion
	LOL("/lol.wav");

	// Nested class for specifying volume
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}

	public static Volume volume = Volume.LOW;

	// Each sound effect has its own clip, loaded with its own sound file.
	private Clip clip;

	// Constructor to construct each element of the enum with its own sound file.
	SoundEngine(String soundFileName) {
		try {
			// Use URL (instead of File) to read from disk and JAR.
			InputStream inputStream = this.getClass().getResourceAsStream(soundFileName);
			// Set up an audio input stream piped from the sound file.

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
			// Get a clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioInputStream);
			// clip.drain(); jos tulee ongelmaa jonkun kanssa
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	// Play or Re-play the sound effect from the beginning, by rewinding.
	public void play() {
		if (volume != Volume.MUTE) {
			FloatControl vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			if (volume == Volume.LOW) {
				vol.setValue(-5);
			} else if (volume == Volume.HIGH) {
				vol.setValue(5);
			}
			if (clip.isRunning()) clip.stop(); // Stop the player if it is still running
			clip.setFramePosition(0); // rewind to the beginning
			clip.start(); // Start playing
		}
	}

	// Optional static method to pre-load all the sound files.
	static void init() {
		values(); // calls the constructor for all the elements
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println("Start");
			volume = Volume.HIGH;

			try {
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(100);
				LOL.play();
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.println("End");

			System.out.println("Start");
			volume = Volume.LOW;
			EAT.play();
			LOL.play();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.println("End");
		}
	}
}