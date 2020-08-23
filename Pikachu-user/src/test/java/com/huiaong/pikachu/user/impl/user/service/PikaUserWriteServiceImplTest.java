package com.huiaong.pikachu.user.impl.user.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.impl.PikachuUserApplicationTests;
import com.huiaong.pikachu.user.user.enums.PikaUserType;
import com.huiaong.pikachu.user.user.model.PikaUser;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PikaUserWriteServiceImplTest extends PikachuUserApplicationTests {

    @Autowired
    private PikaUserWriteService pikaUserWriteService;

    @Test
    public void testRegistry() {
        Response<PikaUser> registry = pikaUserWriteService.registry("admin", "admin", 1, PikaUserType.ADMIN);
        Assert.assertTrue(registry.isSuccess());
        Assert.assertNotNull(registry.getResult());

    }

}