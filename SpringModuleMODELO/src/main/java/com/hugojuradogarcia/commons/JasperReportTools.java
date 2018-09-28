package com.hugojuradogarcia.commons;

import com.hugojuradogarcia.constant.GlobalValue;
import com.hugojuradogarcia.exception.GenericNotFoundException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class JasperReportTools {

    @Autowired
    private ResourceLoader resourceLoader;

    private static JasperReport jasperReportSolicitudIsssteEPR;

    // Get format JasperReport
    public JasperReport getFormatoJasperSolicitudIsssteEPR(String jasperFileName) {
        if (null == jasperReportSolicitudIsssteEPR) {
            try {
                jasperReportSolicitudIsssteEPR = loadJasper(jasperFileName);
            } catch (Exception e) {
                throw new GenericNotFoundException();
            }
        }
        return jasperReportSolicitudIsssteEPR;
    }

    // Load and compile report
    private JasperReport loadJasper(String jasperName)
            throws IOException, JRException {

        InputStream inputStream = null;
        JasperReport jasperReport = null;
        String archivo = GlobalValue.JASPER_FILES_PATH + jasperName;

        try {
            Resource resource = resourceLoader.getResource(archivo + ".jasper");
            inputStream = resource.getInputStream();

            jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
        } catch (FileNotFoundException ex) {
            Resource resource = resourceLoader.getResource(archivo + ".jrxml");
            inputStream = resource.getInputStream();

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            // create jasper file from jrxml
            JasperCompileManager.compileReportToFile(jasperDesign, jasperName + ".jasper");
        } catch (JRException jrex) {
            throw new GenericNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jasperReport;
    }

    public byte[] createPdfOutputStream(JasperReport report,
                                        HashMap<String, Object> mapa, Object data) {
        try{
            if (null == data) {
                return JasperRunManager.runReportToPdf(report, mapa,
                        new JREmptyDataSource());
            }

            // Generate jasper print
            JasperPrint jprint = (JasperPrint) JasperFillManager
                    .fillReport(report, mapa, new JRBeanCollectionDataSource(
                            Collections.singletonList(data)));

            // Remove blank page
            removeBlankPage(jprint.getPages());

            // Export pdf file to bytes byte[]
            return JasperExportManager.exportReportToPdf(jprint);
        }catch (JRException e){
            throw new GenericNotFoundException();
        }
    }

    public void removeBlankPage(List<JRPrintPage> pages) {
        for (Iterator<JRPrintPage> i = pages.iterator(); i.hasNext();) {
            JRPrintPage page = i.next();
            if (page.getElements().size() == 0)
                i.remove();
        }
    }
}
