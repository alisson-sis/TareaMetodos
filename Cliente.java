package co.edu.uniquindio.poo.Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Cliente {
private final String documentoIdentidad;
private final String nombreCompleto;
private final Tienda ownedByTienda;
private final String telefono;
private final String correo;
private final String ciudadResidencia;

private final ArrayList<Factura>listFactura;

public Cliente(String documentoIdentidad, String nombreCompleto, Tienda ownedByTienda, String telefono, String correo,
               String ciudadResidencia){

    this.documentoIdentidad=documentoIdentidad;
    this.nombreCompleto=nombreCompleto;
    this.ownedByTienda=ownedByTienda;
    this.telefono=telefono;
    this.correo=correo;
    this.ciudadResidencia=ciudadResidencia;

    this.listFactura=new ArrayList<>();

}

    public String getDocumentoIdentidad() {

    return documentoIdentidad;
    }

    public String getNombreCompleto() {

    return nombreCompleto;
    }

    public Tienda getOwnedByTienda() {

    return ownedByTienda;
    }

    public String getTelefono() {

    return telefono;
    }

    public String getCorreo() {

    return correo;
    }

    public String getCiudadResidencia() {

    return ciudadResidencia;
    }
    public boolean comprarEnFecha(LocalDate fechaConsulta){
    boolean comprarFecha=false;
    for(Factura factura: listFactura){
        if(factura.getFechaGeneracion().isEqual(fechaConsulta)){
            return true;
        }
    }
    return comprarFecha;
    }
    //punto 4
    public boolean verificarNombre(){
    return nombreCompleto.startsWith("R");
    }
    //punto 6
    public boolean nombreJuan(){
    return nombreCompleto.startsWith("Juan");
    }
}
