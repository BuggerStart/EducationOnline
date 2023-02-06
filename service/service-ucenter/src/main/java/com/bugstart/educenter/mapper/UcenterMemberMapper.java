package com.bugstart.educenter.mapper;

import com.bugstart.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author bugstart
 * @since 2023-02-02
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {


    Integer countRegisterDay(String day);
}
