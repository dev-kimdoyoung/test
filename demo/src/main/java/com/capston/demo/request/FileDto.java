package com.capston.demo.request;

import com.capston.demo.model.File;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FileDto {

    private long id;
    private String fileName;
    private String hashedFileName;
    private String filePath;
    private long fileSize;
    private int price;

    public File toEntity() {
        return File.builder()
                .file_id(id)
                .fileName(fileName)
                .hashedFileName(hashedFileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .price(price)
                .build();
    }

    @Builder
    public FileDto(long id, String fileName, String hashedFileName, String filePath, long fileSize, int price) {
        this.id = id;
        this.fileName = fileName;
        this.hashedFileName = hashedFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.price = price;
    }
}
