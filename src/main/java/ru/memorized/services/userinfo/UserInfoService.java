package ru.memorized.services.userinfo;

import ru.memorized.data.entity.UserInfo;

public interface UserInfoService {

    UserInfo getByUsername(String username);

    void saveOrUpdate(UserInfo userInfo);

}
