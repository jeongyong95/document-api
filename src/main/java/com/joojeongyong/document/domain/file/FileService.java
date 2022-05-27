package com.joojeongyong.document.domain.file;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    public InputStream downloadFile() {
        try {
            File file = new File("standardlogos_2022.zip");
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
