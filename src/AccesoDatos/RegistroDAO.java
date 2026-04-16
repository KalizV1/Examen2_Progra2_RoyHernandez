/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import entidades.RegistroParqueo;
import entidades.Vehiculo;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    private final String ACTIVOS = "activos.txt";
    private final String HISTORIAL = "historial.txt";

    // ===============================
    // GUARDAR INGRESO (ACTIVO)
    // ===============================
    public void guardarIngreso(RegistroParqueo r) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ACTIVOS, true))) {
            bw.write(
                    r.getVehiculo().getPlaca() + ";" +
                            r.getVehiculo().getTipo() + ";" +
                            r.getIngreso()
            );
            
            bw.newLine();
        }
    }

    // ===============================
    // LEER VEHÍCULOS ACTIVOS
    // ===============================
    public List<RegistroParqueo> leerActivos() throws IOException {

        List<RegistroParqueo> lista = new ArrayList<>();
        File f = new File(ACTIVOS);

        if (!f.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                
                Vehiculo v = new Vehiculo(datos[0], datos[1]);
                LocalDateTime ingreso = LocalDateTime.parse(datos[2]);
                
                RegistroParqueo r = new RegistroParqueo(v, ingreso);
                lista.add(r);
            }
        }
        return lista;
    }

    // ===============================
    // ELIMINAR UN ACTIVO (SALIDA)
    // ===============================
    public void eliminarActivo(String placa) throws IOException {

        List<String> lineas = new ArrayList<>();
        File f = new File(ACTIVOS);

        if (!f.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith(placa + ";")) {
                    lineas.add(linea);
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACTIVOS))) {
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
        }
    }

    // ===============================
    // GUARDAR EN HISTORIAL
    // ===============================
    public void guardarHistorial(RegistroParqueo r) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(HISTORIAL, true))) {
            bw.write(
                    r.getVehiculo().getPlaca() + ";" +
                            r.getVehiculo().getTipo() + ";" +
                            r.getIngreso() + ";" +
                            r.getSalida() + ";" +
                            r.getMonto()
            );
            
            bw.newLine();
        }
    }

    // ===============================
    // LEER HISTORIAL (PASO 4)
    // ===============================
    public List<RegistroParqueo> leerHistorial() throws IOException {

        List<RegistroParqueo> lista = new ArrayList<>();
        File f = new File(HISTORIAL);

        if (!f.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                
                Vehiculo v = new Vehiculo(datos[0], datos[1]);
                
                RegistroParqueo r =
                        new RegistroParqueo(v, LocalDateTime.parse(datos[2]));
                r.setSalida(LocalDateTime.parse(datos[3]));
                r.setMonto(Double.parseDouble(datos[4]));
                
                lista.add(r);
            }
        }
        return lista;
    }
}

