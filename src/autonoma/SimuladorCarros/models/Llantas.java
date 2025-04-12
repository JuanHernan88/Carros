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
public class Llantas {
    private String tipo;
    private int limiteVelocidad;

    public Llantas(String tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case "Buenas":
                this.limiteVelocidad = 110;
                break;
            case "Bonitas":
                this.limiteVelocidad = 70;
                break;
            case "Baratas":
                this.limiteVelocidad = 50;
                break;
            default:
                throw new IllegalArgumentException("Tipo de llantas inválido");
        }
    }

    public int getLimiteVelocidad() {
        return limiteVelocidad;
    }

    public String getTipo() {
        return tipo;
    }
}
