package realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/*
*@author: pele
*@time: 2017/10/24 22:56
*@project: work-attendance
*@description:第二个realm
*/
public class myRealm2 implements Realm{
    @Override
    public String getName() {
        return "myRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //只支持UsernamePasswordToken这一种类型的token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到用户名
        String username = (String) authenticationToken.getPrincipal();
        //得到密码
        String password =  new String((char[])authenticationToken.getCredentials());
        //进行比较和验证
        if(!"wang".equals(username)){
            //用户名错误
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            //密码错误
            throw new IncorrectCredentialsException();
        }
        //如果身份验证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
