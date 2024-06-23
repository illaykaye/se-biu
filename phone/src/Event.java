package phone.src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public enum EventType {
        MEETING, GENERAL
    }

    private final EventType eventType;
    private final Date date;
    private final int lengthInMinutes;
    private Contact contact;
    private String description;

    // Constructor for meeting
    public Event(String date, int lengthInMinutes, Contact contact) throws ParseException {
        this.eventType = EventType.MEETING;
        this.lengthInMinutes = lengthInMinutes;
        this.date = dateFormat.parse(date);
        this.contact = contact;
    }

    // Constructor for general event
    public Event(String date, int lengthInMinutes, String description) throws ParseException {
        this.eventType = EventType.GENERAL;
        this.lengthInMinutes = lengthInMinutes;
        this.date = dateFormat.parse(date);
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Date getDate() {
        return date;
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
}
