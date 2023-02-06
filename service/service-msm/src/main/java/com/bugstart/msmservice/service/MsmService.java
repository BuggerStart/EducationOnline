package com.bugstart.msmservice.service;

import java.util.Map;

/**
 * @author bugstart
 * @create 2023-02-02 0:48
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
