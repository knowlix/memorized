package ru.memorized.services.spacecards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.memorized.data.dao.spacecard.DAOSpaceCard;
import ru.memorized.data.entity.Card;
import ru.memorized.data.entity.Space;
import ru.memorized.data.entity.SpaceCard;

@Service
public class SpaceCardServiceImpl implements SpaceCardService {

    @Autowired
    private DAOSpaceCard dao;

    @Override
    public void saveSpaceCard(SpaceCard spaceCard) {
        dao.saveSpaceCard(spaceCard);
    }

    @Override
    public SpaceCard getSpaceCard(Space space, Card card) {
        return dao.getSpaceCard(space, card);
    }
    
    @Override
    public List<SpaceCard> getSpaceCard(Space space) {
        return dao.getSpaceCard(space);
    }
    
    @Override
    public List<SpaceCard> getSpaceCard(Space space, Boolean enabled) {
        return dao.getSpaceCard(space, enabled);
    }

    @Override
    public SpaceCard getExistsSpaceCardOrCreateNew(Space space, Card card) {
        SpaceCard spaceCard = dao.getSpaceCard(space, card);
        if (spaceCard == null) {
            spaceCard = new SpaceCard(space, card);
        }
        return spaceCard;
    }
    
    @Override
    public void deleteSpaceCard(SpaceCard spaceCard) {
        dao.deleteSpaceCard(spaceCard);
    }

    @Override
    public void updateSpaceCard(SpaceCard spaceCard) {
        dao.updateSpaceCard(spaceCard);
    }

    @Override
    public void disableSpaceCard(SpaceCard spaceCard) {
        spaceCard.setEnabled(Boolean.FALSE);
        dao.updateSpaceCard(spaceCard);
        
    }

    @Override
    public void enableSpaceCard(SpaceCard spaceCard) {
        spaceCard.setEnabled(Boolean.TRUE);
        dao.updateSpaceCard(spaceCard);
    }

}
