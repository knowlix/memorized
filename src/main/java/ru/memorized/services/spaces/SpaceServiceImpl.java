package ru.memorized.services.spaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.memorized.data.dao.space.IDAOSpace;
import ru.memorized.data.entity.Space;

@Service
@Transactional(readOnly = true)
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private IDAOSpace dao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Space> getSpaces() {
        return dao.getSpaces();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Space getSpace(int spaceId) {
        return dao.getSpaces(spaceId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Space getSpace(String title) {
        return dao.getSpace(title);
    }

}
