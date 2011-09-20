package ru.memorized.services.cardtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.memorized.data.dao.cardtype.IDAOCardType;
import ru.memorized.data.entity.CardType;

@Service
@Transactional
public class CardTypeServiceImpl implements CardTypeService {

    @Autowired
    private IDAOCardType dao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CardType> getCardTypes() {
        return dao.getCardTypes();
    }

}
