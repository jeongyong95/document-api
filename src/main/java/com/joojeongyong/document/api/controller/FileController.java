package com.joojeongyong.document.api.controller;

import com.google.common.collect.Lists;
import com.joojeongyong.document.domain.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/api/files")
@RestController
public class FileController {

  private final FileService fileService;

  @GetMapping
  public ResponseEntity<List<String>> getFileIdList() {
    return ResponseEntity.ok(Lists.newArrayList());
  }

  @GetMapping("/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws IOException {
    InputStreamResource resource = new InputStreamResource(fileService.downloadFile());
    return ResponseEntity.status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"big-size.zip\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .contentLength(2_844_757_475L) // contentLength가 헤더에 명시되어야 브라우저에서 다운받을 총 크기를 안내할 수 있다. stream에서 크기를 구하면 스트림을 태워서 읽어버린다.
        .body(resource);
  }

  @PostMapping
  public void uploadFile(@RequestBody MultipartFile file) {}
}
