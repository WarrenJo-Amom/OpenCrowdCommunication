package in.amant.opencrowdcommunication.model;

import java.util.Calendar;

public class MessageData {
    private String userName;
    private int userID;
    private String message;
    private boolean right;
    private boolean hideIcon;
    private String sendTime;

    public MessageData() { }

    public MessageData(String userName, int userID, String message, boolean right, boolean hideIcon, String sendTime) {
        this.userName = userName;
        this.userID = userID;
        this.message = message;
        this.right = right;
        this.hideIcon = hideIcon;
        this.sendTime = sendTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isHideIcon() {
        return hideIcon;
    }

    public void setHideIcon(boolean hideIcon) {
        this.hideIcon = hideIcon;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}