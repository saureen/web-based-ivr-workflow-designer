/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.mail;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author kapil
 */
public class MailService {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;


    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void sendCallNotificationByEmail(final String toMail, final String subject,
            final String mailTemplatePath, final List<File> attachments){
         MimeMessagePreparator preparator = new MimeMessagePreparator() {
         public void prepare(MimeMessage mimeMessage) throws Exception {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setTo(toMail);
            Map model = new HashMap();
            model.put("toMail", toMail);
            String text = VelocityEngineUtils.mergeTemplateIntoString(
               velocityEngine, mailTemplatePath, model);
            message.setText(text, true);
            message.setSubject(subject);

            if(attachments != null && attachments.size() > 0){
                for(File file: attachments){
                    message.addAttachment(file.getName(), file);
                }
            }
         }
      };
      this.mailSender.send(preparator);

    }
}
