package ru.touchit.user.view;

import javax.validation.constraints.NotNull;

public class FilterUserView {
    @NotNull
    private long offId;

    private String firstName;

    private String secondName;

    private String middleName;

    private String position;

    private Short docCode;

    private Short citizenshipCode;

    public FilterUserView() {

    }

    public FilterUserView(@NotNull long offId, String firstName, String secondName,
                          String middleName, String position, Short docCode, Short citizenshipCode) {
        this.offId = offId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    public long getOffId() {
        return offId;
    }

    public void setOffId(long offId) {
        this.offId = offId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Short getDocCode() {
        return docCode;
    }

    public void setDocCode(Short docCode) {
        this.docCode = docCode;
    }

    public Short getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Short citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}