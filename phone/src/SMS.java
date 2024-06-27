package phone.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SMS extends Application {
    private final Scanner scanner = super.scanner;
    private final HashMap<String, ArrayList<String>> messages = new HashMap<String, ArrayList<String>>();
    private final Phonebook phonebook;
    private final ArrayList<Contact> contacts;

    public SMS(Phonebook phonebook) {
        this.phonebook = phonebook;
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

    @Override
    public void onContactRemoval() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    private void addMessage() {
        System.out.println("Enter contact name:");
        String name = scanner.nextLine();

        Contact contact = phonebook.searchContact(name);
        if (contact == null) {
            System.out.println("Contact not found. Canceling the message...");
            System.out.println("Error adding contact");
            return;
        }

        System.out.println("Enter correspondence message:");
        String message = scanner.nextLine();

        ArrayList<String> messageList;
        boolean isFirstMessage;

        if (messages.containsKey(name)) {
            messageList = messages.get(name);
            isFirstMessage = false;
        } else {
            messageList = new ArrayList<>();
            isFirstMessage = true;
        }

        messageList.add(message);
        messages.put(name, messageList);

        if (isFirstMessage) {
            System.out.println("New chat opened with " + contact.getName() + ". Message: " + message);
        } else {
            System.out.println("Message added to existing chat with " + contact.getName() + ": " + message);
        }
    }

    private void removeMessage() {
        System.out.println("Enter contact name:");
        String name = scanner.nextLine();

        Contact contact = phonebook.searchContact(name);
        if (contact == null) {
            System.out.println("Contact not found. Canceling the removal...");
            System.out.println("Error removing messages");
            return;
        }

        if (!messages.containsKey(name)) {
            System.out.println("No messages found for this contact.");
            return;
        }

        messages.remove(name);
        System.out.println("All messages removed for contact " + contact.getName());
    }

    private void printContactMessage() {
        System.out.println("Enter contact name:");
        String name = scanner.nextLine();

        Contact contact = phonebook.searchContact(name);

        if (!messages.containsKey(name)) {
            System.out.println("No messages found for contact " + contact.getName());
            return;
        }

        ArrayList<String> messageList = messages.get(name);
        System.out.println("Correspondence with " + contact.getName() + ":");
        for (String message : messageList) {
            System.out.println(message);
        }
    }

    private void searchMessage() {
        System.out.println("Enter message to search:");
        String message = scanner.nextLine();
        for (Contact contact : contacts) {
            if (messages.get(contact.getName()) != null && messages.get(contact.getName()).contains(message)) {
                System.out.println(contact.getName());
            }
        }
    }

    private void printAllMessages() {
        for (Contact contact : contacts) {
            if (messages.containsKey(contact.getName()))
                System.out.println(contact.getName() + ":" + messages.get(contact.getName()));
            else
                System.out.println(contact.getName() + ":[]");
        }
    }


}
