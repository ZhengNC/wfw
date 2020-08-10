package com.qixi.wfw.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZhengNC
 * @date 2020/6/18 10:38
 */
@ApiModel("用户账户实体DTO")
@Data
public class UserAccountDto {

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户手机号（登录账号）")
    private String userPhone;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("账户状态（1 有效（默认），2 禁用，3 注销）")
    private Integer accountState;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
