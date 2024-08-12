package com.fleet.managament.parameters.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fleet.managament.uac.dtos.Audit;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;



@Data
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommonObject extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String details;

    private CommonObject(){

    }
    public CommonObject(Long id,String description, String details) {
        this.id = id;
        this.description = description;
        this.details = details;
    }

    @Override
    public String toString() {
        return "CommonObject [id=" + id + ", description=" + description + ", details=" + details + "]";
    }
}
