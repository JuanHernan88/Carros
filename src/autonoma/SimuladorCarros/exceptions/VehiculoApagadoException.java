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
public class VehiculoApagadoException extends Exception {
    public VehiculoApagadoException() {
        super("No se puede realizar esta acción con el vehículo apagado.");
    }
}

