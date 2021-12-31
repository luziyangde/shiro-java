package cn.luziyangde.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义 Realm
 * 将 认证 / 授权 数据的来源转为数据库的实现
 *
 * @author yunlong.lu
 * @date 2021/12/31
 **/
public class CustomerRealm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principals 主要收集
     * @return {@link AuthorizationInfo}
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     *
     * @param token 身份验证令牌
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在 token 中获取用户名
        String principal = (String) token.getPrincipal();
        System.out.println(principal);
        // 根据身份信息使用 jdbc mybatis 查询相关数据库
        if ("zs".equals(principal)) {
            // 参数1：返回数据库中正确的用户名 参数1：返回数据库中正确的密码 参数3：提供当前 realm 的名字 this.getName();
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("zs", "123", this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
