package de.computerspecs.backend.service;

import de.computerspecs.backend.entity.ImageData;
import de.computerspecs.backend.repository.ImageDataRepository;
import de.computerspecs.backend.dto.ImageDTO;
import de.computerspecs.backend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * service class for interacting with images
 */

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    /**
     * uploads image with multipart file 
     * @param file
     * @return
     * @throws IOException
     */

    public ImageDTO uploadImage(MultipartFile file) throws IOException {

        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ImageDTO("Image uploaded successfully: " +
                file.getOriginalFilename());

    }

/**
 * gets image information with image name
 * @param name
 * @return
 */

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    /**
     * gets image
     * @param name
     * @return
     */

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }

    public List<Long> getImageId(String name) {
        return imageDataRepository.findImageIdsByName(name);
    }

}
