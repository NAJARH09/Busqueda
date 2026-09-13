
        package com.buscador.buscadorKusa.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogoService {

    @Override
    public String buscarImagenPorCodigo(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return null;
        }

        String codigoLimpio = codigo.trim();

        // Primero buscamos la imagen usando el código tal como viene.
        if (existeImagen(codigoLimpio)) {
            return "/catalogo/" + codigoLimpio + ".png";
        }

        // Si no existe, probamos agregando un guion después de KC.
        if (codigoLimpio.startsWith("KC") && !codigoLimpio.startsWith("KC-")) {

            String codigoConGuion =
                    "KC-" + codigoLimpio.substring(2);

            if (existeImagen(codigoConGuion)) {
                return "/catalogo/" + codigoConGuion + ".png";
            }
        }

        return null;
    }

    @Override
    public boolean existeImagen(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            return false;
        }

        try {
            ClassPathResource recurso =
                    new ClassPathResource(
                            "static/catalogo/" + codigo.trim() + ".png"
                    );

            return recurso.exists();

        } catch (Exception e) {
            return false;
        }
    }
}

