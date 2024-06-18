package phone.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SMS extends Application {
    private final Scanner scan = super.scanner;
    private final HashMap<String, ArrayList<String>> messages = new HashMap<String, ArrayList<String>>();
    private final ArrayList<Contact> contacts;

    public SMS(Phonebook phonebook) {
        contacts = phonebook.getContacts();
    }

    @Override
    protected boolean decodeUserInput(int input) throws Exception {
        switch (input) {
            case 1:
                this.addMessage();
                break;
            case 2:
                this.removeMessage();
                break;
            case 3:
                this.printContactMessage();
                break;
            case 4:
                this.searchMessage();
                break;
            case 5:
                this.printAllMessages();
                break;
            case 6:
                return true;
            default:
                throw new Exception("Invalid Option");
        }
        return false;
    }

    @Override
    protected void printOptions() {
        System.out.println("SMS:");
        System.out.println("1: Add message");
        System.out.println("2: Delete message");
        System.out.println("3: Print contact messages");
        System.out.println("4: Search message");
        System.out.println("5: Print all messages");
        System.out.println("6: Exit");
    }
    
    private Contact findContactByNameAndPhoneNumber(String name, String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name) && contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }

	private void addMessage() {
        System.out.println("Enter contact name:");
        String name = scan.nextLine();
        
        System.out.println("Enter phone number:");
        String phoneNumber = scan.nextLine();
        
        Contact contact = findContactByNameAndPhoneNumber(name, phoneNumber);
        if (contact == null) {
            System.out.println("Contact not found. Canceling the message...");
            System.out.println("Error adding contact");
            return;
            }
        
        System.out.println("Enter correspondence message:");
        String message = scan.nextLine();
        
        String key = name + ":" + phoneNumber;
        ArrayList<String> messageList;
        boolean isFirstMessage;

        if (messages.containsKey(key)) {
            messageList = messages.get(key);
            isFirstMessage = false;
        } else {
            messageList = new ArrayList<>();
            isFirstMessage = true;
        }

        messageList.add(message);
        messages.put(key, messageList);

        if (isFirstMessage) {
            System.out.println("New chat opened with " + contact.getName() + ". Message: " + message);
        } 
        else {
            System.out.println("Message added to existing chat with " + contact.getName() + ": " + message);
        }
    }

    private void removeMessage() {
	    System.out.println("Enter contact name:");
	    String name = scan.nextLine();

	    System.out.println("Enter phone number:");
	    String phoneNumber = scan.nextLine();

	    Contact contact = findContactByNameAndPhoneNumber(name, phoneNumber);
	    if (contact == null) {
	        System.out.println("Contact not found. Canceling the removal...");
	        System.out.println("Error removing messages");
	        return;
	    }

	    String key = name + ":" + phoneNumber;

	    if (!messages.containsKey(key)) {
	        System.out.println("No messages found for this contact.");
	        return;
	    }

	    messages.remove(key);
	    System.out.println("All messages removed for contact " + contact.getName());
	}

    private void printContactMessage() {
        System.out.println("Enter contact name:");
        String name = scan.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = scan.nextLine();

        Contact contact = findContactByNameAndPhoneNumber(name, phoneNumber);
        
        String key = name + ":" + phoneNumber;

        if (!messages.containsKey(key)) {
            System.out.println("No messages found for contact " + contact.getName());
            return;
        }

        ArrayList<String> messageList = messages.get(key);
        System.out.println("Correspondence with " + contact.getName() + ":");
        for (String message : messageList) {
            System.out.println(message);
        }
    }

    private void searchMessage() {
    }

    private void printAllMessages() {
    }


}
