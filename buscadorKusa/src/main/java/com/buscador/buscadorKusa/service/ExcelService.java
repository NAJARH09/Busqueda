package com.buscador.buscadorKusa.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    void importarExcel(MultipartFile archivo) throws Exception;

}