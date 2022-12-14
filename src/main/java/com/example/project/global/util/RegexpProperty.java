package com.example.project.global.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexpProperty {

    public static final String PASSWORD = "(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*()_+-=?/])[a-zA-Z0-9~!@#$%^&*()_+-=?/]{8,30}$";

}