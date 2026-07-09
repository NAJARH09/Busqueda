package com.buscador.buscadorKusa.service;

import com.buscador.buscadorKusa.model.Producto;
import com.buscador.buscadorKusa.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoService{
    private final ProductoRepository productoRepository;
    public ProductoServiceImpl(ProductoRepository repository) {
        this.productoRepository = repository;

    }
    @Override
    public Producto buscarPorCodigo(String codigo) {

        return productoRepository.findByCodigo(codigo)
                .orElse(null);

    }
    @Override
    public List<Producto> buscarSugerencias(String texto) {

        return productoRepository
                .findTop10ByCodigoContainingIgnoreCaseOrDescripcionContainingIgnoreCase(texto, texto);

    }
}
