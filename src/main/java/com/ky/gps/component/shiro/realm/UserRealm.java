package com.ky.gps.component.shiro.realm;

import com.ky.gps.enums.SysParamEnum;
import com.ky.gps.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户授权和认证Realm
 *
 * @author Daye
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);
    @Resource
    private SysUserService sysUserService;

    /**
     * 用户身份数组
     */
    private final static String[] LOGIN_TYPE = {"manager", "simple"};

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     *
     * @param token 令牌
     * @return 返回用户信息
     * @throws AuthenticationException 判断用户登录认证失败原因
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. 获取登录类型
        String loginType = SecurityUtils.getSubject().getSession().getAttribute(SysParamEnum.SESSION_LOGIN_TYPE.toString()).toString();
        SecurityUtils.getSubject().getSession().removeAttribute(SysParamEnum.SESSION_LOGIN_TYPE.toString());
        //2. 从token中获取登录的用户名
        String workId = token.getPrincipal().toString();
        //3. 查询数据库
        Map<String, Object> user = null;
        if (LOGIN_TYPE[0].equals(loginType)) {
            //管理员
            user = sysUserService.findAdminBaseInfoByWordId(workId);
        } else if (LOGIN_TYPE[1].equals(loginType)) {
            //普通用户
            user = sysUserService.findBaseInfoByWorkId(workId);
        }
        if (user == null) {
            return null;
        }
        //根据登录类型不同，处理用户信息map中的数据
        // 当前用户信息存到session中
        SecurityUtils.getSubject().getSession().setAttribute(SysParamEnum.SESSION_USER_NAME.toString(), user);
        //返回认证信息
        return new SimpleAuthenticationInfo(user, user.get("password"), getName());
    }
}
