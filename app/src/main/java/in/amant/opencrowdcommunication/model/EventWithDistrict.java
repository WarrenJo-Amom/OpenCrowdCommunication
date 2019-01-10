package in.amant.opencrowdcommunication.model;

public class EventWithDistrict {

    private String event;
    private Event_varOfDistrict event_varOfDistrict;

    private String distrit;

    public EventWithDistrict(String event, Event_varOfDistrict event_varOfDistrict, String distrit) {
        this.event = event;
        this.event_varOfDistrict = event_varOfDistrict;
        this.distrit = distrit;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Event_varOfDistrict getEvent_varOfDistrict() {
        return event_varOfDistrict;
    }

    public void setEvent_varOfDistrict(Event_varOfDistrict event_varOfDistrict) {
        this.event_varOfDistrict = event_varOfDistrict;
    }

    public String getDistrit() {
        return distrit;
    }

    public void setDistrit(String distrit) {
        this.distrit = distrit;
    }
}
