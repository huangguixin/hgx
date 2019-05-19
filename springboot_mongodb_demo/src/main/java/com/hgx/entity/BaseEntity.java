package com.hgx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Data
public class BaseEntity {

    /**
     * Id
     */
    @Id
    private Long id;

}
