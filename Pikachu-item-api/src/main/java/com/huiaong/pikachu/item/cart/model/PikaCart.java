package com.huiaong.pikachu.item.cart.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PikaCart implements Serializable {

    private List<PikaCartItem> items;

}
