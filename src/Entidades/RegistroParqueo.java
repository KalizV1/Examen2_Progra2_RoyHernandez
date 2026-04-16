/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;

public class RegistroParqueo {

    private Vehiculo vehiculo;
    private LocalDateTime ingreso;
    private LocalDateTime salida;
    private double monto;

    public RegistroParqueo(Vehiculo vehiculo, LocalDateTime ingreso) {
        this.vehiculo = vehiculo;
        this.ingreso = ingreso;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getIngreso() {
        return ingreso;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
