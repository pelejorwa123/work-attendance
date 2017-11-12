import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/*
*@author: pele
*@time: 2017/10/24 21:36
*@project: work-attendance
*@description:shiro学习测试类
*/
public class test {
    @Test
    public void testHelloWorld(){
        //1.获取SecurityManager工厂，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //4.登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e) {
            //5.如果身份验证失败，抛出异常
            System.out.println("登录失败");
        }
        //断言用户已经登录，如果不匹配，junit不放行
        Assert.assertEquals(true,subject.isAuthenticated());
        System.out.println("成功登录");
        //6.退出
        subject.logout();
        System.out.println("成功登出");
    }

    @Test
    public void testRealm(){
        //1.获取SecurityManager工厂，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");

        try{
            //4.登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e) {
            //5.如果身份验证失败，抛出异常
            System.out.println("登录失败");
        }
        //断言用户已经登录，如果不匹配，junit不放行
        Assert.assertEquals(true,subject.isAuthenticated());
        System.out.println("成功登录");
        //6.退出
        subject.logout();
        System.out.println("成功登出");
    }

    @Test
    public void testJdbcRealm(){
        //1.获取SecurityManager工厂，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //4.登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e) {
            //5.如果身份验证失败，抛出异常
            System.out.println("登录失败");
        }
        //断言用户已经登录，如果不匹配，junit不放行
        Assert.assertEquals(true,subject.isAuthenticated());
        System.out.println("成功登录");
        //6.退出
        subject.logout();
        System.out.println("成功登出");
    }
}
