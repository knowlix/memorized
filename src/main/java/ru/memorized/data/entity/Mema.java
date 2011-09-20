/**
 * Сущность мемы
 *
 * @author Evgenij Kozhevnikov
 *
 * 27.02.2011
 *
 * */
package ru.memorized.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.StringEscapeUtils;

@Entity
public class Mema implements Serializable {

    private static final long serialVersionUID = -4139416001922045488L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mema_id")
    private Long memaId;

    private String title;

    private String description;

    private Date created;

    private int card_id;

    public Mema() {
    }

    public Long getMemaId() {
        return memaId;
    }

    public void setMemaId(Long memaId) {
        this.memaId = memaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = StringEscapeUtils.escapeHtml(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringEscapeUtils.escapeHtml(description);
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
