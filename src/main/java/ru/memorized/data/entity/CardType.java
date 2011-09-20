package ru.memorized.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
/**
 * Сущность типа карты.
 *
 * @author Evgenij_Kozhevnikov
 * */
public class CardType implements Serializable {

    private static final long serialVersionUID = 1172765188417509931L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_type_id")
    private Long cardTypeId;

    @Column(nullable = false)
    private String name;

    private String description;

    public Long getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
