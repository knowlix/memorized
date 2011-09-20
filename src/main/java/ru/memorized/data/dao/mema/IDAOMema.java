package ru.memorized.data.dao.mema;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.memorized.data.entity.Mema;

public interface IDAOMema {

    public static final String TABLE_NAME = "Mema";

    /**
     * Получение всех мем.
     *
     * @return список всех мем
     *
     * */
    public List<Mema> getMemas();

    /**
     * Возвращает мему по её идентификатору.
     *
     * @param memaId
     * 			- идентификатор мемы
     *
     * */
    public Mema getMema(Long memaId);

    /**
     * Получение мем, принадлежащих карте с id cardId.
     *
     * @param cardId
     *            идентификатор карты
     * @return список мем
     *
     * */
    public List<Mema> getMemas(int cardId);

    /**
     * Все мемы, дата которых позднее указанной даты.
     *
     * @param date
     *            сравниваемая дата
     *
     * @return список мем, удовлетворяющих условию
     *
     * */
    public List<Mema> getMemas(int cardId, Date date);

    /**
     * Сохранение мемы в базе.
     *
     * @param mema
     *            объект мемы с данными для сохранения
     * @return возвращает идентификатор сгенерированной мемы
     * */
    public Long saveMema(Mema mema);

    /**
     * Обновление мемы в базе.
     *
     * @param mema
     *            - объект мемы с новыми данными
     * */
    public void updateMema(Mema mema);

    /**
     * Удаление мемы из базы данных.
     *
     * @param mema
     * 		- объект мемы {@link ru.memorized.data.entity.Mema}
     *
     * */
    public void deleteMema(Mema mema);

    /**
     * Удаление всех мем.
     *
     * @param memas
     *            - список мем, которые нужно удалить
     *
     * */
    public void deleteAllMemas(Set<Mema> memas);

}
