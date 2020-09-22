package com.capston.demo.request;

import com.capston.demo.model.File;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileList {
    private long id;
    private String fileName;
    private String filePath;
    private LocalDateTime uploadDate;
    private long fileSize;

    public File toEntity() {
        return File.builder()
                .file_id(id)
                .fileName(fileName)
                .filePath(filePath)
                .uploadDate(uploadDate)
                .build();
    }

    @Builder
    public FileList(long id, String fileName, String filePath, LocalDateTime uploadDate, long fileSize, int price) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
        this.fileSize = fileSize;
    }
}
