package com.leaf.utils;

import com.leaf.dto.UserDto;

/**
 * @author Hanami
 * @Date 2023-02-21
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
