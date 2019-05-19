package com.hgx.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId
    private Long id;

    private String name;

    private String gender;

    private Date birth;

    @TableLogic
    private Integer logicFlag ;

    @Version
    private Integer version;

}
