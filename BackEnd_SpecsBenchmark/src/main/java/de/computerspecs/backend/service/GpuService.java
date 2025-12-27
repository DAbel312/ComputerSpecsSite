package de.computerspecs.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.computerspecs.backend.dto.GpuDTO;
import de.computerspecs.backend.repository.GpuRepository;

@Service
public class GpuService {

    @Autowired
    GpuRepository gpuRepository;

    public List<String> getGpuNameList() {
        return gpuRepository.getGpuNameList();
    }

    public GpuDTO getGpuByName(String name) {
        System.out.println(name);
        return null;
    }
    
}
