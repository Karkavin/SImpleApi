package ru.touchit.user.view;

import ru.touchit.user.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUserView {
    @NotNull
    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String secondName;

    @Size(max = 30)
    private String middleName;

    @NotNull
    @Size(max = 50)
    private String position;

    @Size(max = 20)
    private String phone;

    @Size(max = 30)
    private String docNumber;

    private String docDate;

    private Boolean isIdentified = false;

    private Short docCode;

    private Short citizenshipCode;

    @NotNull
    private long orgId;

    @NotNull
    private long offId;

    public BaseUserView() {

    }

    public BaseUserView(User user) {
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.middleName = user.getMiddleName();
        this.position = user.getPosition();
        this.phone = user.getPhone();
        this.docNumber = user.getDocNumber();
        Date date = user.getDocDate();
        this.docDate = new SimpleDateFormat("MM-dd-yyyy").format(date);
        this.isIdentified = user.getIsIdentified();
        this.setOrgId(user.getOrganisation().getId());
        this.setOffId(user.getOffice().getId());
        if (user.getDoc() != null) {
            this.docCode = user.getDoc().getCode();
        }
        if (user.getCountry() != null) {
            this.citizenshipCode = user.getCountry().getCode();
        }
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        isIdentified = identified;
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

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public long getOffId() {
        return offId;
    }

    public void setOffId(long offId) {
        this.offId = offId;
    }
}