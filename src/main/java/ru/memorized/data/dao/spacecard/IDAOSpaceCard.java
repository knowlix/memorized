package ru.memorized.data.dao.spacecard;

import java.util.List;

import ru.memorized.data.entity.Card;
import ru.memorized.data.entity.Space;
import ru.memorized.data.entity.SpaceCard;

public interface IDAOSpaceCard {

    public static final String TABLE_NAME = "SpaceCard";

    public SpaceCard getSpaceCard(Space space, Card card);

    /**
     * Получение всех карт, лежащих на одном пространстве.
     * 
     * @param space
     *            пространство
     * @return список карт на пространстве
     * 
     * */
    public List<SpaceCard> getSpaceCard(Space space);
    
    /**
     * Получение всех карт, лежащих на одном пространстве с учетом флага enabled.
     * @param space
     *          - пространство
     * @param enabled
     *          - флаг доступности
     * @return список карт на прострастве
     * */
    public List<SpaceCard> getSpaceCard(Space space, Boolean enabled);
    
    /**
     * Обновление карты.
     * 
     * @param spaceCard
     *          - карта с новыми параметрами
     * 
     * */
    public void updateSpaceCard(SpaceCard spaceCard);

    /**
     * Сохранение карты на пространстве.
     * 
     * @param spaceCard
     *            объект карты, спроецированный на пространство
     * 
     * */
    public void saveSpaceCard(SpaceCard spaceCard);

    /**
     * Удаление карты с пространства.
     * 
     * @param spaceCard
     *            карта в пространстве
     * 
     * */
    public void deleteSpaceCard(SpaceCard spaceCard);

}
