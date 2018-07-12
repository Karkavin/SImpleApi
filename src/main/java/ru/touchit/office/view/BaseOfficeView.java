package ru.touchit.office.view;

import ru.touchit.office.model.Office;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BaseOfficeView {
    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    private String address;

    @Size(max = 20)
    private String phone;

    private Boolean isActive = true;

    public BaseOfficeView() {

    }

    public BaseOfficeView(Office office) {
        this.name = office.getName();
        this.address = office.getAddress();
        this.phone = office.getPhone();
        this.isActive = office.getIsActive();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}