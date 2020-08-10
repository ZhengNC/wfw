package com.qixi.wfw.user.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author ZhengNC
 * @date 2020/6/18 10:38
 */
@ApiModel("用户账户实体")
@Data
@Table(name = "user_account")
public class UserAccount {

    @ApiModelProperty("用户ID")
    @Column(name = "user_id")
    @Id
    private Integer userId;

    @ApiModelProperty("用户手机号（登录账号）")
    @Column(name = "user_phone")
    private String userPhone;

    @ApiModelProperty("用户名")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty("密码")
    @Column(name = "user_password")
    private String userPassword;

    @ApiModelProperty("账户状态（1 有效（默认），2 禁用，3 注销）")
    @Column(name = "account_state")
    private Integer accountState;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
