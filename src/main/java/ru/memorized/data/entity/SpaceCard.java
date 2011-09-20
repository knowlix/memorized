/**
 * Расположение карт в пространстве
 *
 * @author Evgenij Kozhevnikov
 *
 * 27.02.2011
 *
 * */
package ru.memorized.data.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class SpaceCard implements Serializable {

    private static final long serialVersionUID = 8928668461625557040L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "space_card_id")
    private int spaceCardId;

    @OneToOne
    @JoinColumn(name = "space_id")
    private Space space;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "left_coord")
    private int left = 20;

    @Column(name = "top_coord")
    private int top = 20;

    private int height = 505;

    private int width = 388;

    @Column(name = "z_index")
    private int zIndex = 0;

    private Boolean enabled = true;

    public SpaceCard() {
    }

    public SpaceCard(Space space, Card card) {
        setSpace(space);
        setCard(card);
    }

    public int getSpaceCardId() {
        return spaceCardId;
    }

    public void setSpaceCardId(int spaceCardId) {
        this.spaceCardId = spaceCardId;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param widht the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the zIndex
     */
    public int getzIndex() {
        return zIndex;
    }

    /**
     * @param zIndex the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
