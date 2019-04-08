package com.kingboot.order.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    
    private String orderId;
    
    
}
