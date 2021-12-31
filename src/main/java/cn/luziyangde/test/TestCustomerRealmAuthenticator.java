package cn.luziyangde.test;

import cn.luziyangde.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 测试自定义 Realm认证
 *
 * @author yunlong.lu
 * @date 2021/12/31
 **/
public class TestCustomerRealmAuthenticator {
    public static void main(String[] args) {
        // 1、创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2、给安全管理器设置 realm 数据域
        securityManager.setRealm(new CustomerRealm());

        // 3、SecurityUtils 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 4、获取关键对象 Subject 主体
        Subject subject = SecurityUtils.getSubject();

        // 5、创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zs", "123");

        // 6、用户认证
        try {
            System.out.println("认证状态：" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            System.out.println("认证失败：用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("认证失败：密码错误！");
        }
    }
}
