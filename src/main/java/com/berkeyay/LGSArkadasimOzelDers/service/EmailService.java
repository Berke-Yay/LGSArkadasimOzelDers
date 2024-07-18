package com.berkeyay.LGSArkadasimOzelDers.service;

import com.berkeyay.LGSArkadasimOzelDers.entity.Application;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmailService {
    public void sendApplicationEmail(String to, Application application, List<MultipartFile> questions) throws MessagingException, IOException;
    public String buildEmailBody(Application application);
}
