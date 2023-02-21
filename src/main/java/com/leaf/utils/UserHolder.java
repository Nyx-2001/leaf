package com.leaf.utils;

import com.leaf.dto.UserDto;

/**
 * @author starsofocean
 * date 2023/2/21 15:11
 */
public class UserHolder {
    private static final ThreadLocal<UserDto> tl=new ThreadLocal<>();

    public static void saveUser(UserDto user){
        tl.set(user);
    }

    public static UserDto getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }

}
