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

/**
 * Entity для сущности Документ
 * @autor Artyom Karkavin
 */
@Entity
@Table(name = "doc", catalog = "public")
public class Doc {
    /** Поле: код документа */
    @Id
    @Column(name = "code")
    private Short code;

    /** Поле: наименование */
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    /** Поле: связь с сущностью Сотрудник */
    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    /**
     * Конструктор
     */
    public Doc() {

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
        user.setDoc(this);
    }

    public void removeOffice(User user) {
        getUsers().remove(user);
        user.setDoc(null);
    }
}