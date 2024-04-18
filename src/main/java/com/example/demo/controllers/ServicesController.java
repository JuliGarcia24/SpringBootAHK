package com.example.demo.controllers;

import com.example.demo.models.dto.ServiceDTO;
import com.example.demo.models.dto.ServiceResponseDTO;
import com.example.demo.models.entities.services.Service;
import com.example.demo.models.repositories.IServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private IServiceRepository serviceRepository;

    @GetMapping()
    public ResponseEntity<List<ServiceResponseDTO>> getServices(){

        List<Service> services = this.serviceRepository.findAll();
        List<ServiceResponseDTO> dtos = services
                        .stream()
                        .map(s -> ServiceResponseDTO.createFrom(s))
                        .toList();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    // Devolver un solo servicio
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> findById(@PathVariable int id){

        Optional<Service> service = this.serviceRepository.findById(Long.valueOf(id));

        if(service.isEmpty()){
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ServiceResponseDTO.createFrom(service.get()),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable int id){
        Optional<Service> service = this.serviceRepository.findById(Long.valueOf(id));

        if(service.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        this.serviceRepository.delete(service.get());
        return new ResponseEntity<>("has been deleted my pana", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ServiceResponseDTO> createService(@RequestBody ServiceDTO serviceDTO){
        Service newService = serviceDTO.createService();
        this.serviceRepository.save(newService);

        return new ResponseEntity<>(ServiceResponseDTO.createFrom(newService), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> modifyService(@RequestBody ServiceDTO serviceDTO, @PathVariable int id){
        Optional<Service> service = this.serviceRepository.findById(Long.valueOf(id));

        if(service.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        service.get().setName(serviceDTO.getName());
        service.get().setIcon(serviceDTO.getIcon());
        this.serviceRepository.save(service.get());

        return new ResponseEntity<>(ServiceResponseDTO.createFrom(service.get()), HttpStatus.OK);

    }

}
