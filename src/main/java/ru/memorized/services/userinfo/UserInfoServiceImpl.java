package ru.memorized.services.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.memorized.data.dao.userinfo.IDAOUserInfo;
import ru.memorized.data.entity.UserInfo;

@Service
@Transactional(readOnly = false)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private IDAOUserInfo dao;

    @Override
    public UserInfo getByUsername(String username) {
        return dao.getByUsername(username);
    }

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        dao.saveOrUpdate(userInfo);
    }

}
