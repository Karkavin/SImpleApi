package ru.touchit.api.user.model;

import ru.touchit.api.catalog.model.Country;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.organisation.model.Organisation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity для сущности Сотрудник
 * @author Artyom Karkavin
 */
@Entity
@Table(name = "user", catalog = "public")
public class User {
    /** Поле: идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Поле: имя */
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    /** Поле: фамилия */
    @Column(name = "second_name", length = 30)
    private String secondName;

    /** Поле: отчество */
    @Column(name = "middle_name", length = 30)
    private String middleName;

    /** Поле: должность */
    @Column(name = "position", nullable = false, length = 50)
    private String position;

    /** Поле: телефон */
    @Column(name = "phone", length = 20)
    private String phone;

    /** Поле: идентифицирован ли сотрудник */
    @Column(name = "is_identified")
    private boolean isIdentified = false;

    /** Поле: связь с сущностью Документ Сотрудника */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDoc userDoc;

    /** Поле: связь с сущностью Страна */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    private Country country;

    /** Поле: связь с сущностью Организация */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organisation organisation;

    /** Поле: связь с сущностью Офис */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "off_id", nullable = false)
    private Office office;

    /**
     * Конструктор
     */
    public User() {

    }

    /**
     * Конструктор
     * @see UserDoc
     * @see Country
     * @see Organisation
     * @see Office
     */
    public User(String firstName, String secondName, String middleName, String position, String phone,
                boolean isIdentified, UserDoc userDoc, Country country, Organisation organisation, Office office) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
        setUserDoc(userDoc);
        this.country = country;
        this.organisation = organisation;
        this.office = office;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(boolean isIdentified) {
         this.isIdentified = isIdentified;
    }

    public UserDoc getUserDoc() {
        return userDoc;
    }

    public void setUserDoc(UserDoc userDoc) {
        this.userDoc = userDoc;
        if (userDoc != null) {
            this.userDoc.setUser(this);
        }
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}