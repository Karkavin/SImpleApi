package ru.touchit.api.office.view;

import ru.touchit.api.office.model.Office;

import javax.validation.constraints.NotNull;

/**
 * View для представления запрашиваемых фильтров {@link Office}
 * @author Artyom Karkavin
 */
public class FilterOfficeView {
    /** Поле: идентификатор организации */
    @NotNull
    private long orgId;

    /** Поле: наименование */
    private String name;

    /** Поле: телефон */
    private String phone;

    /** Поле: статус офиса (активен ли) */
    private Boolean isActive;

    /**
     * Конструктор
     */
    public FilterOfficeView() {

    }

    /**
     * Конструктор
     * @param orgId идентификатор организации
     * @param name наименование
     * @param phone телефон
     * @param isActive статус офиса (активен ли)
     */
    public FilterOfficeView(long orgId, String name, String phone, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.isActive = isActive;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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