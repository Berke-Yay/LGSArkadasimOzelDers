package com.berkeyay.LGSArkadasimOzelDers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {
    @Id
    @SequenceGenerator(
            name = "application_id_sequence",
            sequenceName = "application_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "application_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long applicationId;

    private String chosenClass;
    private String date;
    private String time;
    private String parentName;
    private String studentName;
    private String email;
    private String phoneNumber;
    private String preferredSchool;

    @ElementCollection
    private List<String> questions;

}
