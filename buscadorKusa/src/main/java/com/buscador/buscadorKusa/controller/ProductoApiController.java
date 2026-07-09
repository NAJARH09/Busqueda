package com.buscador.buscadorKusa.controller;

import com.buscador.buscadorKusa.model.Producto;
import com.buscador.buscadorKusa.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoApiController {

    private final ProductoService service;

    public ProductoApiController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/api/productos")
    public List<Producto> buscar(

            @RequestParam String texto

    ){

        return service.buscarSugerencias(texto);

    }

}