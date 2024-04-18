package com.example.demo.models.dto;

import com.example.demo.models.entities.services.Service;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ServiceResponseDTO {
    private Long id;
    private String name;
    private String icon;

    public static ServiceResponseDTO createFrom(Service service){
        return ServiceResponseDTO
                .builder()
                .id(service.getId())
                .name(service.getName())
                .icon(service.getIcon())
                .build();
    }
}
