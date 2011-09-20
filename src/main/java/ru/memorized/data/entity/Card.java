/**
 * Сущность карты
 *
 * @author Evgenij Kozhevnikov
 *
 * 27.02.2011
 *
 * */
package ru.memorized.data.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang.StringEscapeUtils;

@Entity
public class Card implements Serializable {

    private static final long serialVersionUID = -5023712067970392130L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int cardId;

    private String title;

    private String description;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    @OrderBy("memaId desc")
    private Set<Mema> memas = new TreeSet<Mema>();

    private String author;

    private String type;

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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

    public Set<Mema> getMemas() {
        return memas;
    }

    public void setMemas(Set<Mema> memas) {
        this.memas = memas;
    }

    /**
     * @return тип карты {@link ru.memorized.data.CardTypes}
     */
    public String getType() {
        return type;
    }

    /**
     * @param тип карты {@link ru.memorized.data.CardTypes}
     */
    public void setType(String type) {
        this.type = type;
    }

}
