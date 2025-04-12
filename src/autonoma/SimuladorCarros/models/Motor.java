/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.SimuladorCarros.models;

/*
 * 
 * @author Juan Esteban Hernández Martínez
 * @since 20250411
 * @version 1.0.0 
 */
public class Motor {
    private String tipo;
    private int velocidadMaxima;

    public Motor(String tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case "1000":
                this.velocidadMaxima = 100;
                break;
            case "2000":
                this.velocidadMaxima = 160;
                break;
            case "3000":
                this.velocidadMaxima = 220;
                break;
            default:
                throw new IllegalArgumentException("Tipo de motor inválido");
        }
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public String getTipo() {
        return tipo;
    }

}
