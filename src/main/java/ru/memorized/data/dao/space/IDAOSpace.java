package ru.memorized.data.dao.space;

import java.util.List;

import ru.memorized.data.entity.Space;

public interface IDAOSpace {

    public static final String TABLE_NAME = "Space";

    public List<Space> getSpaces();

    /**
     * Получение объекта пространства по идентификатору
     * 
     * @param spaceId
     *            идентификатор пространства
     * 
     * @return объект пространства
     * 
     * */
    public Space getSpaces(int spaceId);

    /**
     * Получение пространства по уникальному имени
     * 
     * @param title
     *            - уникальное имя пространство
     * @return объект пространства
     * 
     * */
    public Space getSpace(String title);

}
