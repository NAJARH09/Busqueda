package com.buscador.buscadorKusa.service;

import com.buscador.buscadorKusa.model.Producto;
import com.buscador.buscadorKusa.repository.ProductoRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ExcelServiceImpl implements ExcelService {

    private final ProductoRepository repository;

    public ExcelServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void importarExcel(MultipartFile archivo) throws Exception {

        Workbook workbook = WorkbookFactory.create(archivo.getInputStream());

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> filas = sheet.iterator();

        // Saltar encabezado
        if (filas.hasNext()) {
            filas.next();
        }

        while (filas.hasNext()) {

            Row fila = filas.next();

            if (fila == null) continue;

            String codigo = obtenerTexto(fila.getCell(0));

            if (codigo.isBlank()) {
                continue;
            }

            Optional<Producto> existente = repository.findByCodigo(codigo);

            Producto producto = existente.orElse(new Producto());

            producto.setCodigo(codigo);
            producto.setDescripcion(obtenerTexto(fila.getCell(1)));
            producto.setUnidad(obtenerTexto(fila.getCell(2)));

            producto.setPrecioUnitario(obtenerDecimal(fila.getCell(3)));
            producto.setPrecioCaja(obtenerDecimal(fila.getCell(4)));

            producto.setCantidadCaja(
                    obtenerDecimal(fila.getCell(5)).intValue()
            );

            producto.setPrecioDistribuidor(
                    obtenerDecimal(fila.getCell(6))
            );

            repository.save(producto);

        }

        workbook.close();

    }

    private String obtenerTexto(Cell cell){

        if(cell==null)
            return "";

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell).trim();

    }

    private BigDecimal obtenerDecimal(Cell cell){

        if(cell==null)
            return BigDecimal.ZERO;

        try{

            if(cell.getCellType()==CellType.NUMERIC){

                return BigDecimal.valueOf(cell.getNumericCellValue());

            }

            String texto=cell.toString();

            if(texto.isBlank())
                return BigDecimal.ZERO;

            return new BigDecimal(texto);

        }catch(Exception e){

            return BigDecimal.ZERO;

        }

    }

}
