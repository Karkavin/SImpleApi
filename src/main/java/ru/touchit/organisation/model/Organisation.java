package ru.touchit.organisation.model;

import ru.touchit.office.model.Office;
import ru.touchit.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity для сущности Организация
 * @autor Artyom Karkavin
 */
@Entity
@Table(name = "organisation", catalog = "public")
public class Organisation {
    /** Поле: идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Поле: наименование */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /** Поле: полное наименование */
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    /** Поле: ИНН */
    @Column(name = "inn", nullable = false, length = 10)
    private String inn;

    /** Поле: КПП */
    @Column(name = "kpp", nullable = false, length = 9)
    private String kpp;

    /** Поле: адрес регистрации организации */
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    /** Поле: телефон */
    @Column(name = "phone", length = 20)
    private String phone;

    /** Поле: статус (действующая ли) */
    @Column(name = "is_active")
    private boolean isActive = true;

    /** Поле: связь с сущностью Офис */
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    /** Поле: связь с сущностью Сотрудник */
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    /**
     * Конструктор
     */
    public Organisation() {

    }

    /**
     * Конструктор
     */
    public Organisation(String name, String fullName, String inn, String kpp, String address, String phone,
                        boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public Set<Office> getOffices() {
        if (offices == null){
            offices = new HashSet<>();
        }
        return offices;
    }

    public void addOffice(Office office) {
        getOffices().add(office);
        office.setOrganisation(this);
    }

    public void removeOffice(Office office) {
        getOffices().remove(office);
        office.setOrganisation(null);
    }

    public Set<User> getUsers() {
        if (users == null){
            users = new HashSet<>();
        }
        return users;
    }

    public void addUser(User user) {
        getUsers().add(user);
        user.setOrganisation(this);
    }

    public void removeOffice(User user) {
        getUsers().remove(user);
        user.setOrganisation(null);
    }
}