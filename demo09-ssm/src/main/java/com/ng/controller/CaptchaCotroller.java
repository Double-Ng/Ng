package com.ng.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptchaCotroller {


        private Producer kaptchaProducer=null;

        @Autowired
        public void setCaptchaProducer(Producer kaptchaProducer) {
            this.kaptchaProducer = kaptchaProducer;
        }

        @RequestMapping("/getVerifyCode.do")
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
            response.setDateHeader("Expires",0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            String capText = kaptchaProducer.createText();
            request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
            BufferedImage bi = kaptchaProducer.createImage(capText);
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
                ImageIO.write(bi, "jpg", out);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

}
