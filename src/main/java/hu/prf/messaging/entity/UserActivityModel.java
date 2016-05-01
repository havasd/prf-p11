package hu.prf.messaging.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserActivityModel {
    @Id
    private String name;
    private String otherName;
    private Long numOfMsgs;

    public UserActivityModel() {

    }

    public UserActivityModel(String name, Long numOfMsgs) {
        this.name = name;
        this.numOfMsgs = numOfMsgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getnumOfMsgs() {
        return numOfMsgs;
    }

    public void setNumberOfMessages(Long numberOfMessages) {
        this.numOfMsgs = numberOfMessages;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
    
    @Override
    public String toString() {
        return "UserActivityModel [name=" + name + ", numOfMsgs=" + numOfMsgs
                + "]";
    }

}