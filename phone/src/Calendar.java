package phone.src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Calendar extends Application {
    private final LinkedList<Event>[] calendar;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Calendar() {
        super();
        this.calendar = new LinkedList[30];
        for (int i = 0; i < 30; i++) calendar[i] = new LinkedList<>(); // initialize each day
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
    protected void printAllData() {
        for (LinkedList<Event> calDay : this.calendar) {
            for (Event e : calDay) System.out.println(e);
        }
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
                this.printAllData();
                break;
            case 7:
                return true;
            default:
                throw new Exception("Invalid Option");
        }
        return false;
    }

    private LinkedList<Event> getDay(int day) {
        return this.calendar[day];
    }

    private void overlaps() {
        for (int i = 0; i < 30; i++) {
            overlapsDay(i);
        }
    }

    private void overlapsDay(int dayIndex) {
        LinkedList<Event> day = this.getDay(dayIndex);
        ListIterator<Event> iter = day.listIterator();
        if (!iter.hasNext()) return;

        Event prevEvent = iter.next();
        while (iter.hasNext()) {
            Event currentEvent = iter.next();
            if (prevEvent.overlapsWith(currentEvent)) {
                iter.remove();
            } else {
                prevEvent = currentEvent;
            }
        }
    }

    private void meetingsWith() throws Exception {
        System.out.println("Enter contact name: ");
        String contactName = scanner.nextLine();
        // search contact in phonebook
        // throw exception if doesn't exist
        boolean foundMeeting = false;
        for (LinkedList<Event> calDay : this.calendar) {
            for (Event e : calDay) {
                if (e.getContact().getName().equals(contactName)) {
                    System.out.println(e);
                    foundMeeting = true;
                }
            }
        }
        if (!foundMeeting) System.out.println("There no meetings with " + contactName);
    }

    private void eventsAt() {
        System.out.println("Enter day of the month: ");
        int day = scanner.nextInt();
        LinkedList<Event> calDay = this.getDay(day);
        Iterator<Event> iter = calDay.iterator();
        System.out.println("Events at day: \n");
        while (iter.hasNext()) System.out.println(iter.next());
    }

    private void deleteEvent() throws ParseException {
        System.out.println("Enter date and time of event to delete (dd/MM/yyyy HH:mm): ");
        String dateString = scanner.nextLine();
        Date date = dateFormat.parse(dateString);
        LinkedList<Event> calDay = this.getDay(date.getDay());
        Event eve = null;
        boolean removed = false;
        for (Event e : calDay) {
            if (e.isDate(dateString)) {
                eve = e;
                removed = calDay.remove(e);
                break;
            }
        }
        if (!removed) {
            System.out.println("Event at specified date not found.");
        } else {
            System.out.println(eve + "deleted successfully.");
        }
    }

    private void addEvent() throws ParseException {
        Event event = null;
        System.out.println("Meeting or event [m/e]? ");
        String me = scanner.nextLine();

        System.out.println("Enter date and time (dd/MM/yyyy HH:mm): ");
        String dateString = scanner.nextLine();

        System.out.println("Enter length (minutes): ");
        int lengthInMinutes = scanner.nextInt();

        if (me.equals("m")) {
            System.out.println("Enter contact name: ");
            // search contact
            // if not exists throw exception
        } else if (me.equals("e")) {
            System.out.println("Enter event description: ");
            String description = scanner.nextLine();
            event = new Event(dateString, lengthInMinutes, description);
        }
        boolean added = false;
        LinkedList<Event> calDay = this.getDay(event.getStartTime().getDay());
        ListIterator<Event> iter = calDay.listIterator();
        while (iter.hasNext()) {
            Event e = iter.next();
            if (event.getStartTime().after(e.getStartTime())) {
                iter.add(event);
                added = true;
            }
        }
        if (!added) {
            iter.add(event);
        }
    }
}
