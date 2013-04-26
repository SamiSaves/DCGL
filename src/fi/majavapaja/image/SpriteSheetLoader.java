package fi.majavapaja.image;

import java.awt.image.BufferedImage;

import fi.majavapaja.graphics.ImageManipulation;

public class SpriteSheetLoader {

	// TODO: add auto width and height detection!
	public static SpriteSheet loadSpriteSheet(String path) {
		return null;
	}

	public static SpriteSheet loadSpriteSheet(String path, int width, int height) {
		BufferedImage img = null;
		int counter = 0;
		
		img = ImageManipulation.loadImage(path);
		
		int w = img.getWidth() / width;
		int h = img.getHeight() / height;
		BufferedImage[] sprites = new BufferedImage[w * h];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				sprites[counter] = img.getSubimage(j * width, i * height, width, height);
				counter++;
			}
		}
		return new SpriteSheet(sprites);
	}
}
