package protowtfc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImgPanel extends JPanel {
	private Image scaledImage = null;

	public ImgPanel(File imageFile, int wishedWidth) {
		super(new GridBagLayout());
		changeImage(imageFile, wishedWidth);
		JPanel p = new JPanel();
		p.setBackground(new Color(0, 0, 0, 0));
		add(p);
	}

	public ImgPanel(URL resource, int wishedWidth) {
		super(new GridBagLayout());
		try {
			BufferedImage bufferedImage = ImageIO.read(resource);
			initImg(bufferedImage, wishedWidth, ImageUtils.needAlpha(resource.getPath()));
		} catch (IOException ex) {
			Logger.getLogger(ImgPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
		JPanel p = new JPanel();
		p.setBackground(new Color(0, 0, 0, 0));
		add(p);
	}

	public void changeImage(File imageFile, int wishedWidth) {
		if (!imageFile.exists())
			return;
		initImg(new ImageIcon(imageFile.getAbsolutePath()).getImage(), wishedWidth, ImageUtils.needAlpha(imageFile));
	}

	public void changeImage(URL resource, int wishedWidth) {
		if (resource == null)
			return;
		try {
			BufferedImage bufferedImage = ImageIO.read(resource);
			initImg(bufferedImage, wishedWidth, ImageUtils.needAlpha(resource.getPath()));
		} catch (IOException ex) {
			Logger.getLogger(ImgPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void initImg(Image image, int wishedWidth, boolean needAlpha) {
		int wishedHeight = (int) (image.getHeight(null) * wishedWidth / image.getWidth(null));
		scaledImage = ImageUtils.getScaledImage(image, wishedWidth, wishedHeight, needAlpha);
		setPreferredSize(new Dimension(wishedWidth, wishedHeight));
		SwingUtilities.invokeLater(() -> repaint());
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(scaledImage, 0, 0, null);
	}
}