package com.buscador.buscadorKusa.util;


import com.buscador.buscadorKusa.model.Producto;

public class GeneradorMensaje {

    public static String generar(Producto producto){

        if(producto == null){
            return "";
        }

        return """
Hola.

Producto: %s

Código: %s

💰 Precio unitario: S/ %s

📦 Precio por caja (%d unidades): S/ %s c/u

🤝 Precio distribuidor: S/ %s

Quedo atento a cualquier consulta.
""".formatted(
                producto.getDescripcion(),
                producto.getCodigo(),
                producto.getPrecioUnitario(),
                producto.getCantidadCaja(),
                producto.getPrecioCaja(),
                producto.getPrecioDistribuidor()
        );

    }

}