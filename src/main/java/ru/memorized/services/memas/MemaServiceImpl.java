package ru.memorized.services.memas;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.memorized.data.dao.mema.IDAOMema;
import ru.memorized.data.entity.Mema;

@Service
@Transactional(readOnly = false)
public class MemaServiceImpl implements MemaService {

    @Autowired
    private IDAOMema dao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Mema> getMemas(int cardId) {
        return dao.getMemas(cardId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMema(Mema mema) {
        mema.setMemaId(dao.saveMema(mema));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Mema> getMemas(int cardId, Date date) {
        return dao.getMemas(cardId, date);
    }

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void deleteMema(Mema mema) {
		dao.deleteMema(mema);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public Mema getMema(Long memaId) {
		return dao.getMema(memaId);
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAllMemas(Set<Mema> memas) {
        dao.deleteAllMemas(memas);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMema(Mema mema) {
        dao.updateMema(mema);
    }

}
