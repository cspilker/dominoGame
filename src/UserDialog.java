package de.nordakademie.domino;
import java.util.Scanner;

public class UserDialog {
	public static Scanner scanner=null;

	int getUserInput(String label, String... alternatives) {
        if (scanner==null) scanner=new Scanner(System.in);
		do {
			promtUser(label, alternatives);
			int input = scanUserInput();
			if (input < 0 || input >= alternatives.length) {
				System.out.println("Eingabe bitte wiederholen");
			} else {
				return input;
			}
		} while (true);
	}

	private int scanUserInput() {

		try {
			String inputString = scanner.nextLine();
			return Integer.parseInt(inputString);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	private void promtUser(String label, String... alternatives) {
		System.out.print(label);
		int index = 0;
		for (String alternative : alternatives) {
			System.out.format("(%d) %s ", index++, alternative);
		}
		System.out.println();
	}
}
