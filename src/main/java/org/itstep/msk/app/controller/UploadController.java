package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Upload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    @Value("${app.uploads.path}")
    private String uploadsPath;

    @GetMapping("/upload/{uploadId}")
    @ResponseBody
    public ResponseEntity<Resource> upload(@PathVariable("uploadId") Upload upload) throws MalformedURLException {
        Path path = Paths.get(uploadsPath).toAbsolutePath().resolve(upload.getFilename());
        Resource file = new UrlResource(path.toUri());

        if (!file.exists() || !file.isReadable()) {
            throw new RuntimeException();
        }

        return ResponseEntity.ok().body(file);
    }
}
