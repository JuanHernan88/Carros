/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.SimuladorCarros.models;

import autonoma.SimuladorCarros.exceptions.*;


/*
 * 
 * @author Juan Esteban Hernández Martínez
 * @since 20250411
 * @version 1.0.0 
 */
public class Vehiculo {
    private boolean encendido;
    private int velocidadActual;
    private boolean patinando;
    private boolean accidentado;
    private Motor motor;
    private Llantas llantas;

    // Constructor
    public Vehiculo(Motor motor, Llantas llantas) {
        this.motor = motor;
        this.llantas = llantas;
        this.encendido = false;
        this.velocidadActual = 0;
    }

    // Métodos
    public void encender() throws VehiculoYaEncendidoException, VehiculoAccidentadoException {
        if (accidentado) throw new VehiculoAccidentadoException();
        if (encendido) throw new VehiculoYaEncendidoException();
        encendido = true;
    }

    public void acelerar(int kmh) throws VehiculoApagadoException, ExcesoVelocidadMotorException {
        if (!encendido) throw new VehiculoApagadoException();
        velocidadActual += kmh;
        if (velocidadActual > motor.getVelocidadMaxima()) {
            accidentado = true;
            throw new ExcesoVelocidadMotorException();
        }
    }

    public void apagar() throws VehiculoYaApagadoException, ApagarVehiculoAltaVelocidadException {
     if (!encendido) {
        throw new VehiculoYaApagadoException();
     }
     if (velocidadActual > 60) {
        accidentado = true;
        encendido = false;
        throw new ApagarVehiculoAltaVelocidadException();
     }
     encendido = false;
    }
   public void frenar(int kmh) throws VehiculoApagadoException, 
                                  FrenadoInnecesarioException, 
                                  VehiculoPatinandoException {
    if (!encendido) {
        throw new VehiculoApagadoException();
    }
    if (velocidadActual == 0) {
        throw new FrenadoInnecesarioException();
    }
    if (patinando) {
        throw new VehiculoPatinandoException();
    }
    
    velocidadActual = Math.max(0, velocidadActual - kmh);
}
public void frenarBruscamente(int kmh) throws VehiculoApagadoException {
    if (!encendido) {
        throw new VehiculoApagadoException();
    }
    
    // Lógica de derrape (patinaje)
    if (velocidadActual > 50 && kmh > 30) { // Umbrales para derrapar
        patinando = true;
        velocidadActual *= 0.7; // Reduce velocidad pero no se detiene
        return; // Salir sin aplicar frenado normal
    }
    
    // Frenado normal si no hay derrape
    velocidadActual = Math.max(0, velocidadActual - kmh);
}

    public void recuperarControl() {
        if (patinando && velocidadActual == 0) {
            patinando = false;
            System.out.println("El vehículo ha recuperado el control.");
        }
    }
    public int getVelocidadActual() {
    return velocidadActual;
    }
    public boolean isEncendido() {
    return this.encendido;
    }

    public boolean isPatinando() {
    return this.patinando;
    }

    public boolean isAccidentado() {
    return this.accidentado;
    }
    public Motor getMotor() {
    return this.motor;
    }
    public Llantas getLlantas() {
    return this.llantas;
    }

}
