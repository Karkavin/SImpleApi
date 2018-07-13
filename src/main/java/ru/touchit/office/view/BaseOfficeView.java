package ru.touchit.office.view;

import ru.touchit.office.model.Office;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View для представления подробной информации об офисе (без id)
 * @autor Artyom Karkavin
 */
public class BaseOfficeView {
    /** Поле: наименование */
    @NotNull
    @Size(max = 50)
    private String name;

    /** Поле: адрес */
    @NotNull
    @Size(max = 100)
    private String address;

    /** Поле: телефон */
    @Size(max = 20)
    private String phone;

    /** Поле: статус офиса (активен ли) */
    private Boolean isActive = true;

    /** Поле: идентификатор организации */
    private long orgId;

    /**
     * Конструктор
     */
    public BaseOfficeView() {

    }

    /**
     * Конструктор
     * @param office Entity сущности Офис
     */
    public BaseOfficeView(Office office) {
        this.name = office.getName();
        this.address = office.getAddress();
        this.phone = office.getPhone();
        this.isActive = office.getIsActive();
        this.orgId = office.getOrganisation().getId();
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

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
}