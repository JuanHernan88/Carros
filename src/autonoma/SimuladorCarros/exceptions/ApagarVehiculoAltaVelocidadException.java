/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.SimuladorCarros.exceptions;

/*
 * 
 * @author Juan Esteban Hernández Martínez
 * @since 20250411
 * @version 1.0.0 
 */
public class ApagarVehiculoAltaVelocidadException extends Exception {
    public ApagarVehiculoAltaVelocidadException() {
        super("¡Accidente! No se puede apagar un vehículo a más de 60 Km/h.");
    }
}

