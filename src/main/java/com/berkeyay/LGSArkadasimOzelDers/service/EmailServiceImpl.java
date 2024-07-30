package com.berkeyay.LGSArkadasimOzelDers.service;

import com.berkeyay.LGSArkadasimOzelDers.entity.Application;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{

    private String rootMail = "lgsarkadasimprojesi@gmail.com";
    private String rootMailTest = "yayberke2007@gmail.com";
    @Autowired
    private JavaMailSender mailSender;

    public void sendApplicationEmail(String to, Application application, List<MultipartFile> questions) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setBcc(rootMailTest);

        //helper.setText(buildEmailBody(application));
        String emailContent = "";
        System.out.println(application.getChosenClass());
        if("mentorship".equalsIgnoreCase(application.getChosenClass())){
            helper.setSubject("LGS Arkadaşım Bire Bir Mentörlük Başvurusu");
            emailContent = "<!DOCTYPE html><html><head><style>"
                    + "body { font-family: 'Times New Roman', 'Georgia', serif; }"
                    + "</style></head><body>"
                    + "Sevgili LGS Öğrencisi, <br><br>"
                    + "Mentör talebiniz alınmıştır. Ekibimiz size en yakın zamanda konuyla ilgili geri dönüş yapacaktır. Başvuru detaylarını aşağıda bulabilirsiniz: <br><br>" +
                    "İstenilen mentörün okulu: " + "<br><br>" +
                    "1. Tercih: " + application.getFirstPreferredSchool() + "<br>" +
                    "2. Tercih: " + application.getSecondPreferredSchool() + "<br>" +
                    "3. Tercih: " + application.getThirdPreferredSchool() + "<br><br>" +
                    "Mentörle ilk görüşmenin tarihi ve saati: " + application.getDate() + " " + application.getTime() + "<br><br>" +
                    "Öğrencinin adı: " + application.getStudentName() + "<br>" +
                    "Velinin adı: " + application.getParentName() + "<br>" +
                    "İletişim numarası: " + application.getPhoneNumber() + "<br><br>" +
                    "Ek bilgi: " + application.getAdditionalInformation() + "<br><br>" +
                     "LGS Arkadaşım Ekibi" + "<br>"
                    + "+90 (533) 150 02 97" + "<br>"
                    + "lgsarkadasimprojesi@gmail.com" + "<br>"
                    + "</body></html>";
        }
        else{
            helper.setSubject("LGS Arkadaşım Özel Ders Başvurusu");
            emailContent = "<!DOCTYPE html><html><head><style>"
                    + "body { font-family: 'Times New Roman', 'Georgia', serif; }"
                    + "</style></head><body>"
                    + "Sevgili LGS Öğrencisi, <br><br>"
                    + "Özel Ders Başvuru detaylarını aşağıda bulabilirsiniz: <br><br>" +
                    "Seçilen ders: " + application.getChosenClass() + "<br>" +
                    "Seçilen konular: " + application.getFirstChosenTopic() + ", " + application.getSecondChosenTopic() + "<br>" +
                    "Dersin tarihi ve saati: " + application.getDate() + " " + application.getTime() + "<br><br>" +
                    "Öğrencinin adı: " + application.getStudentName() + "<br>" +
                    "Velinin adı: " + application.getParentName() + "<br>" +
                    "İletişim numarası: " + application.getPhoneNumber() + "<br><br>" +
                    "Dersi verecek mentörün okulu: " + "<br><br>" +
                    "1. Tercih: " + application.getFirstPreferredSchool() + "<br>" +
                    "2. Tercih: " + application.getSecondPreferredSchool() + "<br>" +
                    "3. Tercih: " + application.getThirdPreferredSchool() + "<br><br>" +
                    "Ek bilgi: " + application.getAdditionalInformation() + "<br><br>"
                    + "LGS Arkadaşım Ekibi" + "<br>"
                    + "+90 (533) 150 02 97" + "<br>"
                    + "lgsarkadasimprojesi@gmail.com" + "<br>"
                    + "</body></html>";
        }


        helper.setText(emailContent, true);

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
                "Seçilen konular: " + application.getFirstChosenTopic() + ", " + application.getSecondChosenTopic() + "\n" +
                "Dersin tarihi ve saati: " + application.getDate() + " " + application.getTime() + "\n" +
                "Öğrencinin adı: " + application.getStudentName() + "\n" +
                "Velinin adı: " + application.getParentName() + "\n" +
                "İletişim numarası: " + application.getPhoneNumber() + "\n\n" +
                "Dersi verecek öğrencinin okulu: " + "\n" +
                "1. tercih: " + application.getFirstPreferredSchool() + "\n" +
                "2. tercih: " + application.getSecondPreferredSchool() + "\n" +
                "3. tercih: " + application.getThirdPreferredSchool() + "\n" +
                "Ek bilgi: " + application.getAdditionalInformation() + "\n";
    }

}
