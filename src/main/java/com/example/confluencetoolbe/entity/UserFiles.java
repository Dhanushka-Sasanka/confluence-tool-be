package com.example.confluencetoolbe.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userFileId;
    private String trackID;

    private String filePath;

    private String completeStatus;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private User user;

}
