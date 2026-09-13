package com.buscador.buscadorKusa.service;


import org.springframework.stereotype.Service;

//primer paso

public interface CatalogoService {
    String buscarImagenPorCodigo(String codigo);
    boolean existeImagen(String codigo);

}
