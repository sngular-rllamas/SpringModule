package com.hugojuradogarcia;

import com.hugojuradogarcia.commons.JasperReportTools;
import com.hugojuradogarcia.constant.GlobalValue;
import com.hugojuradogarcia.entity.Employee;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@PropertySource("classpath:application.properties")
class JasperReportServiceImp implements JasperReportService {

    @Autowired
    private JasperReportTools jasperReportTools;

    public HashMap<String, Object> mapa = new HashMap<>();

    @Override
    public byte[] getFormatoSolicitudIsssteEPR(Employee employee) {
        JasperReport jasperReportIssste = jasperReportTools.getFormatoJasperSolicitudIsssteEPR(GlobalValue.SOLICITD_ISSSTE_EPR_JASPER);
        // Map path image background
        mapa.put(GlobalValue.SOLICITD_ISSSTE_EPR_JASPER, GlobalValue.IMAGE_FILES_PATH + GlobalValue.SOLICITD_ISSSTE_EPR_IMAGE);
        //mapa.put("testBackground", GlobalValue.IMAGE_FILES_PATH + GlobalValue.SOLICITD_ISSSTE_EPR_IMAGE);
        byte[] data = jasperReportTools.createPdfOutputStream(jasperReportIssste, mapa, employee);

        return data;
    }

}
