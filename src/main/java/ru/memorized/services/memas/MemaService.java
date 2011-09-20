package ru.memorized.services.memas;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.memorized.data.entity.Mema;

public interface MemaService {

	public Mema getMema(Long memaId);

    public List<Mema> getMemas(int cardId);

    public List<Mema> getMemas(int cardId, Date date);

    public void saveMema(Mema mema);

    public void updateMema(Mema mema);

    public void deleteMema(Mema mema);

    public void deleteAllMemas(Set<Mema> memas);

}
