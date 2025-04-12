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
    private boolean encendido = false;
    private int velocidadActual = 0;
    private boolean patinando = false;
    private boolean accidentado = false;
    private Motor motor;
    private Llantas llantas;

    public Vehiculo(Motor motor, Llantas llantas) {
        this.motor = motor;
        this.llantas = llantas;
    }

    public void encender() throws VehiculoYaEncendidoException, VehiculoAccidentadoException {
        if (accidentado) throw new VehiculoAccidentadoException();
        if (encendido) throw new VehiculoYaEncendidoException();
        encendido = true;
        System.out.println("Vehículo encendido.");
    }

    public void apagar() throws VehiculoYaApagadoException, ApagarVehiculoAltaVelocidadException {
        if (!encendido) throw new VehiculoYaApagadoException();
        if (velocidadActual > 60) {
            accidentado = true;
            encendido = false;
            System.out.println("¡Accidente! El vehículo se apagó a alta velocidad.");
            throw new ApagarVehiculoAltaVelocidadException();
        }
        encendido = false;
        System.out.println("Vehículo apagado.");
    }

    public void acelerar(int kmh) throws VehiculoApagadoException, VehiculoAccidentadoException, VehiculoPatinandoException, ExcesoVelocidadMotorException {
        if (!encendido) throw new VehiculoApagadoException();
        if (accidentado) throw new VehiculoAccidentadoException();
        if (patinando) throw new VehiculoPatinandoException();

        velocidadActual += kmh;
        if (velocidadActual > motor.getVelocidadMaxima()) {
            accidentado = true;
            encendido = false;
            System.out.println("¡Accidente! Se superó la capacidad del motor.");
            throw new ExcesoVelocidadMotorException();
        }
        System.out.println("Acelerando. Velocidad actual: " + velocidadActual + " Km/h.");
    }

    public void frenar(int kmh) throws VehiculoApagadoException, FrenadoInnecesarioException, VehiculoPatinandoException {
        if (!encendido) throw new VehiculoApagadoException();
        if (velocidadActual == 0) throw new FrenadoInnecesarioException();
        if (patinando) throw new VehiculoPatinandoException();

        velocidadActual -= kmh;
        if (velocidadActual < 0) velocidadActual = 0;

        System.out.println("Frenando. Velocidad actual: " + velocidadActual + " Km/h.");
    }

    public void frenarBruscamente(int kmh) throws VehiculoApagadoException, VehiculoPatinandoException {
        if (!encendido) throw new VehiculoApagadoException();
        if (velocidadActual == 0) {
            System.out.println("Frenado brusco innecesario. El vehículo ya está detenido.");
            return;
        }
        if (patinando) throw new VehiculoPatinandoException();

        if (velocidadActual > llantas.getLimiteVelocidad() || kmh > velocidadActual) {
            patinando = true;
            System.out.println("¡El vehículo patinó!");
            return;
        }

        velocidadActual -= kmh;
        if (velocidadActual < 0) velocidadActual = 0;

        System.out.println("Frenado brusco controlado. Velocidad actual: " + velocidadActual + " Km/h.");
    }

    public void recuperarControl() {
        if (patinando && velocidadActual == 0) {
            patinando = false;
            System.out.println("El vehículo ha recuperado el control.");
        }
    }

}
