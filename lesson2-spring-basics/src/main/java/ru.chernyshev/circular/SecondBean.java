package ru.chernyshev.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component

public class SecondBean {

    private FirstBean firstBean;

    @Autowired
    public void setFirstBean(FirstBean firstBean) {
        this.firstBean = firstBean;
    }
}
