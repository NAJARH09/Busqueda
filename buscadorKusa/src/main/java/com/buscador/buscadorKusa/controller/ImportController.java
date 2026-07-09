package com.buscador.buscadorKusa.controller;

import com.buscador.buscadorKusa.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

@Controller
public class ImportController {

    private final ExcelService excelService;

    public ImportController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/importar")
    @ResponseBody
    public String importar(@RequestParam("archivo") MultipartFile archivo) {

        try {

            excelService.importarExcel(archivo);

            return "OK";

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR";

        }

    }

}