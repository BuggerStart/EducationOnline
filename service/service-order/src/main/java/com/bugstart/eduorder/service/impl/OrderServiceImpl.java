package com.bugstart.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugstart.eduorder.client.EduClient;
import com.bugstart.eduorder.client.UcenterClient;
import com.bugstart.eduorder.entity.Order;
import com.bugstart.eduorder.mapper.OrderMapper;
import com.bugstart.eduorder.service.OrderService;
import com.bugstart.eduorder.utils.OrderNoUtil;
import com.bugstart.utils.ordervo.CourseWebVoOrder;
import com.bugstart.utils.ordervo.UcenterMemberOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author bugstart
 * @since 2023-02-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private EduClient eduClient;

    @Resource
    private UcenterClient ucenterClient;

    //根据课程id和用户id创建订单，返回订单id
    @Override
    public String createOrders(String courseId, String memberId) {
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
