package phone.src;

import java.text.ParseException;
import java.util.*;

public class Calendar extends Application {

    private final Scanner scanner = super.scanner;
    private final LinkedList<Event>[] days;

    public Calendar() {
        this.days = new LinkedList[30];
        for (int i = 0; i < 30; i++) days[i] = new LinkedList<>(); // initialize each day
    }

    @Override
    protected void printOptions() {
        System.out.println("Calendar:");
        System.out.println("1: Add event");
        System.out.println("2: Delete event");
        System.out.println("3: Print events at date");
        System.out.println("4: Print meetings with contact");
        System.out.println("5: Check overlaps");
        System.out.println("6: Print all events");
        System.out.println("7: Exit");
    }
    
    @Override
    protected boolean decodeUserInput(int input) throws Exception {
        switch (input) {
            case 1:
                this.addEvent();
                break;
            case 2:
                this.deleteEvent();
                break;
            case 3:
                this.eventsAt();
                break;
            case 4:
                this.meetingsWith();
                break;
            case 5:
                this.overlaps();
                break;
            case 6:
                this.printEvents();
                break;
            case 7:
                return true;
            default:
                throw new Exception("Invalid Option");
        }
        return false;
    }

    private void printEvents() {
        System.out.println(Arrays.toString(this.days));
    }

    private void overlaps() {
    }

    private void meetingsWith() {
    }

    private void eventsAt() {
    }

    private void deleteEvent() {
        System.out.println("Enter date and time of event to delete (dd/MM/yyyy HH:mm): ");
        String dateString = scanner.nextLine();

    }

    private void addEvent() {
        Event event = null;
        System.out.println("Meeting or event [m/e]? ");
        String me = scanner.nextLine();

        System.out.println("Enter date and time (dd/MM/yyyy HH:mm): ");
        String dateString = scanner.nextLine();

        System.out.println("Enter length (minutes): ");
        int lengthInMinutes = scanner.nextInt();
        try {
            if (me.equals("m")) {
                System.out.println("Enter contact name: ");
                // search contact
                // if not exists throw exception
            } else if (me.equals("e")) {
                System.out.println("Enter event description: ");
                String description = scanner.nextLine();
                event = new Event(dateString, lengthInMinutes, description);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }

        // add event to cal
    }
}
