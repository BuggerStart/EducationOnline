package com.bugstart.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugstart.edu.exception.MiliException;
import com.bugstart.educenter.entity.UcenterMember;
import com.bugstart.educenter.entity.vo.RegisterVo;
import com.bugstart.educenter.mapper.UcenterMemberMapper;
import com.bugstart.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugstart.educenter.utils.MD5;
import com.bugstart.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author bugstart
 * @since 2023-02-02
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new MiliException(20001,"登入失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember==null){
            throw new MiliException(20001,"登入失败");
        }
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new MiliException(20001,"登入失败");
        }
        if (mobileMember.getIsDisabled()){
            throw new MiliException(20001,"登入失败");
        }

        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)||
                StringUtils.isEmpty(code)){
            throw new MiliException(20001,"注册失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer isExist = baseMapper.selectCount(wrapper);
        if (isExist>0){
            throw new MiliException(20001,"手机号已经注册过...");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!redisCode.equals(code)){
            throw new MiliException(20001,"所输入验证码信息错误");
        }
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        member.setIsDisabled(false);
        baseMapper.insert(member);

    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
