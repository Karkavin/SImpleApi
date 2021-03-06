package ru.touchit.api.catalog.model;

import ru.touchit.api.user.model.UserDoc;

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
 * @author Artyom Karkavin
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

    /** Поле: связь с сущностью Документ Сотрудника */
    @OneToMany(mappedBy = "doc")
    private Set<UserDoc> userDocs;

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

    public Set<UserDoc> getUserDocs() {
        if (userDocs == null){
            userDocs = new HashSet<>();
        }
        return userDocs;
    }

    public void addUserDoc(UserDoc userDoc) {
        getUserDocs().add(userDoc);
        userDoc.setDoc(this);
    }

    public void removeUserDoc(UserDoc userDoc) {
        getUserDocs().remove(userDoc);
        userDoc.setDoc(null);
    }
}