package com.anteash.rng.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileDownloader {

    private File file = null;
    @Autowired
    TemplatePreviewer templatePreviewer;

    public String createHtmlCode(String template, String projectVersion) {
        String htmlCode = templatePreviewer.getPreview(template, projectVersion);
        htmlCode = htmlCode.replace("<","&#60");
        htmlCode = htmlCode.replace(">", "&#62");
        return htmlCode;
    }
}
