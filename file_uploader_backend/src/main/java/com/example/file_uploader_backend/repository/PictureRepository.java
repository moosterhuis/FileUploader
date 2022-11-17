package com.example.file_uploader_backend.repository;

import com.example.file_uploader_backend.domain.DatabaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<DatabaseFile, String> {
}
