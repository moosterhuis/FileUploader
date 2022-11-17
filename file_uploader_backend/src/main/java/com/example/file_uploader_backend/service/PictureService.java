package com.example.file_uploader_backend.service;

import com.example.file_uploader_backend.domain.DatabaseFile;
import com.example.file_uploader_backend.domain.ResponseFile;
import com.example.file_uploader_backend.repository.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public List<ResponseFile> getAllPictures() {
        List<ResponseFile> files = pictureRepository.findAll().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).toList();

        return files;
    }

    public DatabaseFile getPicture(String uuid) {
        return pictureRepository.findById(uuid).orElseThrow();
    }

    public DatabaseFile addPicture(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DatabaseFile databaseFile = new DatabaseFile(
                UUID.randomUUID().toString(),
                fileName,
                file.getContentType(),
                file.getBytes());
        return pictureRepository.save(databaseFile);
    }

    public void deletePicture(String id) {
        pictureRepository.deleteById(id);
    }
}
