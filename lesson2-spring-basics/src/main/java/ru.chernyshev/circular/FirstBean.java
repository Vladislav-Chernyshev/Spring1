package ru.chernyshev.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class FirstBean {
    private SecondBean secondBean;

    @Autowired
    public FirstBean(SecondBean secondBean) {
        this.secondBean = secondBean;
    }
}
