package protowtfc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {

	private static final String JPG_EXT = ".jpg";
	private static final String JPG_MAJ_EXT = ".JPG";
	private static final String JPEG_EXT = ".jpeg";
	private static final String JPEG_MAJ_EXT = ".JPEG";
	private static final String GIF_EXT = ".gif";
	private static final String GIF_MAJ_EXT = ".GIF";
	private static final String PNG_EXT = ".png";
	private static final String PNG_MAJ_EXT = ".PNG";

	public static boolean isImage(File f) {
		String name = f.getName();
		return name.endsWith(JPG_EXT) || name.endsWith(JPEG_EXT) || name.endsWith(JPG_MAJ_EXT)
				|| name.endsWith(JPEG_MAJ_EXT) || name.endsWith(PNG_EXT) || name.endsWith(PNG_MAJ_EXT)
				|| name.endsWith(GIF_EXT) || name.endsWith(GIF_MAJ_EXT);
	}

	public static boolean needAlpha(File f) {
		String fileName = f.getName();
		return needAlpha(fileName);
	}

	public static boolean needAlpha(String fileName) {
		return fileName.endsWith(PNG_EXT) || fileName.endsWith(PNG_MAJ_EXT) || fileName.endsWith(GIF_EXT)
				|| fileName.endsWith(GIF_MAJ_EXT);
	}

	/**
	 * Resize an image to enter in a maxWidth x maxHeight px.<br>
	 * Ratio is kept, so the image width or height could not be the same as
	 * provided.
	 *
	 * @param img       The image to resize
	 * @param maxWidth  The maximum width the image could reach.
	 * @param maxHeight The maximum height the image could reach.
	 * @param needAlpha Yes if the image is png, or gif.
	 * @return the resized image.
	 */
	public static BufferedImage resizeImageToFit(BufferedImage img, int maxWidth, int maxHeight, boolean needAlpha) {
		int actualWidth = img.getWidth();
		int actualHeight = img.getHeight();
		double actualRatio = actualWidth / actualHeight;
		double targetedRatio = maxWidth / maxHeight;

		int newWidth = maxWidth;
		int newHeight = maxHeight;

		if (Double.compare(actualRatio, targetedRatio) > 0) {
			newHeight = ((int) maxWidth * actualHeight / actualWidth);
		} else {
			newWidth = ((int) maxHeight * actualWidth / actualHeight);
		}

		return resizeImage(img, newWidth, newHeight, needAlpha);
	}

	/**
	 * Put the image at the given size.<br>
	 * CAUTION : It could be distorted if the ratio isn't the same as the original
	 * image.
	 *
	 * @param srcImg    The image to resize
	 * @param w         new width
	 * @param h         new height
	 * @param needAlpha put alpha if the image is PNG, ...
	 * @return the resized image
	 */
	public static Image getScaledImage(Image srcImg, int w, int h, boolean needAlpha) {
		BufferedImage resizedImg = new BufferedImage(w, h,
				needAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	/**
	 * Resize the image
	 *
	 * @param img       the image to resize
	 * @param newWidth  new width
	 * @param newHeight new height
	 * @param needAlpha true if png or gif, false for jpeg, ...
	 * @return the resized image
	 */
	public static BufferedImage resizeImage(BufferedImage img, int newWidth, int newHeight, boolean needAlpha) {
		BufferedImage scaleImage = new BufferedImage(newWidth, newHeight,
				needAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) scaleImage.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return scaleImage;
	}

}
