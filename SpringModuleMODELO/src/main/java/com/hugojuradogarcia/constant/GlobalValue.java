package com.hugojuradogarcia.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalValue {
    public static String SOLICITD_ISSSTE_EPR_JASPER;
    public static String SOLICITD_ISSSTE_EPR_IMAGE;
    public static String IMAGE_FILES_PATH;
    public static String JASPER_FILES_PATH;

    @Value("${jasper.files.jasper.solicitudIssste}")
    public void setSolicitdIsssteEprJasper(String solicitdIssste) {
        SOLICITD_ISSSTE_EPR_JASPER = solicitdIssste;
    }

    @Value("${jasper.files.image.solicitudIssste}")
    public void setSolicitdIsssteEprImage(String solicitudIssste) {
        SOLICITD_ISSSTE_EPR_IMAGE = solicitudIssste;
    }

    @Value("${jasper.imagesPath}")
    public void setImageFilesPath(String imagesPath) {
        IMAGE_FILES_PATH = imagesPath;
    }

    @Value("${jasper.reportsPath}")
    public void setJasperFilesPath(String reportsPath) {
        JASPER_FILES_PATH = reportsPath;
    }
}
