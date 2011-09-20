/**
 * Сущность пространства
 *
 * @author Evgenij Kozhevnikov
 *
 * 27.02.2011
 *
 * */
package ru.memorized.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.StringEscapeUtils;

@Entity
public class Space implements Serializable {

    private static final long serialVersionUID = -8977885838841287174L;

    @Id
    @Column(name = "space_id")
    private int spaceId;

    private String title;

    public Space() {
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = StringEscapeUtils.escapeHtml(title);
    }

}
