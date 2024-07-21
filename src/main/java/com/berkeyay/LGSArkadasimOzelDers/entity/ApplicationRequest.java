package com.berkeyay.LGSArkadasimOzelDers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationRequest {
    private String chosenClass;
    private String chosenTopic;
    private String date;
    private String time;
    private String parentName;
    private String studentName;
    private String email;
    private String phoneNumber;
    private String firstPreferredSchool;
    private String secondPreferredSchool;
    private String thirdPreferredSchool;
    private List<MultipartFile> questions;
}
