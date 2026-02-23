package de.computerspecs.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.computerspecs.backend.dto.GpuDTO;
import de.computerspecs.backend.repository.GpuRepository;

/**
 * service for interaction with gpu db
 */

@Service
public class GpuService {

    @Autowired
    GpuRepository gpuRepository;

    /**
     * gets all gpu names from db
     * @return gpu names
     */

    public List<String> getGpuNameList() {
        return gpuRepository.getGpuNameList();
    }

    /**
     * gets all gpus from db by gpu name
     * @param name
     * @return gpu
     */

    public GpuDTO getGpuByName(String name) {
        return gpuRepository.findGpuByName(name);
    }
    
}
