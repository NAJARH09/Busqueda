package com.buscador.buscadorKusa.service;

import com.buscador.buscadorKusa.model.Producto;

import java.util.List;


public interface ProductoService {
    Producto buscarPorCodigo(String codigo);
    List<Producto> buscarSugerencias(String texto);
}
