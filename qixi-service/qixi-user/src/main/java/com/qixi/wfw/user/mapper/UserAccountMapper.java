package com.qixi.wfw.user.mapper;

import com.qixi.wfw.user.entity.po.UserAccount;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ZhengNC
 * @date 2020/6/18 11:24
 */
@Repository
public interface UserAccountMapper extends Mapper<UserAccount> {
}
