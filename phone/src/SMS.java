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



    private void addMessage() {
    }

    private void removeMessage() {
    }

    private void printContactMessage() {
    }

    private void searchMessage() {
    }

    private void printAllMessages() {
    }


}
