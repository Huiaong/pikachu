package com.huiaong.pikachu.admin.vo.user;

import com.huiaong.pikachu.user.auth.model.PikaRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PikaLoginVO implements Serializable {

    private Long id;

    private String name;

    private String token;

    private List<PikaRole> roles;
}
