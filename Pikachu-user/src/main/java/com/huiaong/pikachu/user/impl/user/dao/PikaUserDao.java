package com.huiaong.pikachu.user.impl.user.dao;

import com.google.common.base.Strings;
import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import com.huiaong.pikachu.common.util.JsonMapper;
import com.huiaong.pikachu.user.constant.PikaUserConstant;
import com.huiaong.pikachu.user.user.dto.PikaLoginDTO;
import com.huiaong.pikachu.user.user.enums.PikaLoginType;
import com.huiaong.pikachu.user.user.model.PikaUser;
import lombok.AllArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
public class PikaUserDao extends MyBatisDao<PikaUser> {
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;

    public PikaLoginDTO findByToken(String token) {
        String pikaLoginDTOJson = redisTemplate.opsForValue().get(PikaUserConstant.SESSION + token);
        if (Strings.isNullOrEmpty(pikaLoginDTOJson)) return null;
        return JsonMapper.nonDefaultMapper().fromJson(pikaLoginDTOJson, PikaLoginDTO.class);
    }

    public PikaUser findByName(String loginName) {
        return sqlSession.selectOne(sqlId("findByName"), loginName);
    }

    public PikaUser findByEmail(String loginName) {
        return sqlSession.selectOne(sqlId("findByEmail"), loginName);
    }

    public PikaUser findByMobile(String loginName) {
        return sqlSession.selectOne(sqlId("findByMobile"), loginName);
    }

    public void cacheToken(PikaLoginDTO pikaLoginDTO) {
        redisTemplate.opsForValue().set(PikaUserConstant.SESSION + pikaLoginDTO.getToken(),
                JsonMapper.nonDefaultMapper().toJson(pikaLoginDTO), PikaUserConstant.EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    public Boolean createByName(PikaUser user) {
        return sqlSession.insert(sqlId("createByName"), user) == 1;
    }

    public Boolean createByEmail(PikaUser user) {
        return sqlSession.insert(sqlId("createByEmail"), user) == 1;
    }

    public Boolean createByMobile(PikaUser user) {
        return sqlSession.insert(sqlId("createByMobile"), user) == 1;
    }

    public Boolean deleteToken(String token) {
        return redisTemplate.delete(PikaUserConstant.SESSION + token);
    }
}
