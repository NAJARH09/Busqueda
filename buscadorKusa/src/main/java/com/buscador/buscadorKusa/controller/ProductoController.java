package com.buscador.buscadorKusa.controller;

import com.buscador.buscadorKusa.model.Producto;
import com.buscador.buscadorKusa.service.CatalogoService;
import com.buscador.buscadorKusa.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final CatalogoService catalogoService;

    public ProductoController(
            ProductoService productoService,
            CatalogoService catalogoService) {

        this.productoService = productoService;
        this.catalogoService = catalogoService;
    }

    @GetMapping("/")
    public String inicio() {
        return "buscar";
    }

    @GetMapping("/buscar")
    public String buscarProducto(
            @RequestParam(required = false) String codigo,
            Model model) {

        if (codigo != null && !codigo.isBlank()) {

            Producto producto = productoService.buscarPorCodigo(codigo);

            model.addAttribute("producto", producto);

            if (producto != null) {
                String imagen =
                        catalogoService.buscarImagenPorCodigo(producto.getCodigo());

                model.addAttribute("imagen", imagen);
            }
        }

        return "buscar";
    }
}