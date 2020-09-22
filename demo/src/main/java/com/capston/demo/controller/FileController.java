package com.capston.demo.controller;

import com.capston.demo.request.FileDto;
import com.capston.demo.request.FileList;
import com.capston.demo.service.FileService;
import com.capston.demo.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public RedirectView addFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            long size = file.getSize();
            System.out.println(size);
            String hashedFileName = new MD5Generator(fileName).toString();
            
            // 실행되는 위치의 files 폴더에 파일이 저장
            String savePath = System.getProperty("user.dir") + "\\files";

            // 파일이 저장되는 폴더가 없으면 폴더를 생성
            if(!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            String filePath = savePath + "\\" + hashedFileName;
            file.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setHashedFileName(hashedFileName);
            fileDto.setFileName(fileName);
            fileDto.setFilePath(filePath);
            fileDto.setFileSize(size);
            fileDto.setPrice((int) (size / 1024 / 1024));

            long file_id = fileService.saveFile(fileDto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RedirectView("/home");
    }

    @GetMapping(value = "/download/{fileId}", produces = "application/json; charset=utf8")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") long id) throws IOException {
        FileList fileList = fileService.getFile(id);
        Path path = Paths.get(fileList.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileList.getFileName() + ";")
                .body(resource);
    }

}
