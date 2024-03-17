package consola;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import core.Cliente;
import core.Clientes;
import core.ClientesException;
import core.VisitadorClientes;
import impl.ProveedorAlmacenamientoClientesFichero;

public class GestionClientesConsolaApp {

  private static final String ARCHIVO = "Clientes.dat";

  public GestionClientesConsolaApp() {

  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    // Creamos un objeto de la clase
    GestionClientesConsolaApp app = new GestionClientesConsolaApp();
    // Inicializamos la opción elegida a un valor invalido
    int opcion = -1;
    // Mientras no se elija una opción correcta
    do {
      // Mostramos el menu
      System.out.println();
      System.out.println("MENU PRINCIPAL");
      System.out.println("--------------");
      System.out.println("1. Listar clientes");
      System.out.println("2. Añadir Cliente");
      System.out.println("3. Editar un cliente");
      System.out.println("4. Eliminar un cliente");
      System.out.println("0. Salir del programa");
      System.out.println("Elija una opción ( 0 - 4 ): ");
      try {
        opcion = Integer.parseInt(sc.nextLine());
        // Si la opción está en rango se devuelve. Si no se muestra error y se da otra
        // vuelta
        if (opcion >= 0 && opcion <= 4) {
          switch (opcion) {
          case 1:
            app.listar();
            break;
          case 2:
            app.añadir();
            break;
          case 3:
            app.actualizar();
            break;
          case 4:
            app.eliminar();
            break;

          }
        } else {
          System.out.println("Opción elegida incorrecta. Debe introducir un número comprendido entre ( 0 - 4 )");
        }
      } catch (NumberFormatException e) {
        System.out.println("Opción elegida incorrecta. Debe introducir un número comprendido entre ( 0 - 4 )");
      }
    } while (opcion != 0);
    sc.close();
  }

  /*
   * Método eliminar: Este método se encarga de elminar del archivo la información
   * del cliente del que se provee el dni
   */
  private void eliminar() {
    System.out.println("ELIMINAR CLIENTE");
    System.out.println("--------------");
    Scanner sc = new Scanner(System.in);
    // Se pide el dni al usuario
    System.out.println("NIF del cliente a eliminar (8 números y la letra debe ser mayúscula)");
    String nif = sc.nextLine();
    try {
      // Se saca la infomación del archivo
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(
          "C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
      Clientes almacen = new Clientes(proveedor);
      // Se utiliza el método removeClente para eliminar el cliente del archivo
      almacen.removeCliente(nif);
      System.out.println("Operación realizada con éxito");

    } catch (IOException e) {
      // Se lanza si no se encuentra el archivo
      System.out.println("No se ha encontrado el archivo, liste los clientes para crearlo");
    } catch (ClientesException e) {
      // Se lanza si no se encuentra el cliente
      System.out.println("No se ha encontrado e cliente");
    }

  }

  private void actualizar() {

    System.out.println("EDITAR CLIENTE");
    System.out.println("--------------");
    Scanner sc = new Scanner(System.in);
    // Se pide el dni del cliente
    System.out.println("NIF del cliente a eliminar (8 números y la letra debe ser mayúscula)");
    String nif = sc.nextLine();
    // Saca la información del archivo
    try {
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(
          "C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
      Clientes almacen = new Clientes(proveedor);
      // Se le piden al usuario para editarlo
      System.out.println("Introduzca los datos del cliente");
      System.out.print("Nombre (Primera letra de cada palabra en mayúscula)");
      String nombre = sc.nextLine();
      System.out.print("Apellidos (Primera letra de cada palabra en mayúscula)");
      String apellido = sc.nextLine();
      System.out.print("Numero de empledos(Entero mayor que cero)");
      int empleados = Integer.parseInt(sc.nextLine());
      System.out.print("Facturación (valor real superior mayor que cero)");
      double facturacion = Double.parseDouble(sc.nextLine());
      System.out.println("¿Es de nacionalidad europea? (s/n)");
      String europeo = sc.nextLine();
      boolean europeoSiNo = false;

      if (europeo.toLowerCase().equals("s")) {
        europeoSiNo = true;
      } else if (europeo.toLowerCase().equals("n")) {
        europeoSiNo = false;
      } else {
        throw new IllegalArgumentException();
      }
      // Se crea el cliente que se va a actualizar
      Cliente cliente1 = new Cliente(nif, apellido, nombre, empleados, facturacion, europeoSiNo);
      // Con el método updateCliente actualizamos el cliente
      almacen.updateCliente(cliente1);
      System.out.println("Operación realizada con éxito");
    } catch (IllegalArgumentException e) {
      // Se lanza si el dni es incorrecto
      System.out.println("Hay un dato mal introducido");
    } catch (IOException e) {
      // Se lanza si no se encuentra el archivo
      System.out.println("No se ha encontrado el archivo, liste los clientes para crearlo");
    } catch (ClientesException e) {
      // Se lanza si el dni es incorrecto
      System.out.println("El dni es incorrecto");

    } catch (NullPointerException e) {
      // Se lanza si hay algún dato null
      System.out.println("Ningún campo debe ser null");

    }
  }

  private void añadir() {
    Scanner sc = new Scanner(System.in);
    ProveedorAlmacenamientoClientesFichero proveedor;
    System.out.println("AÑADIR CLIENTE");
    System.out.println("--------------");
    try {
      // Saca la información del archivo
      proveedor = new ProveedorAlmacenamientoClientesFichero("C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
      Clientes almacen = new Clientes(proveedor);

      try {
     // Se le piden al usuario los datos
        System.out.println("Introduzca los datos del cliente");
        System.out.println("NIF (8 números y la letra debe ser mayúscula)");
        String nif = sc.nextLine();
        System.out.print("Nombre (Primera letra de cada palabra en mayúscula)");
        String nombre = sc.nextLine();
        System.out.print("Apellidos (Primera letra de cada palabra en mayúscula)");
        String apellido = sc.nextLine();
        System.out.print("Numero de empledos(Entero mayor que cero)");
        int empleados = sc.nextInt();
        System.out.print("Facturación (valor real superior mayor que cero)");
        double facturacion = sc.nextDouble();
        System.out.println("¿Es de nacionalidad europea? (s/n)");
        String europeo = sc.next();
        boolean europeoSiNo = false;

        if (europeo.toLowerCase().equals("s")) {
          europeoSiNo = true;
        } else if (europeo.toLowerCase().equals("n")) {
          europeoSiNo = false;
        } else {
          throw new IllegalArgumentException();
        }
        // Crea y añade el cliente al archivo
        Cliente cliente = new Cliente(nif, apellido, nombre, empleados, facturacion, europeoSiNo);
        almacen.addCliente(cliente);
        System.out.println("Operación realizada con éxito");
      } catch (IllegalArgumentException e) {
        // Se lanza si el dni es incorrecto
        System.out.print("Ha introducido un dato erroneo");

      } catch (ClientesException e) {
        // Se lanza si el dni esta repetido
        System.out.print("Ha introducido un dni repetido");

      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void listar() {
    System.out.println("LISTAR CLIENTE");
    System.out.println("--------------");
    try {
      // Saca la información del fichero
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(
          "C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
      Clientes almacen = new Clientes(proveedor);

      almacen.visita(new VisitadorClientes() {
        // Usando el visitador se escriben los datos en el fichero
        @Override
        public void visita(Cliente cliente) {

        }

      });
    } catch (IOException e) {
      System.out.println("La ruta del archivo es incorrecta");
    }

  }

}
