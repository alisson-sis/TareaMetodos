package co.edu.uniquindio.poo.Model;

import java.util.Locale;

public class Producto {

        private final String nombre;
        private final String codigo;
        private final String descripcion;
        private int cantidadDisponible;
        private final double valor;
        private final Categoria categoria;
        private final Tienda ownedByTienda;


        public Producto(String nombre, String codigo, String descripcion,
                        int cantidadDisponible, double valor,
                        Categoria categoria, Tienda ownedByTienda) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.descripcion = descripcion;
            this.cantidadDisponible = cantidadDisponible;
            this.valor = valor;
            this.categoria = categoria;
            this.ownedByTienda = ownedByTienda;
        }

        public String getNombre() { return nombre;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public int getCantidadDisponible() {
            return cantidadDisponible;
        }

        public double getValor() {
            return valor;
        }

        public Categoria getCategoria() {
            return categoria;
        }

        public Tienda getOwnedByTienda() {
            return ownedByTienda;
        }

//punto 5
    public boolean esIphone(){
            boolean verificarCelular=false;
            boolean verificarModelo=false;
            if(categoria.equals(Categoria.CELULARES)){
                return verificarCelular=true;
            }
                if(nombre.toLowerCase().contains("iphone 16 pro max")){ //contains: sirve para encontrar la cadena
                    return verificarModelo=true;
            }
        return verificarCelular && verificarModelo;
    }
//punto 8
    public int rangos(){
            int precioMinimo;
            int precioMaximo;
    }
    }

