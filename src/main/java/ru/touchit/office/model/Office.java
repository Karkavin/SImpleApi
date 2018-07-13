package ru.touchit.office.model;

import ru.touchit.organisation.model.Organisation;
import ru.touchit.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity для сущности Офис
 * @autor Artyom Karkavin
 */
@Entity
@Table(name = "office", catalog = "public")
public class Office {
    /** Поле: идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Поле: наименование */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /** Поле: адрес */
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    /** Поле: телефон */
    @Column(name = "phone", length = 20)
    private String phone;

    /** Поле: статус офиса (активен ли) */
    @Column(name = "is_active")
    private boolean isActive = true;

    /** Поле: связь с сущностью Организация */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organisation organisation;

    /** Поле: связь с сущностью Сотрудник */
    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    /**
     * Конструктор
     */
    public Office() {

    }

    /**
     * Конструктор
     */
    public Office(Organisation organisation, String name, String address, String phone, boolean isActive) {
        this.organisation = organisation;
        this.name = name;
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

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Set<User> getUsers() {
        if (users == null){
            users = new HashSet<>();
        }
        return users;
    }

    public void addUser(User user) {
        getUsers().add(user);
        user.setOffice(this);
    }

    public void removeOffice(User user) {
        getUsers().remove(user);
        user.setOffice(null);
    }
}