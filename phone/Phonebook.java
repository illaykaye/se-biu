package phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Phonebook {
	private ArrayList<Contact> contacts;

	Scanner scanner = new Scanner(System.in);

	public Phonebook() {
		this.contacts = new ArrayList<Contact>();
	}
	
	public void run() {
		boolean exit = false;
		System.out.println("Phonebook:");
		System.out.println("1: Add contact");
		System.out.println("2: Delete contact");
		System.out.println("3: Print all contacts");
		System.out.println("4: Search contact");
		System.out.println("5: Sort phonebook (lexicographic)");
		System.out.println("6: Sort phonebook (numeric)");
		System.out.println("7: Remove duplicates");
		System.out.println("8: Reverse phonebook");
		System.out.println("9: Save phonebook");
		System.out.println("10: Load contacts");
		System.out.println("11: Exit");
		while (!exit) {
			System.out.print("Enter cmd: ");
			int cmd = scanner.nextInt();
			switch (cmd) {
				case 1:
					this.addContact();
					break;
				case 2:
					this.removeContact();
					break;
				case 3:
					this.printAllContacts();
					break;
				case 4:
					this.searchContact();
					break;
				case 5:
					this.nameLexicographicSort();
					break;
				case 6:
					this.numberNumericSort();
					break;
				case 7:
					this.removeDuplicateContacts();
					break;
				case 8:
					this.reverseOrder();
					break;
				case 9:
					this.saveToFile();
					break;
				case 10:
					this.loadFromFile();
					break;
				case 11:
					exit = true;
					break;
				default:
					System.out.println("Command not found");
					break;
			};
		}
	}
	
	private void addContact() {
		System.out.print("Enter contact name: ");
		String name = scanner.nextLine();
		System.out.print("Enter phone number: ");
		String phoneNumber = scanner.nextLine();
		Contact contact = new Contact(name, phoneNumber);
		this.contacts.add(contact);
	}
	
	private void removeContact() {
		System.out.print("Enter contact name to remove: ");
		String name = scanner.nextLine();
		for (int i = 0; i < this.contacts.size(); ++i) {
			if (this.contacts.get(i).getName().equals(name)) {
				this.contacts.remove(i);
				return;
			}
		}
	}
	
	private void printAllContacts() {}
	
	private void searchContact() {
		System.out.print("Enter contact name to search: ");
		String name = scanner.nextLine();
		int counter = 1;
		for (int i = 0; i < this.contacts.size(); ++i) {
			Contact contact = this.contacts.get(i);
			if (this.contacts.get(i).getName().equals(name)) {
				System.out.printf("%d. %s - %s", counter, contact.getName(), contact.getPhoneNumber());
				counter++;
			}
		}
	}

	private void nameLexicographicSort() {
		this.contacts.sort((a, b) -> a.getName().compareTo(b.getName()));
	}
	
	private void numberNumericSort() {
		this.contacts.sort((a, b) -> b.getPhoneNumber().compareTo(a.getPhoneNumber()));
	}
	
	private void removeDuplicateContacts() {
		for (int i = this.contacts.size(); 0 <= i; --i) {
			for (Contact contact : this.contacts.subList(0, i)) {
				if (this.contacts.get(i).equals(contact)) {
					this.contacts.remove(i);
					break;
				}
			}
		}
	}
	
	private void reverseOrder() {
		Collections.reverse(this.contacts);
	}
	
	// NOTE: This should be combined with "printAllContacts"
	private void saveToFile() {
		
	}
	
	private void loadFromFile() {
		
	}
}
