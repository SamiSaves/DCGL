package fi.majavapaja.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Stores Font and Color also draws strings with their combination.
 * 
 * @LastUpdate 22.4.2013 22:30
 * @author Doublecaster
 *
 */

public class AdvancedFont {
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int CENTER = 3;
	public static final int ABSOLUTE_CENTER = 4;
	public static final int BOTTOM_LEFT = 5;

	private static Font defaultFont = new Font(Font.MONOSPACED, Font.PLAIN, 16);
	private static Color defaultColor = Color.BLACK;

	private Font font;
	private Color color;

	private FontMetrics fm;
	private int fontHeight;
	private int fontDescent;

	private int lineSpace = 2;

	public AdvancedFont(Font font, Color color) {
		this.font = font;
		this.color = color;

		if (font == null) this.font = defaultFont;
		if (color == null) this.color = defaultColor;

		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		fm = img.createGraphics().getFontMetrics(this.font);

		fontHeight = fm.getHeight();
		fontDescent = fm.getDescent();
	}

	public void drawString(Graphics g, String string, int x, int y, int alignment) {
		g.setFont(font);
		g.setColor(color);
		
		String[] text = string.split("\n");

		for (int i = 0; i < text.length; i++) {
			Point p = setupAlignment(x, y, alignment, text[i]);
			g.drawString(text[i], p.x, p.y + i * fontHeight + lineSpace);
		}
	}

	private Point setupAlignment(int x, int y, int alignment, String s) {
		int fontWidth = fm.stringWidth(s);

		if (alignment == CENTER) {
			x -= fontWidth / 2;
			y += fontHeight / 2;
		} else if (alignment == ABSOLUTE_CENTER) {
			x -= fontWidth / 2;
			y += fontHeight / 2 - fontDescent;
		} else if (alignment == RIGHT) {
			y += fontHeight / 2;
			x -= fontWidth;
		} else if(alignment == BOTTOM_LEFT){
			y -= fontHeight / 2;
		} else {
			y += fontHeight / 2;
		}
		return new Point(x, y);
	}

	public void changeFont(Font newFont) {
		font = newFont;
		if (font == null) this.font = defaultFont;
	}

	public void changeColor(Color newColor) {
		color = newColor;
		if (color == null) this.color = defaultColor;
	}
}
