package com.berkeyay.LGSArkadasimOzelDers.service;

import com.berkeyay.LGSArkadasimOzelDers.entity.Application;
import com.berkeyay.LGSArkadasimOzelDers.entity.ApplicationRequest;
import com.berkeyay.LGSArkadasimOzelDers.repository.ApplicationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmailService emailService;

    private final Path rootLocation = Path.of("src/main/java/com/berkeyay/LGSArkadasimOzelDers/questionFiles");
    @Override
    public Application saveApplication(ApplicationRequest applicationRequest) throws MessagingException, IOException {
        List<String> questionPaths = new ArrayList<>();

        if(applicationRequest.getQuestions()!=null){
            for(MultipartFile file : applicationRequest.getQuestions()){
                String fileName = file.getOriginalFilename();
                try{
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
                    questionPaths.add(fileName);
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }


        Application application = Application.builder()
                        .chosenClass(applicationRequest.getChosenClass())
                        .chosenTopic(applicationRequest.getChosenTopic())
                        .date(applicationRequest.getDate())
                        .time(applicationRequest.getTime())
                        .parentName(applicationRequest.getParentName())
                        .studentName(applicationRequest.getStudentName())
                        .email(applicationRequest.getEmail())
                        .phoneNumber(applicationRequest.getPhoneNumber())
                        .firstPreferredSchool(applicationRequest.getFirstPreferredSchool())
                        .secondPreferredSchool(applicationRequest.getSecondPreferredSchool())
                        .thirdPreferredSchool(applicationRequest.getThirdPreferredSchool())
                        .questions(questionPaths)
                        .build();

        emailService.sendApplicationEmail(application.getEmail(), application, applicationRequest.getQuestions());

        return applicationRepository.save(application);
    }
}
