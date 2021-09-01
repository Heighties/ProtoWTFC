package protowtfc;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

	private static String QUESTION_CARD = "QUESTION";
	private static String REPONSE_CARD = "REPONSE";

	private CardLayout cardLayout = new CardLayout();
	private JPanel cardPanel = new JPanel(cardLayout);
	private String selectedCard = QUESTION_CARD;
	HashSet<Questions> set = new HashSet<Questions>();

	public Game() {

		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(620, 900));

		// initialisation d'état

		// type nom de variable = assignation

		Questions questions = Questions.random();
		set.add(questions);

		// ("image/" pour retrouver l'image dans le dossier image)
		ImgPanel questionpanel = new ImgPanel(Game.class.getResource("/images/" + questions.com1), 600);
		ImgPanel reponsepanel = new ImgPanel(Game.class.getResource("/images/" + questions.rep1), 600);

		cardPanel.add(QUESTION_CARD, questionpanel);
		cardPanel.add(REPONSE_CARD, reponsepanel);

		JButton btn = new JButton("Next");
		btn.addActionListener(l -> {
			if (QUESTION_CARD.equals(selectedCard)) {
				cardLayout.show(cardPanel, REPONSE_CARD);
				repaint();
				selectedCard = REPONSE_CARD;
			} else {
				if (set.size() == Questions.values().length) {
					// terminado ! }
					btn.setEnabled(false);
					return;

				}
				Questions nouvelleQuestion = Questions.random();
				while (set.contains(nouvelleQuestion)) {
					nouvelleQuestion = Questions.random();

				}
				set.add(nouvelleQuestion);

				System.out.println(nouvelleQuestion.name());
				// rafraichir l'image du panneau
				questionpanel.changeImage(Game.class.getResource("/images/" + nouvelleQuestion.com1), 600);
				reponsepanel.changeImage(Game.class.getResource("/images/" + nouvelleQuestion.rep1), 600);

				cardLayout.show(cardPanel, QUESTION_CARD);
				repaint();
				selectedCard = QUESTION_CARD;
			}
		});

		add(cardPanel, new GridBagConstraints(0, 0, 1, 1, 1d, 1000d, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		add(Box.createVerticalGlue(), new GridBagConstraints(0, 1, 1, 1, 1d, 1d, GridBagConstraints.CENTER,
				GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
		add(btn, new GridBagConstraints(0, 2, 1, 1, 1d, 0d, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	public static void main(String[] args) {
		new Game().setVisible(true);
	}
}