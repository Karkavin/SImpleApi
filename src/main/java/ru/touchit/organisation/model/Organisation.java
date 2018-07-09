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
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organisation", catalog = "public")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "inn", nullable = false)
    private Long inn;

    @Column(name = "kpp", nullable = false)
    private Long kpp;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    public Organisation() {

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

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
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