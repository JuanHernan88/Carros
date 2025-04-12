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

public void apagar() throws VehiculoYaApagadoException, VehiculoAccidentadoException, ApagarVehiculoAltaVelocidadException {
    if (this.accidentado) {
        throw new VehiculoAccidentadoException();
    }
    if (!this.encendido) {
        throw new VehiculoYaApagadoException();
    }
    if (this.velocidadActual > 60) {
        throw new ApagarVehiculoAltaVelocidadException();
    }
    this.encendido = false;
    this.velocidadActual = 0;
}

    public void frenar(int kmh) throws VehiculoApagadoException, FrenadoInnecesarioException, VehiculoPatinandoException {
    if (!encendido) {
        throw new VehiculoApagadoException();
    }
    
    if (kmh <= 0) {
        throw new FrenadoInnecesarioException();
    }
    
    if (patinando) {
        throw new VehiculoPatinandoException();
    }
    
    // Aquí está la parte importante: reducir la velocidad
    velocidadActual = Math.max(0, velocidadActual - kmh); // No permite velocidad negativa
    
    // Verificar si las llantas pueden manejar la velocidad después de frenar
    if (velocidadActual > llantas.getLimiteVelocidad()) {
        patinando = true;
        throw new VehiculoPatinandoException();
    }
}
public void frenarBruscamente(int kmh) throws VehiculoApagadoException {
    if (!encendido) {
        throw new VehiculoApagadoException();
    }
    
    // Aplica el frenado primero
    velocidadActual = Math.max(0, velocidadActual - kmh);
    
    // Luego verifica si hay derrape
    if (velocidadActual > 50 && kmh > 30) {
        patinando = true;
        velocidadActual *= 0.7; // Reduce velocidad adicional por derrape
    }
}

public void recuperarControl() {
    if (this.patinando && this.velocidadActual <= 60) {
        this.patinando = false;
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
