package in.amant.opencrowdcommunication.model;

public class UserData {

    private String name;
    private String org;
    private String rank;

    public UserData() {
    }

    public UserData(String name, String org, String rank) {
        this.name = name;
        this.org = org;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
