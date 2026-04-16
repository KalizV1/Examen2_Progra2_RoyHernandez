/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import accesodatos.RegistroDAO;
import entidades.RegistroParqueo;
import entidades.Vehiculo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ParqueoService {

    private final double TARIFA_POR_HORA = 500;
    private final RegistroDAO dao = new RegistroDAO();

    // ===============================
    // REGISTRAR INGRESO
    // ===============================
    public void registrarIngreso(String placa, String tipo) throws Exception {

        if (placa == null || placa.trim().isEmpty()) {
            throw new Exception("La placa es obligatoria");
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new Exception("El tipo de vehículo es obligatorio");
        }

        List<RegistroParqueo> activos = dao.leerActivos();

        for (RegistroParqueo r : activos) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                throw new Exception("El vehículo ya se encuentra en el parqueo");
            }
        }

        Vehiculo v = new Vehiculo(placa, tipo);
        RegistroParqueo registro =
                new RegistroParqueo(v, LocalDateTime.now());

        dao.guardarIngreso(registro);
    }

    // ===============================
    // OBTENER VEHÍCULOS ACTIVOS
    // ===============================
    public List<RegistroParqueo> obtenerActivos() throws Exception {
        return dao.leerActivos();
    }

    // ===============================
    // REGISTRAR SALIDA Y CALCULAR COBRO
    // ===============================
    public double registrarSalida(String placa) throws Exception {

        if (placa == null || placa.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un vehículo");
        }

        List<RegistroParqueo> activos = dao.leerActivos();
        RegistroParqueo salida = null;

        for (RegistroParqueo r : activos) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                salida = r;
                break;
            }
        }

        if (salida == null) {
            throw new Exception("Vehículo no encontrado");
        }

        // Hora de salida automática
        salida.setSalida(LocalDateTime.now());

        long minutos = Duration
                .between(salida.getIngreso(), salida.getSalida())
                .toMinutes();

        double horas = Math.ceil(minutos / 60.0);
        double monto = horas * TARIFA_POR_HORA;

        salida.setMonto(monto);

        // Persistencia
        dao.eliminarActivo(placa);
        dao.guardarHistorial(salida);

        return monto;
    }
}

