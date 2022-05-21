package com.joojeongyong.document.api.controller;

import com.google.common.collect.Lists;
import com.joojeongyong.document.domain.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    return ResponseEntity.ok(null);
  }

  @PostMapping
  public void uploadFile(@RequestBody MultipartFile file) {}
}
