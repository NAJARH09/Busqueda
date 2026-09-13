package com.buscador.buscadorKusa.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogoService {

    @Override
    public String buscarImagenPorCodigo(String codigo) {
        if (!existeImagen(codigo)) {
            return null;
        }

        return "/catalogo/" + codigo.trim() + ".png";
    }

    @Override
    public boolean existeImagen(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return false;
        }

        try {
            ClassPathResource recurso =
                    new ClassPathResource("static/catalogo/" + codigo.trim() + ".png");

            return recurso.exists();
        } catch (Exception e) {
            return false;
        }
    }
}