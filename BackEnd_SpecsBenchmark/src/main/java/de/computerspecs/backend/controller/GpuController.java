package de.computerspecs.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.computerspecs.backend.dto.GpuDTO;
import de.computerspecs.backend.service.GpuService;

@RestController
@RequestMapping("/api/gpu")
public class GpuController {

    @Autowired
    GpuService gpuService;
    
    @GetMapping("/namelist")
    public List<String> getGpuNameList() {
        return gpuService.getGpuNameList();
    }

    @GetMapping("/getByName")
    public GpuDTO getGpuByName(@RequestParam String name) {
        return gpuService.getGpuByName(name);
    }
}
