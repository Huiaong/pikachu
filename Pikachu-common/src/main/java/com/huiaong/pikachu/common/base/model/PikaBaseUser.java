package com.huiaong.pikachu.common.base.model;


import java.io.Serializable;
import java.util.List;

public interface PikaBaseUser extends Serializable {

    Long getId();

    String getName();

    List<String> getRoles();
}
