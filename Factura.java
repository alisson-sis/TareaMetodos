package co.edu.uniquindio.poo.Model;

import java.time.LocalDate;
import java.util.*;

    public class Factura {
        private final String codigo;
        private final LocalDate fechaGeneracion;
        private final Cliente cliente;
        private final double total;
        private final MetodoPago metodoPago;
        private final Tienda ownedByTienda;

        private final List<Producto> listProductos=new LinkedList<>();
        private final ArrayList<DetalleFactura>listDettalleFactura=new ArrayList<>();
        private final ArrayList<Factura>listFactura;




        public Factura(String codigo, LocalDate fechaGeneracion, double total, MetodoPago metodoPago,
                       Cliente cliente, Tienda ownedByTienda, ArrayList<Factura> listFactura) {
            this.codigo = codigo;
            this.fechaGeneracion = fechaGeneracion;
            this.total = total;
            this.metodoPago = metodoPago;
            this.cliente=cliente;
            this.ownedByTienda=ownedByTienda;

            this.listFactura = listFactura;
        }

        public String getCodigo() {
            return codigo;
        }

        public LocalDate getFechaGeneracion() {
            return fechaGeneracion;
        }

        public double getTotal() {
            return total;
        }

        public MetodoPago getMetodoPago() {
            return metodoPago;
        }

        public List<Producto> getListProducto() {
            return listProductos;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public Tienda getOwnedByTienda() {
            return ownedByTienda;
        }

        public List<Producto> getListProductos() {
            return listProductos;
        }

        public ArrayList<DetalleFactura> getListDettalleFactura() {
            return listDettalleFactura;
        }

        public ArrayList<Factura> getListFactura() {
            return listFactura;
        }
        //CRUD

        //Registrar
        public String registrarFactura(DetalleFactura detalleFactura) {
            Optional<Factura> facturaEncontrada = encontrarFactura(cliente.getTelefono());
            if (facturaEncontrada.isEmpty()) {
                listDettalleFactura.add(detalleFactura);
                return "la factura fue registrada exitosaMente";

            } else {
                return "no fue posible";
            }
        }
        //buscar
        public Optional<Factura>encontrarFactura(String telefono){
            for(Factura factura: listFactura){
                if(telefono.equals(cliente.getTelefono())){
                    return Optional.of(factura);
                }
            }
            return Optional.empty();
        }
        //Actualizar
        public record DetalleFactura(int cantidadComprada, float subTotal, Tienda ownedByFactura,
                                     Producto producto){
            public Optional<DetalleFactura>actualizarFactura(Optional<Factura> facturaAntigua, int cantidadCompradaNueva,
                                                      float subTotalNueva, Tienda ownedByFacturaNueva,
                                                      Producto productoNueva){
                if(facturaAntigua.isPresent()){
                    Factura datosFiltrados=facturaAntigua.get();
                    DetalleFactura nuevosDatos=new DetalleFactura(cantidadCompradaNueva, subTotalNueva, ownedByFacturaNueva,
                            productoNueva);

                    return Optional.of(nuevosDatos);

                    }
                return Optional.empty();

                }
            }
            //eliminar
            public String eliminarFactura(DetalleFactura detalleFactura) {
                Optional<Factura> facturaEncontrada = encontrarFactura(cliente.getTelefono());
                if (facturaEncontrada.isEmpty()) {
                    listDettalleFactura.remove(detalleFactura);
                    return "la factura fue eliminada exitosaMente";

                } else {
                    return "no fue posible";
                }
            }
            //punto 4
        public boolean tieneLetraR(){
            boolean resultado=false;
            resultado=cliente.verificarNombre();
            return resultado;
        }
        //punto 6
        public boolean facturaNombreJuan(){
            boolean nombreRR=false;
            nombreRR= cliente.nombreJuan();
            return nombreRR;
        }


        }





