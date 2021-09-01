package protowtfc;

import java.util.Random;

public enum Questions {

	Interstellar("cominter.PNG", "affinter.jpg"), JurassicPark("comjur.png", "affjur.jpg"),
	Titanic("comtita.png", "afftita.jpg"), PulpFiction("compulp.png", "affpulp.jpg"),
	BabyDriver("combaby.png", "affbaby.jpg");

	String com1;
	String rep1;

	private Questions(String c1, String r1) {

		com1 = c1;
		rep1 = r1;

	}

	public static Questions random() {

		Questions[] values = Questions.values();
		int maxValues = values.length;
		Random r = new Random();
		r.nextInt(maxValues);
		return values[r.nextInt(maxValues)];

	}
}
