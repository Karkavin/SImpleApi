package ru.touchit.user.view;

import javax.validation.constraints.NotNull;

/**
 * View для представления запрашиваемых фильтров
 * @autor Artyom Karkavin
 */
public class FilterUserView {
    /** Поле: идентификатор офиса */
    @NotNull
    private long offId;

    /** Поле: имя */
    private String firstName;

    /** Поле: фамилия */
    private String secondName;

    /** Поле: отчество */
    private String middleName;

    /** Поле: должность */
    private String position;

    /** Поле: код документа */
    private Short docCode;

    /** Поле: код страны */
    private Short citizenshipCode;

    /**
     * Конструктор
     */
    public FilterUserView() {

    }

    /**
     * Конструктор
     * @param offId идентификатор офиса
     * @param firstName имя
     * @param secondName фамилия
     * @param middleName отчество
     * @param position должность
     * @param docCode код документа
     * @param citizenshipCode код страны
     */
    public FilterUserView(long offId, String firstName, String secondName,
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