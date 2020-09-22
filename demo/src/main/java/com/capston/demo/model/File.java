package com.capston.demo.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "file_id")
@Table(name = "T_FILE")
@EntityListeners(AuditingEntityListener.class)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long file_id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String hashedFileName;

    @Column(nullable = false)
    private String filePath;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime uploadDate;

    @Column(nullable = false)
    private long fileSize;

    @Column(nullable = true)
    private int price;

    @Builder
    public File(long file_id, String fileName, String hashedFileName, String filePath, LocalDateTime uploadDate, long fileSize, int price) {
        this.file_id = file_id;
        this.fileName = fileName;
        this.hashedFileName = hashedFileName;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
        this.fileSize = fileSize;
        this.price = updatePrice(price);
    }

    private int updatePrice(int price) {
        this.price += price;
        return this.price;
    }
}
