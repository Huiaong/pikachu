package com.huiaong.pikachu.resources.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode()
public class PikaPictureFile implements Serializable {

    private byte[] fileData;

    private String filePrefix;

    private String fileSuffix;

}
