package test.netty;

import java.io.Serializable;

public class AnimalServiceImp implements IAnimalService, Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -160535222600556362L;

    public String getMoneyName() {
        return "I'am Jackey";
    }
}