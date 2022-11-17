package com.example.file_uploader_backend.controller;

import com.example.file_uploader_backend.domain.DatabaseFile;
import com.example.file_uploader_backend.domain.ResponseFile;
import com.example.file_uploader_backend.service.PictureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class pictureController {

    private final PictureService pictureService;

    public pictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping
    ResponseEntity<List<ResponseFile>> getAllPictures() {
        List<ResponseFile> responseFiles = pictureService.getAllPictures();
        return ResponseEntity.ok(responseFiles);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<byte[]> getFile(@PathVariable String uuid) {
        DatabaseFile databaseFile = pictureService.getPicture(uuid);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getName() + "\"")
                .body(databaseFile.getData());
    }

    @PostMapping
    ResponseEntity<DatabaseFile> addPicture(@RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        DatabaseFile savedDatabaseFile = pictureService.addPicture(file);
        return ResponseEntity.ok(savedDatabaseFile);
    }

    @DeleteMapping("/{uuid}")
    ResponseEntity deletePicture(@PathVariable String uuid) {
        pictureService.deletePicture(uuid);
        return ResponseEntity.ok().build();
    }
}
