package phone;

import java.util.ArrayList;

public class Phonebook {
	private ArrayList<Contact> contacts;
	
	public Phonebook() {
		this.contacts = new ArrayList<Contact>();
	}
	
	public void run() {
		
	}
	
	private void addContact(Contact contact) {
		this.contacts.add(contact);
	}
	
	private void removeContact(String name) {
		for (int i = 0; i < this.contacts.size(); ++i)
			if (this.contacts.get(i).getName().equals(name)) {
				this.contacts.remove(i);
				return;
			}
	}
	
	private void printAllContacts() {
		
	}
	
	private void nameLexicographicSort() {
		this.contacts.sort((a, b) -> a.getName().compareTo(b.getName()));
	}
	
	private void numberNumericSort() {
		this.contacts.sort((a, b) -> b.getPhoneNumber().compareTo(a.getPhoneNumber()));
	}
	
	private void removeDuplicateContacts() {
		for (int i = 0; i < this.contacts.size(); ++i)
			for (Contact contact : this.contacts.subList(0, i))
				if (this.contacts.get(i).equals(contact)) {
					this.contacts.remove(i);
					break;
				}
	}
	
	private void reverseOrder() {
		
	}
	
	// NOTE: This should be combined with "printAllContacts"
	private void saveToFile() {
		
	}
	
	private void loadFromFile() {
		
	}
}
