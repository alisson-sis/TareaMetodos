package co.edu.uniquindio.poo.Model;

import java.util.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

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

    public String registrarCliente2(Cliente cliente){
        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.documentoIdentidad());
        if(clienteEncontrado.isEmpty()){
            listClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        }else return "No se puede registrar, ya existe un cliente con esa informacion registrado anteriormente.";
    }


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

    public double calcularTotal(Collection<Producto> listProductos) {
        double suma = 0;
        for (Producto producto : listProductos) {
            suma += producto.getValor();
        }
        return suma;

    }

    //punto 1: obtener la lista de productos con una cantidad disponible mayor igual a 10
    public List<Producto> cantidadMayor() {
        List<Producto> guardarProductos = new ArrayList<>();
        for (Producto producto : listProductos.values()) {
            if (producto.getCantidadDisponible() >= 10) {
                guardarProductos.add(producto);
            }
        }
        return guardarProductos;
    }

    //punto 2: Obtener la lista de codigos de los productos de cantidad mayor  igual
    // a 10 y menor que 50

    public List<String> cantidadMayorEntreMenor() {
        List<String> listCodigo = new ArrayList<>();
        for (Producto producto : listProductos.values()) {
            if (producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50) {
                listCodigo.add(producto.getCodigo());
            }
        }
        return listCodigo;
    }

    //punto 3: obtener la lista de clientes que hayan comprado el 07/10/26

    public ArrayList<Cliente> obtenerClientesCompra(Cliente cliente) {
        ArrayList<Cliente> listClientes = new ArrayList<>();
        LocalDate fechaConsulta = LocalDate.of(2026, 10, 7);
        for (Factura factura : listFacturas) {
            if (factura.getFechaGeneracion().isEqual(fechaConsulta)) {
                listClientes.add(cliente);
            }
        }
        return listClientes;
    }

    //punto 4: obtener las facturas que tengan un cliente donde su nombre empiece por R

    public boolean nombreR() {
        ArrayList<Factura> resultado = new ArrayList<>();
        for (Factura factura : listFacturas) {
            if (factura.tieneLetraR()) {
                return resultado.add(factura);
            }
        }
        return nombreR();
    }
    //punto 5: obtener las facturas donde se haya comprado un celular de marca iphone 16 pro MAX

    public ArrayList<Factura> obtenerComprasIphone(Producto producto) {
        ArrayList<Factura> listFacturasIphone = new ArrayList<>();
        for (Factura facturaaux : listFacturas) {
            for (Producto productos : facturaaux.getListProducto()) { //listade los productos comprados
                if (productos.getCategoria().equals(Categoria.CELULARES)) { //variable
                    if (producto.esIphone()) {
                        listFacturasIphone.add(facturaaux);
                        break;
                    }
                }
            }
        }
        return listFacturasIphone;
    }
//punto 6:   Obtener las facturas que tenga un cliente donde su nombre sea juan y
// haya comprado un celular de marca Iphone 16 pro max

    public ArrayList<Factura> obtenerClienteNombreIphoen(Cliente cliente) {
        ArrayList<Factura> listFacturaJuan = new ArrayList<>();
        for (Factura facturaCliente : listFacturas) {

            if (facturaCliente.equals(obtenerClientesCompra(cliente))) {
                if (facturaCliente.facturaNombreJuan()) {
                    listFacturaJuan.add(facturaCliente);
                }
            }

        }
        return listFacturaJuan;
    }
    // Punto 7: Implementar un metodo que reciba una categoría
    //  y retorne todos los productos registrados que pertenezcan a ella.

    public Optional<Producto> obtenerProducto(Producto producto) {
            return listProductos.values().stream().filter
                    (p -> p.getCategoria()==producto.getCategoria()).findFirst();
        }
        //punto 8:  Implementar un metodo que reciba un precio mínimo y un precio máximo, y retorne
    // los productos cuyo precio se encuentre dentro de ese rango, incluyendo ambos límites.
    // Preguntar en clase al profe

    public List<Producto>rangoDePrecios(Producto producto){
        List<Producto>filtrarProductos=new ArrayList<>();
        for(Producto productoAux: listProductos.values()) {
            if(producto.getValor()>=precioMinimo && producto.getValor()<=precioMaximo){
                filtrarProductos.add(productoAux);
            }
        }
        return filtrarProductos;
    }
//punto 9: Implementar un metodo que retorne todos los productos
 // registrados en la tienda, ordenados de menor a mayor según su precio.
    //subio el nivel preguntar en clasee

    public List<Producto>ordenarMayorMenor(){
        List<Producto>listOrden=new ArrayList<>(listProductos.values());
          return listProductos.values().stream().sorted(Comparator.comparingDouble
                                                        (Producto::getValor)).collect(Collectors.toList());
    }
    //punto 10: Implementar un metodo que identifique el producto con el precio más alto de la tienda.
    // Si no existen productos registrados, el metodo debe retornar un Optional vacío.

    public Optional<Producto>precioMasAlto(){
        List<Producto>listAlto=ordenarMayorMenor();
        if(listAlto.isEmpty()){
            return Optional.empty();
    }
        return Optional.of(listAlto.get(listAlto.size()-1));
    }
    //punto 11: Implementar un metodo que reciba el nombre de una ciudad y retorne
    // todos los clientes que residan en ella.
    // La búsqueda debe realizarse sin diferenciar entre mayúsculas y minúsculas.

    public List<Cliente>residenciaCliente(Cliente cliente){
        List<Cliente>filtrarResidencia=new ArrayList<>();
        for(Cliente clienAux: listClientes){
            if(cliente.documentoIdentidad==ciudadBuscada){
                filtrarResidencia.add(clienAux);
            }
        }
        return filtrarResidencia;
    }
    }

    //nota: se requiere del main para recibir datos, es decir, preguntar al administrador. preguntar al profe







