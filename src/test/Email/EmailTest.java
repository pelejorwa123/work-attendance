package Email;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
*@author: pele
*@time: 2017/11/1 21:34
*@project: work-attendance
*@description:测试发送邮件
*/
public class EmailTest {

    @Test
    public void send(){
        ApplicationContext apc = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mail.xml");
        JavaMailSenderImpl sender = (JavaMailSenderImpl) apc.getBean(JavaMailSenderImpl.class);
        SimpleMailMessage mail = new SimpleMailMessage();
        try{
            System.out.println("开始发送");
            mail.setFrom("1461775766@qq.com");
            mail.setTo("1461775766@qq.com");
            mail.setSubject("测试主题");
            mail.setText("send email by java");
            sender.send(mail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void sendHtmlEmail() throws MessagingException {
        ApplicationContext apc = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mail.xml");
        JavaMailSenderImpl sender = (JavaMailSenderImpl) apc.getBean(JavaMailSenderImpl.class);
        InternetAddress fromAddress = new InternetAddress("1461775766@qq.com");
        InternetAddress toAddress = new InternetAddress("1461775766@qq.com");
        String content = "<p>激活测试</p>" +
                "<a href=\"http://www.baidu.com\">激活</a>";
        MimeMessage emailMessage = sender.createMimeMessage();
        emailMessage.setFrom(fromAddress);
        emailMessage.addRecipient(MimeMessage.RecipientType.TO,toAddress);
        emailMessage.setSubject("测试html邮件");
        emailMessage.setContent(content,"text/html;charset=utf-8");
        sender.send(emailMessage);
    }
}
