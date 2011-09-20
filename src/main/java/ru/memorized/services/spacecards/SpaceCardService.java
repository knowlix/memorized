package ru.memorized.services.spacecards;

import java.util.List;

import ru.memorized.data.entity.Card;
import ru.memorized.data.entity.Space;
import ru.memorized.data.entity.SpaceCard;

public interface SpaceCardService {

    public void saveSpaceCard(SpaceCard spaceCard);

    public List<SpaceCard> getSpaceCard(Space space);
    
    public List<SpaceCard> getSpaceCard(Space space, Boolean enabled);
    
    public SpaceCard getSpaceCard(Space space, Card card);
    
    /**
     * Возвращает существующий объект карты, если он есть в базе
     * или новый объект, если его там нет.
     * @param space
     *          - пространство
     * @param card
     *          - карта
     * @return объект карты на пространстве {@link ru.memorized.data.entity.SpaceCard}
     * */
    public SpaceCard getExistsSpaceCardOrCreateNew(Space space, Card card);

    public void deleteSpaceCard(SpaceCard spaceCard);
    
    /**
     * Временное удаление карты с простанства с сохранением всех настроек отображения.
     * @param spaceCard
     *          - карта, которую нужно временно удалить
     * */
    public void disableSpaceCard(SpaceCard spaceCard);
    
    /**
     * Восстановление карты на простанстве после временного удаления.
     * @param spaceCard 
     *          - карта, которую нужно восстановить
     * */
    public void enableSpaceCard(SpaceCard spaceCard);
    
    public void updateSpaceCard(SpaceCard spaceCard);

}
