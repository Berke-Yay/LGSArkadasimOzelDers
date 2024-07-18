package com.berkeyay.LGSArkadasimOzelDers.service;

import com.berkeyay.LGSArkadasimOzelDers.entity.Application;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{

    private String rootMail = "yayberke2007@gmail.com";
    @Autowired
    private JavaMailSender mailSender;

    public void sendApplicationEmail(String to, Application application, List<MultipartFile> questions) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setBcc(rootMail);
        helper.setSubject("LGS Arkadaşım Özel Ders Başvurusu");
        helper.setText(buildEmailBody(application));

        if(questions!=null){
            for(MultipartFile question : questions){
                helper.addAttachment(question.getOriginalFilename(), new ByteArrayResource(question.getBytes()));
            }
        }

        mailSender.send(message);
    }

    public String buildEmailBody(Application application){
        return "Özel Ders Başvuru detaylarını aşağıda bulabilirsiniz: \n" +
                "Seçilen ders: " + application.getChosenClass() + "\n" +
                "Dersin tarihi ve saati: " + application.getDate() + " " + application.getTime() + "\n" +
                "Öğrencinin adı: " + application.getStudentName() + "\n" +
                "Velinin adı: " + application.getParentName() + "\n" +
                "İletişim numarası: " + application.getPhoneNumber() + "\n" +
                "Dersi verecek öğrencinin okulu: " + application.getPreferredSchool();
    }

}
