package com.bugstart.educenter.service;

import com.bugstart.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bugstart.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author bugstart
 * @since 2023-02-02
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
