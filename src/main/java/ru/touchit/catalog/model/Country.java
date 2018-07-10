package ru.touchit.catalog.model;

import ru.touchit.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country", catalog = "public")
public class Country {
    @Id
    @Column(name = "code")
    private Short code;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    public Country() {

    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        if (users == null){
            users = new HashSet<>();
        }
        return users;
    }

    public void addUser(User user) {
        getUsers().add(user);
        user.setCountry(this);
    }

    public void removeOffice(User user) {
        getUsers().remove(user);
        user.setCountry(null);
    }
}