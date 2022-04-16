package com.cll.graduation.project.vo;

import lombok.Data;

@Data
public class CurrentUser {

    private static ThreadLocal<LoginUser> currentUser = new ThreadLocal<>();

    public static void remove() {
        currentUser.remove();
    }

    public static void set(LoginUser user) {
        currentUser.set(user);
    }

    public static LoginUser get() {
        return currentUser.get();
    }
}
