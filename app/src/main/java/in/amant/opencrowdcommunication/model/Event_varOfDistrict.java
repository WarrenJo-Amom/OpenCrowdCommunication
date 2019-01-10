package in.amant.opencrowdcommunication.model;

import java.io.Serializable;

public class Event_varOfDistrict implements Serializable {

    private String name;
    private String no;
    private String descr;

    public Event_varOfDistrict(String name, String no, String descr) {
        this.name = name;
        this.no = no;
        this.descr = descr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
