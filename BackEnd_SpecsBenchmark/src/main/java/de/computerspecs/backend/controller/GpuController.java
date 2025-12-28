package de.computerspecs.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.computerspecs.backend.dto.GpuDTO;
import de.computerspecs.backend.service.GpuService;

/**
 * rest controller for interaction with gpu db
 */

@RestController
@RequestMapping("/api/gpu")
public class GpuController {

    @Autowired
    GpuService gpuService;
    
    /**
     * gets all gpu names from db
     * @return gpu names
     */

    @GetMapping("/namelist")
    public List<String> getGpuNameList() {
        return gpuService.getGpuNameList();
    }

    /**
     * gets all gpus from db by gpu name
     * @param name
     * @return gpu
     */

    @GetMapping("/getByName")
    public GpuDTO getGpuByName(@RequestParam String name) {
        return gpuService.getGpuByName(name);
    }
}
