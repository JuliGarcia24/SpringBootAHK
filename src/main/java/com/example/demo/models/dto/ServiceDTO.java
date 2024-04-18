package com.example.demo.models.dto;

import com.example.demo.models.entities.services.Service;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ServiceDTO {
    private String name;
    private String icon;

    public Service createService(){
        return Service
                .builder()
                .name(this.name)
                .icon(this.icon)
                .build();
    }
}
