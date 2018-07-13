package ru.touchit.user.view;

/**
 * View для представления результатов работы фильтров
 * @autor Artyom Karkavin
 */
public class FilterResultUserView {
    /** Поле: идентификатор */
    private long id;

    /** Поле: имя */
    private String firstName;

    /** Поле: фамилия */
    private String secondName;

    /** Поле: отчество */
    private String middleName;

    /** Поле: должность */
    private String position;

    /**
     * Конструктор
     */
    public FilterResultUserView() {

    }

    /**
     * Конструктор
     * @param id идентификатор
     * @param firstName имя
     * @param secondName фамилия
     * @param middleName отчество
     * @param position должность
     */
    public FilterResultUserView(long id, String firstName, String secondName, String middleName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}