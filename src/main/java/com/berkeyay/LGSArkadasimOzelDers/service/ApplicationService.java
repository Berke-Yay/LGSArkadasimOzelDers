package com.berkeyay.LGSArkadasimOzelDers.service;

import com.berkeyay.LGSArkadasimOzelDers.entity.Application;
import com.berkeyay.LGSArkadasimOzelDers.entity.ApplicationRequest;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface ApplicationService {
    Application saveApplication(ApplicationRequest applicationRequest) throws MessagingException, IOException;
}
