package com.sample.ratingo.mapper;

import com.sample.ratingo.api.UserDO;
import com.sample.ratingo.repository.User;

public class UserMapper {

    public static UserDO map(User user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.getId());
        userDO.setFirstName(user.getFirstName());
        userDO.setLastName(user.getLastName());
        userDO.setEmail(user.getEmail());
        userDO.setMobNo(user.getMobNo());
        return userDO;
    }

    public static User map(UserDO userDO) {
        User user = new User();
        user.setId(userDO.getId());
        user.setFirstName(userDO.getFirstName());
        user.setLastName(userDO.getLastName());
        user.setEmail(userDO.getEmail());
        user.setMobNo(userDO.getMobNo());
        return user;
    }

}
