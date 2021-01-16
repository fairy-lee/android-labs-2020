package edu.hzuapp.androidlabs.net1814080903337.model;



public class Storage {
    private int id;
    private String name;
    private String location;
    private int isDel;

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getOverduedate() {
        return overduedate;
    }

    public void setOverduedate(String overduedate) {
        this.overduedate = overduedate;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    private  String opendate;
    private String overduedate;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
