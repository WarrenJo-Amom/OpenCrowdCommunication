package in.amant.opencrowdcommunication.model;

import java.util.ArrayList;

public class District {

    private String name;
    private ArrayList<Spot_varOfDistrict> spots = new ArrayList<>();

    public District(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Spot_varOfDistrict> getSpots() {
        return spots;
    }

    public void setSpots(ArrayList<Spot_varOfDistrict> spots) {
        this.spots = spots;
    }
}
