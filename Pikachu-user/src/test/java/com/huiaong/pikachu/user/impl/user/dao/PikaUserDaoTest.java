package com.huiaong.pikachu.user.impl.user.dao;

import com.huiaong.pikachu.common.util.JsonMapper;
import com.huiaong.pikachu.user.impl.PikachuUserApplicationTests;
import com.huiaong.pikachu.user.user.dto.PikaLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class PikaUserDaoTest extends PikachuUserApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private PikaUserDao pikaUserDao;

    @Test
    public void testFindByToken() {
        PikaLoginUser hello = pikaUserDao.findByToken("hello1");
        log.info("{}", JsonMapper.nonDefaultMapper().toJson(hello));
//        PikaLoginDTO pikaLoginDTO = new PikaLoginDTO();
//        pikaLoginDTO.setId(1L);
//        pikaLoginDTO.setToken("e8724365-90e7-40e4-b197-d2813170babb");
//        redisTemplate.opsForValue().set("SESSION:hello1", JsonMapper.nonDefaultMapper().toJson(pikaLoginDTO));
    }
}