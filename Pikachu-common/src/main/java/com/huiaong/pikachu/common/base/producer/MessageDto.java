package com.huiaong.pikachu.common.base.producer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageDto implements Serializable {

    private Long referenceId;

}
