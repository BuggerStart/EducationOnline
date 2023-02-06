package com.bugstart.edu.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bugstart
 * @create 2023-01-28 20:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiliException extends RuntimeException{
    private Integer code;

    private String msg;
}
