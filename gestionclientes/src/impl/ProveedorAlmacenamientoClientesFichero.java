package impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import core.Cliente;
import core.ClientesException;
import core.ProveedorAlmacenamientoClientes;

public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {

  // Atributos
  // Almacen de clientes
  private Set<Cliente> clientes;
  private String rutaFichero;

  public ProveedorAlmacenamientoClientesFichero(String rutaFichero) throws IOException {

    this.clientes = new HashSet<>();
    this.rutaFichero = rutaFichero;
  }

  public boolean contains(Cliente cliente) {
    return clientes.contains(cliente);
  }

  public int size() {
    return clientes.size();
  }

  @Override
  public Cliente[] getAll() {
    Path ruta = Path.of(rutaFichero);
    String linea = null;
    try {
      BufferedReader flujoTexto = new BufferedReader(new FileReader(rutaFichero));
      do {
        linea = flujoTexto.readLine();
        if (linea != null) {
          String[] datos = linea.split(",");
          Cliente clientesacado = new Cliente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]),
              Double.parseDouble(datos[4]), Boolean.parseBoolean(datos[5]));
          clientes.add(clientesacado);
        }
      } while (linea != null);
      flujoTexto.close();
      return clientes.toArray(new Cliente[clientes.size()]);
    } catch (FileNotFoundException e) {

      try {
        Files.createFile(ruta);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      return clientes.toArray(new Cliente[0]);

    } catch (IOException e) {
      e.printStackTrace();
      return clientes.toArray(new Cliente[0]);

    }
  }

  @Override
  public void saveAll(Cliente[] clientes) {
    
      try {
        PrintWriter flujoTexto = new PrintWriter(new FileWriter(rutaFichero));
        for(Cliente clienteAEscribir :clientes ) {
          flujoTexto.printf("%s, %s, %s, %d, %.2f, %b%n", clienteAEscribir.getNif(), clienteAEscribir.getApellidos(), clienteAEscribir.getNombre(), clienteAEscribir.getEmpleados(), clienteAEscribir.getFacturacion(), clienteAEscribir.isNacionalUe());
        }
        flujoTexto.close();
      } catch (IOException e) {
        
        e.printStackTrace();
      }
      
   

  }

}
