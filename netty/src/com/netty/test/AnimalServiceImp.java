package com.netty.test;

import java.io.Serializable;

public class AnimalServiceImp implements IAnimalService, Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -160535222600556362L;

    @Override
    public String getMoneyName() {
        return "I'am Jackey";
    }
}