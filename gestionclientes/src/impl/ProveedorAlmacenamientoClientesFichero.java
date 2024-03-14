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

/*
 * Clase ProveedorAlmacenamientoClientesFichero: esta clase se se encarga de sacar la información de los clientes de un fichero, además de escrir esta misma y tambien si el fichero no se ecuentra, se crea
 */

public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {

  // Atributos
  // Almacen de clientes
  private Set<Cliente> clientes;
  // Ruta que se va a proporcionar
  private String rutaFichero;

  /*
   * Constructor
   * 
   * @param : rutaFichero que esta va a ser la ruta del archivo, o del que se saca
   * o se escribe la información o del dichero que vamos a crear
   */
  public ProveedorAlmacenamientoClientesFichero(String rutaFichero) throws IOException {

    this.clientes = new HashSet<>();
    this.rutaFichero = rutaFichero;
  }

  /*
   * Método contains:
   * 
   * @return Devuelve tue o false si el contenedor clientes contiene el cliente
   * dado
   */
  public boolean contains(Cliente cliente) {
    return clientes.contains(cliente);
  }

  /*
   * Método size:
   * 
   * @return Devuelveel tamaño del contenedor
   */
  public int size() {
    return clientes.size();
  }

  /*
   * Método getAll
   * 
   * @return Este metodo de vuelve, despues de haber sacado la información del
   * fichero dado, un contenedor con todos los clientes creados y si no existe el
   * fichero lo crea
   */
  @Override
  public Cliente[] getAll() {
    // Convierte la ruta del fichero de String a path para poder encontrar el
    // fichero
    Path ruta = Path.of(rutaFichero);
    String linea = null;
    try {
      // Entra en el archivo, si o enceuntra
      BufferedReader flujoTexto = new BufferedReader(new FileReader(rutaFichero));
      // Lee el archivo linea a linea hasta acbarlo y saca la información de este para
      // crear el cliente
      do {
        linea = flujoTexto.readLine();
        if (linea != null) {
          // Se utiliza split para semapara la linea en datos,ya que estos se separan por
          // una coma, y los añade a un array de string
          String[] datos = linea.split(",");
          // Convierte los datos si hay que convertilos y crea el cliente
          Cliente clientesacado = new Cliente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]),
              Double.parseDouble(datos[4]), Boolean.parseBoolean(datos[5]));
          // añade el cliente al contenedor que va a devolver
          clientes.add(clientesacado);
        }
      } while (linea != null);
      flujoTexto.close();
      return clientes.toArray(new Cliente[clientes.size()]);
    } catch (FileNotFoundException e) {
      // Si no consigue el archivo, este método lo que hace es que lo crea
      // devuelve un contenedor vacío
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
    //Entra en el archivo para escribirlo
    try {
      PrintWriter flujoTexto = new PrintWriter(new FileWriter(rutaFichero));
      // De cada cliente que está en el contenedor se sacan los datos y se escriben en
      // el fichero usanfo el formato del printf
      for (Cliente clienteAEscribir : clientes) {
        flujoTexto.printf("%s, %s, %s, %d, %.2f, %b%n", clienteAEscribir.getNif(), clienteAEscribir.getApellidos(),
            clienteAEscribir.getNombre(), clienteAEscribir.getEmpleados(), clienteAEscribir.getFacturacion(),
            clienteAEscribir.isNacionalUe());
      }
      flujoTexto.close();
    } catch (IOException e) {

      e.printStackTrace();
    }

  }

}
