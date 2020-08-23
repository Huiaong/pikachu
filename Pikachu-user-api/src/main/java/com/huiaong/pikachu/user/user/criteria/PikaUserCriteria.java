package com.huiaong.pikachu.user.user.criteria;


import com.huiaong.pikachu.common.pager.PagingCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PikaUserCriteria extends PagingCriteria implements Serializable {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    /**
     * @see com.huiaong.pikachu.user.user.enums.PikaUserType
     */
    private Integer type;

    /**
     * @see com.huiaong.pikachu.user.user.enums.PikaUserStatus
     */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
