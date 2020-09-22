package com.capston.demo.service;

import com.capston.demo.model.File;
import com.capston.demo.repository.FileRepository;
import com.capston.demo.request.FileDto;
import com.capston.demo.request.FileList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class FileService {

    private static final long ONE = 1;

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getFile_id();
    }

    public FileList getFile(long id) {
        File file = fileRepository.findById(id).get();

        return FileList.builder()
                .id(id)
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .uploadDate(file.getUploadDate())
                .build();
    }

    public List<FileList> getAllFileList() {
        List<File> list = fileRepository.findAll();

        return getFileList(list);
    }

    public List<FileList> getAllFilePreviousDay() {
        List<File> list = fileRepository.findAllByUploadDateBetween(LocalDateTime.now().minusDays(ONE), LocalDateTime.now());
        return getFileList(list);
    }

    public List<FileList> getAllFileWeekAgo() {
        List<File> list = fileRepository.findAllByUploadDateBetween(LocalDateTime.now().minusWeeks(ONE), LocalDateTime.now());

        return getFileList(list);
    }

    public List<FileList> getAllFileOneMonthAgo() {
        List<File> list = fileRepository.findAllByUploadDateBetween(LocalDateTime.now().minusMonths(ONE), LocalDateTime.now());
        return getFileList(list);
    }

    public int getPrice() {
        Optional<Integer> price = fileRepository.findFirstByOrderByPrice();
        return price.orElse(0);
    }

    private List<FileList> getFileList(final List<File> list) {
        List<FileList> fileList = new ArrayList<>();

        for(File file : list) {
            FileList fileListDto = FileList.builder()
                    .id(file.getFile_id())
                    .fileName(file.getFileName())
                    .filePath(file.getFilePath())
                    .uploadDate(file.getUploadDate())
                    .fileSize(file.getFileSize() / 1024 / 1024)
                    .build();
            fileList.add(fileListDto);
        }

        return fileList;
    }
}
