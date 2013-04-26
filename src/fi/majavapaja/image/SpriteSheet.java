package fi.majavapaja.image;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage[] sprites;

	public SpriteSheet(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public BufferedImage getSprite(int id) {
		if (id >= sprites.length || id < 0) return null;
		return sprites[id];
	}
}
