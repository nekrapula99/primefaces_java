package com.tlm.faelec.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDefaultScriptlet;

public class RotateText extends JRDefaultScriptlet {

	/**
	 * Creates an image with rotated text
	 * 
	 * @param text
	 *            the text to be displayed
	 * @param font
	 * @param width
	 *            The width of the returned image, must fit the size of the text
	 * @param height
	 *            the height of the returned image, must fit the size of the
	 *            text
	 * @param textAngle
	 *            the angle of the text
	 * @param textColor
	 *            the color of the text
	 * @return an image with the rotated text
	 */
	public Image rotateText(String text, Font font, int width, int height,
			int textAngle, Color textColor) {
		if (text == null)
			throw new IllegalArgumentException("text must be not-null ");
		if (text.length() == 0)
			throw new IllegalArgumentException("text is empty string");
		if (text.trim().length() == 0)
			throw new IllegalArgumentException(
					"text must contain at least one character that is not space");

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		// make whole image transparent
		for (int i = image.getWidth() - 1; i > -1; i--) {
			for (int j = image.getHeight() - 1; j > -1; j--) {
				if (image.getRGB(i, j) == new Color(255, 255, 255).getRGB()) {
					image.setRGB(i, j, new Color(0, 0, 0, 0).getRGB());
				}
			}
		}

		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		double maxLineWidth = width/* * 2.0/3.0 */;
		double completeWidth = fm.stringWidth(text);

		double caLinesCountD = completeWidth / maxLineWidth;
		int caLinesCount = -1;
		if (caLinesCountD % 1.0 > 0) {
			caLinesCount = (int) caLinesCountD + 1;
		} else
			caLinesCount = (int) caLinesCountD;

		List<String> lines = new ArrayList<String>(caLinesCount);

		if (caLinesCount == 1)
			lines.add(text);
		else {
			int caSepIdx = text.length() / caLinesCount;
			for (int i = 0; i < text.length();) {
				int nextCaSep = i + caSepIdx;
				if (text.length() > nextCaSep) {

					int sepIdx = text.substring(i, nextCaSep + 1).lastIndexOf(
							' ');
					if (sepIdx == 0) {
						i++;
						continue;

					} else if (sepIdx == -1) {
						lines.add(text.substring(i, nextCaSep));
						i += nextCaSep;
					} else {
						lines.add(text.substring(i, i + sepIdx));
						i += sepIdx + 1;
					}
				} else {
					lines.add(text.substring(i));
					break;
				}
			}
		}

		g.rotate(Math.PI / 180 * textAngle, width / 2, height / 2);
		int textHeight = fm.getMaxAscent();

		int lineY = height / 2;
		// shift up for vertical centration
		int halfLines = lines.size() / 2;
		double shiftLines = lines.size() % 2 == 0 ? halfLines - 1
				: halfLines - 0.5;
		shiftLines += 0.2;
		lineY -= textHeight * shiftLines;

		for (int lineIdx = 0; lineIdx < lines.size(); lineIdx++) {
			String line = lines.get(lineIdx);
			int lineWidth = fm.stringWidth(line);
			int lineX = (width / 2) - (lineWidth / 2);
			g.setColor(textColor);
			g.drawChars(line.toCharArray(), 0, line.length(), lineX, lineY);
			lineY += textHeight;
		}

		return image;
	}

}