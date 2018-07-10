package ru.touchit.organisation.view;

import ru.touchit.organisation.model.Organisation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BaseOrganisationView {
    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    private String fullName;

    @NotNull
    @Size(min = 10, max = 10)
    private String inn;

    @NotNull
    @Size(min = 9, max = 9)
    private String kpp;

    @NotNull
    @Size(max = 100)
    private String address;

    @Size(max = 20)
    private String phone;

    private Boolean isActive;

    public BaseOrganisationView() {

    }

    public BaseOrganisationView(Organisation organisation) {
        this.name = organisation.getName();
        this.fullName = organisation.getFullName();
        this.inn = organisation.getInn();
        this.kpp = organisation.getKpp();
        this.address = organisation.getAddress();
        this.phone = organisation.getPhone();
        this.isActive = organisation.getIsActive();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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