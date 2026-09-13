
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

        System.out.println("=================================");
        System.out.println("CODIGO RECIBIDO: [" + codigo + "]");

        String codigoLimpio = codigo.trim();

        System.out.println("CODIGO LIMPIO: [" + codigoLimpio + "]");

        System.out.println("IMAGEN EXACTA EXISTE: "
                + existeImagen(codigoLimpio));

        if (codigoLimpio.startsWith("KC")
                && !codigoLimpio.startsWith("KC-")) {

            String codigoConGuion =
                    "KC-" + codigoLimpio.substring(2);

            System.out.println("CODIGO CON GUION: [" + codigoConGuion + "]");

            System.out.println("IMAGEN CON GUION EXISTE: "
                    + existeImagen(codigoConGuion));
        }

        System.out.println("=================================");

        // 1. Buscar exactamente como viene
        if (existeImagen(codigoLimpio)) {
            return "/catalogo/" + codigoLimpio + ".png";
        }

        // 2. Probar agregando guion
        if (codigoLimpio.startsWith("KC")
                && !codigoLimpio.startsWith("KC-")) {

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

