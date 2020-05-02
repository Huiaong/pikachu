package com.huiaong.pikachu.user.impl.address.service;


import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.common.util.JsonMapper;
import com.huiaong.pikachu.user.address.model.PikaAddress;
import com.huiaong.pikachu.user.address.service.PikaAddressReadService;
import com.huiaong.pikachu.user.impl.PikachuUserApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PikaAddressReadServiceImplTest extends PikachuUserApplicationTests {

    @Autowired
    private PikaAddressReadService pikaAddressReadService;

    @Test
    public void testFindById() {
        Response<PikaAddress> addressResp = pikaAddressReadService.findById(1L);
        Assert.assertTrue(addressResp.isSuccess());
        PikaAddress address = addressResp.getResult();
        Assert.assertNotNull(address);
        log.info("- testFindById test result:{}", JsonMapper.nonDefaultMapper().toJson(address));
    }
}