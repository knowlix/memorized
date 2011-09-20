package ru.memorized.data.dao.userinfo;

import ru.memorized.data.entity.UserInfo;

public interface IDAOUserInfo {

    public static final String TABLE_NAME = "UserInfo";

    /**
     * Получение дополнительной информации о пользователе по его логину.
     *
     * @param username
     *            - логин пользователя
     * @return дополнительная информация о пользователе
     * */
    UserInfo getByUsername(String username);

    /**
     * Сохранение или обновление дополнительной информации о пользователе.
     *
     * @param userInfo
     *            - дополнительная информация о пользователе
     * */
    void saveOrUpdate(UserInfo userInfo);

}
