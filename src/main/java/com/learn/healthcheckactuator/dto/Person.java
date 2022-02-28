package com.learn.healthcheckactuator.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Person {

    private String firstName="a";
    private Integer id=1;

}
