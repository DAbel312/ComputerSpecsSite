package de.computerspecs.backend.controller;

import de.computerspecs.backend.service.ImageDataService;
import de.computerspecs.backend.entity.ImageData;
import de.computerspecs.backend.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * controller to get or post images
 */

@RestController
@RequestMapping("/api/image")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;

    /**
     * uploads image with multipart file
     * @param file
     * @return
     * @throws IOException
     */

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        ImageDTO response = imageDataService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    /**
     * gets image information with image name
     * @param name
     * @return
     */

    @GetMapping("/info/{name}")
    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    /**
     * gets image with image name
     * @param name
     * @return
     */

    @GetMapping("/get/{id}")
    public ResponseEntity<?>  getImageById(@PathVariable("id") int id){
        byte[] image = imageDataService.getImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping("/getInfo/{name}")
    public List<Long> getImageIdByName(@PathVariable("name") String name) {
        System.out.println("NAMENAMENAME:::" + name);
        return imageDataService.getImageId(name);
    }
}
