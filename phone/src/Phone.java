package phone.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phone {
	private final Scanner scanner = new Scanner(System.in);
	private final List<Application> apps;

	public Phone() {
		this.apps = new ArrayList<>(4);
		this.apps.add(new Phonebook());
		for (int i = 1; i < 4; i++) {
			this.apps.add(null);
		}
	}

	private void printOptions() {
		System.out.println("Phone apps:");
		System.out.println("1: Phonebook");
		System.out.println("2: SMS");
		System.out.println("3: Calendar");
		System.out.println("4: Media");
		System.out.println("5: Exit");
	}

	private boolean decodeUserInput(int input) throws Exception {
		switch (input) {
		case 1:
			this.apps.get(0).runApp();
			break;
		case 2:
			if (this.apps.get(1) == null) {
				this.apps.set(1, new SMS((Phonebook) this.apps.get(0)));
			}
			this.apps.get(1).runApp();
			break;
		case 3:
			if (this.apps.get(2) == null) {
				this.apps.set(2, new Calendar((Phonebook) this.apps.get(0)));
			}
			this.apps.get(2).runApp();
			break;
		case 4:
			if (this.apps.get(3) == null) {
				System.out.println("Choose directory name: ");
				String dirName = scanner.nextLine();
				this.apps.set(3, new Media(dirName));
			}
			this.apps.get(3).runApp();
			break;
		case 5:
			return true;
		default:
			throw new Exception("Invalid Option");
		}
		return false;
	}

	public void run() {
		boolean exit = false;
		while (!exit) {
			printOptions();
			System.out.println("Choose an option: ");
			try {
				int input = Integer.parseInt(scanner.nextLine());
				exit = decodeUserInput(input);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
