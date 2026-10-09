package co.edu.uniquindio.poo.Model;

import java.util.*;

public class Tienda {
    //array: es estatico, no se modifica su tamaño, tamaño fijo(datos primitivos)(.length)
    //arrayList: es dinamico, se modifica su tamaño mediante agregues o elimines(almacena objetos)
    //map: permite encontrar cosas mas faciles con una llave
    //linkelist: mejor rendimiento

    private final String nombre;
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listClientes = new ArrayList<>();
    private final List<Factura> listFacturas = new LinkedList<>();
    private Map<String, Producto> listProductos = new HashMap<>();

    public Tienda(String nombre, String nit, String telefono) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }

    public String getNombre() {

        return nombre;
    }

    public String getNit() {

        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    //Crud=Resgitrar, buscar, actualizar, eliminar
//optional

    //Registrar
    public String registrarCliente(Cliente cliente) {
        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.documentoIdentidad);
        if (clienteEncontrado.isEmpty()) {
            listClientes.add(cliente);
            return "el cliente fue registrado exitosamente";

        } else {
            return "No se pudo registrar, ya existe un cliente con los mismos datos";

        }
    }

    //Buscar-imperativa
    public Optional<Cliente> buscarCliente(String documentoIdentidad) {
        for (Cliente cliente : listClientes) {
            if (documentoIdentidad.equals(cliente.documentoIdentidad)) {
                return Optional.of(cliente); //guardar
            }
        }
        return Optional.empty();
    }

    //actualizar
    public record Cliente(String documentoIdentidad, String nombreCompleto,
                          String ownedByTienda, String telefono, String correo) {

        public static Optional<Cliente> actualizarCliente(Optional<Cliente> clienteAntiguo,
                                                          String documentoIdentidadNueva, String nombreCompletoNueva,
                                                          String ownedByTienda, String telefonoNuevo, String correoNuevo) {
            if (clienteAntiguo.isPresent()) {
                Cliente datosAntiguos = clienteAntiguo.get();
                Cliente clienteActualizado = new Cliente(documentoIdentidadNueva, nombreCompletoNueva, ownedByTienda,
                        telefonoNuevo, correoNuevo);

                return Optional.of(clienteActualizado);
            }
            return Optional.empty();
        }

    }

    //eliminar
    public String eliminarCliente(Cliente cliente) {
        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.documentoIdentidad());
        if (clienteEncontrado.isEmpty()) {
            listClientes.remove(cliente);
            return "El cliente fue eliminado exitosamente";
        } else return "No se puede eliminar, no hay dato que eliminar";
    }

    public Optional<Factura> obtenerFactura(String codigo) {
        return listFacturas.stream().filter(f -> f.getCodigo().equals(codigo)).findFirst();
    }

    //desarrollo del punto 1
    public List<Producto> cantidadMayor() {
        List<Producto> guardarProductos = new ArrayList<>();
        for (Producto producto : listProductos.values()) {
            if (producto.getCantidadDisponible() >= 10) {
                guardarProductos.add(producto);
            }
        }
        return guardarProductos;
    }

    //Desarrollo punto 2
    public double calcularTotal(Collection<Producto> listProductos) {
        double suma = 0;
        for (Producto producto : listProductos) {
            suma+= producto.getValor();
        }
        return suma;

    }

}





