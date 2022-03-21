package models;

public class Ranger {
    //create properties
    private int id;
    private String rangerName;
    private int rangerBadgeNumber;
    private String rangerContact;

    //get a constructor
    public Ranger(String rangerName, int rangerBadgeNumber, String rangerContact) {
        this.rangerName = rangerName;
        this.rangerBadgeNumber = rangerBadgeNumber;
        this.rangerContact = rangerContact;
    }

    //generate setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public int getRangerBadgeNumber() {
        return rangerBadgeNumber;
    }

    public void setRangerBadgeNumber(int rangerBadgeNumber) {
        this.rangerBadgeNumber = rangerBadgeNumber;
    }

    public String getRangerContact() {
        return rangerContact;
    }

    public void setRangerContact(String rangerContact) {
        this.rangerContact = rangerContact;
    }
}
