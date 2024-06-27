package phone.src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event implements Comparable<Event> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public enum EventType {
        MEETING, GENERAL
    }

    private final EventType eventType;
    private final Date startTime;
    private final int lengthInMinutes;
    private Contact contact;
    private String description;

    // Constructor for meeting
    public Event(String date, int lengthInMinutes, Contact contact) throws ParseException {
        this.eventType = EventType.MEETING;
        this.lengthInMinutes = lengthInMinutes;
        this.startTime = dateFormat.parse(date);
        this.contact = contact;
    }

    // Constructor for general event
    public Event(String date, int lengthInMinutes, String description) throws ParseException {
        this.eventType = EventType.GENERAL;
        this.lengthInMinutes = lengthInMinutes;
        this.startTime = dateFormat.parse(date);
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public Contact getContact() throws Exception {
        if (this.getEventType() == EventType.GENERAL) {
            throw new Exception("Not a meeting");
        }
        return contact;
    }

    public String getDescription() throws Exception {
        if (this.getEventType() == EventType.MEETING) {
            throw new Exception("Not a general event");
        }
        return description;
    }

    public boolean isDate(String date) throws ParseException {
        return (this.getStartTime().equals(dateFormat.parse(date)));
    }

    public int getDayOfTheMonth() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(this.getStartTime());
        return cal.get(java.util.Calendar.DAY_OF_MONTH);
    }

    public boolean isDate(Date date) {
        return (this.getStartTime().equals(date));
    }

    public Date getEndTime() {
        return new Date(this.getStartTime().getTime()+ (long) this.getLengthInMinutes() *60*1000);
    }
    public boolean overlapsWith(Event e) {
        return (this.startTime.before(e.getEndTime()) && this.getEndTime().after(e.startTime));
    }

    @Override
    public int compareTo(Event o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
    /*
    @Override
    public String toString() {
        String str = "";
        str += (this.getEventType() == EventType.MEETING) ? "Meeting: " : "Event: ";
        str += "date: ";
        str += this.getStartTime();
        str += ", length: ";
        str += this.getLengthInMinutes();
        try {
            str += (this.getEventType() == EventType.MEETING) ? this.getContact().getName() : this.getDescription();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return str;
    }*/
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append((this.getEventType() == EventType.MEETING) ? "Meeting: " : "Event: ");
        str.append("date: ");
        str.append(dateFormat.format(this.getStartTime()));
        str.append(", length: ");
        str.append(this.getLengthInMinutes()).append(" minutes, ");

        try {
            if (this.getEventType() == EventType.MEETING) {
                Contact contact = this.getContact();
                if (contact != null) {
                    str.append("Contact: ").append(contact.getName());
                } else {
                    str.append("No contact available");
                }
            } else {
                String description = this.getDescription();
                if (description != null && !description.isEmpty()) {
                    str.append("Description: ").append(description);
                } else {
                    str.append("No description available");
                }
            }
        } catch (Exception e) {
            str.append("Error: ").append(e.getMessage());
        }

        return str.toString();
    }
}
