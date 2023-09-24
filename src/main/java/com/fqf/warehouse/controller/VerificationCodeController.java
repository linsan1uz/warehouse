package com.fqf.warehouse.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class VerificationCodeController {
    @Resource(name="captchaProducer")
    private Producer producer;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/captcha/captchaImage")
    public void CaptchaImage(HttpServletResponse response){
        ServletOutputStream out = null;
        //将验证图片写给前端
        try {
            //生成验证码图片的文件
            String text = producer.createText();
            //使用验证码文本生成验证码 --bufferedImage对象就代表生成的验证码图片
            BufferedImage image = producer.createImage(text);
            //将验证码存放到redis
            redisTemplate.opsForValue().set(text,"",60*30, TimeUnit.SECONDS);

             /** 将验证码响应给前端
             *
             * 设置响应正文image/jpeg
             * */
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            ImageIO.write(image,"jpg",out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
