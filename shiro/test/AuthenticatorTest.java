import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/*
*@author: pele
*@time: 2017/10/25 10:57
*@project: work-attendance
*@description:Authenticator的测试
*/
public class AuthenticatorTest {

    private void login(String configFile) throws AuthenticationException{
        //1.获取SecurityManager工厂，此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        //4.登录，即身份验证
        subject.login(token);
    }

    @Test
    public void testAllSuccesfulStrategyWithSuccess(){
        try {
            login("classpath:shiro-authenticator-all-success.ini");
        }catch (AuthenticationException e){
            System.out.println("登录失败");
        }
        Subject subject = SecurityUtils.getSubject();
        //断言用户已经登录，如果不匹配，junit不放行
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2,principalCollection.asList().size());
        System.out.println("登录成功");
        subject.logout();
        System.out.println("退出成功");

    }

    @Test
    public void testAllSuccesfulStrategyWithFail(){
        try {
            login("classpath:shiro-authenticator-all-fail.ini");
        }catch (AuthenticationException e){
            System.out.println("登录失败");
            System.exit(0);
        }
        Subject subject = SecurityUtils.getSubject();
        //断言用户已经登录，如果不匹配，junit不放行
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2,principalCollection.asList().size());
        System.out.println("登录成功");
        subject.logout();
        System.out.println("退出成功");
    }

}
