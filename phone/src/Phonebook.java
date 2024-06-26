// Group 4
package phone.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Phonebook extends Application {
    private final Scanner scanner = super.scanner;
    private final ArrayList<Contact> contacts;

    public Phonebook() {
        //noinspection Convert2Diamond
        this.contacts = new ArrayList<Contact>();
    }

    @Override
    protected void printOptions() {
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
    }

    @Override
    protected boolean decodeUserInput(int input) throws Exception {
        switch (input) {
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
                return true;
            default:
                throw new Exception("Invalid Option");
        }
        return false;
    }


    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }


    private void addContact() {
        System.out.println("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        try {
            int numberCheck = Integer.parseInt(phoneNumber);
            if (numberCheck < 0 || phoneNumber.length() != 10) throw new Exception();
            Contact contact = new Contact(name, phoneNumber);
            this.contacts.add(contact);
        } catch (Exception e) {
            System.out.println("Invalid Phone Number");
        }
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


    private void printAllContacts() {
        for (Contact contact : this.contacts) {
            System.out.println(contact);
        }
    }

    private void searchContact() {
        System.out.print("Enter contact name to search: ");
        String name = scanner.nextLine();
        int counter = 1;
        for (Contact contact : this.contacts) {
            if (contact.getName().equals(name)) {
                System.out.printf("%d. %s - %s", counter, contact.getName(), contact.getPhoneNumber());
                System.out.println();
                counter++;
            }
        }
        if (counter == 1)
            System.out.println("Contact not found");
    }

    private void nameLexicographicSort() {
        //noinspection ComparatorCombinators
        this.contacts.sort((a, b) -> a.getName().compareTo(b.getName()));
    }

    private void numberNumericSort() {
        this.contacts.sort((a, b) -> b.getPhoneNumber().compareTo(a.getPhoneNumber()));
    }

    private void removeDuplicateContacts() {
        for (int i = this.contacts.size() - 1; 0 <= i; --i) {
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

    private void saveToFile() {
        System.out.println("Enter Path:");
        String path = scanner.nextLine();
        try (FileWriter writer = new FileWriter(path)) {
            for (Contact contact : this.contacts) {
                writer.write(contact.toString() + '\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadFromFile() {
        System.out.println("Enter file path to load contacts: ");
        String path = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    Contact contact = new Contact(parts[0], parts[1]);
                    this.contacts.add(contact);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
