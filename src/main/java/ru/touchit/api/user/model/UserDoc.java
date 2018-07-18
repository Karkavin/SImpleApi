package ru.touchit.api.user.model;

import ru.touchit.api.catalog.model.Doc;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Entity для сущности Документ Сотрудника
 * @author Artyom Karkavin
 */
@Entity
@Table(name = "user_doc", catalog = "public")
public class UserDoc {
    /** Поле: идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Поле: номер документа */
    @Column(name = "doc_number", length = 30)
    private String docNumber;

    /** Поле: дата выдачи документа */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /** Поле: связь с сущностью Документ */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code")
    private Doc doc;

    /** Поле: связь с сущностью Сотрудник */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Конструктор
     */
    public UserDoc() {

    }

    /**
     * Конструктор
     * @see Doc
     */
    public UserDoc(String docNumber, Date docDate, Doc doc) {
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.doc = doc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}