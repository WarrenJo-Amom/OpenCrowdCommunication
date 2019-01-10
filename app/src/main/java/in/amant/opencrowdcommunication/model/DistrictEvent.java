package in.amant.opencrowdcommunication.model;

import java.util.ArrayList;

public class DistrictEvent {

    private String name;
    private ArrayList<Event_varOfDistrict> events = new ArrayList<>();

    public DistrictEvent(String name, ArrayList<Event_varOfDistrict> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Event_varOfDistrict> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event_varOfDistrict> events) {
        this.events = events;
    }
}
