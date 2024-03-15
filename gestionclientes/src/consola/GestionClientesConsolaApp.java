package consola;

import java.io.IOException;
import java.util.Scanner;

import core.Cliente;
import core.Clientes;
import core.VisitadorClientes;
import impl.ProveedorAlmacenamientoClientesFichero;

public class GestionClientesConsolaApp {

  private static final String ARCHIVO = "biblioteca.xml";


  public GestionClientesConsolaApp() {

  }

  public static void main(String[] args) {
    // Obtenemos los usuarios
    Scanner sc = new Scanner(System.in);
    // Creamos un objeto de la clase
    GestionClientesConsolaApp app = new GestionClientesConsolaApp();
    // Inicializamos la opción elegida a un valor invalido
    int opcion =  - 1;
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
      System.out.print("Elija una opción ( 0 - 4 ): ");
      try {
        opcion = Integer.parseInt(sc.next());
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
        e.printStackTrace();
      }
    }while (opcion!=0);
  }

  private void eliminar() {
    System.out.println("ELIMINAR CLIENTE");
    System.out.println("--------------");
    Scanner sc = new Scanner(System.in);
    System.out.println("NIF del cliente a eliminar (8 números y la letra debe ser mayúscula)");
    String nif = sc.nextLine();
    try {
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero("C:\\Users\\Alumnado\\Desktop\\Ejemplo.dat");
      Clientes almacen = new Clientes(proveedor); 
      almacen.removeCliente(nif);
      
    } catch (IOException e) {
      System.out.println("No se ha encontrado al cliente");
    }
    sc.close();
  }

  private void actualizar() {
    
    System.out.println("EDITAR CLIENTE");
    System.out.println("--------------");
    Scanner sc = new Scanner(System.in);
    System.out.println("NIF del cliente a eliminar (8 números y la letra debe ser mayúscula)");
    String nif = sc.nextLine();
    boolean nacionalidad = true;
    try {
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero("C:\\Users\\Alumnado\\Desktop\\Ejemplo.dat");
      Clientes almacen = new Clientes(proveedor); 
      Cliente a = almacen.getByNif(nif); 
      nacionalidad = a.isNacionalUe();
      almacen.removeCliente(nif);
      
      
    } catch (IOException e) {
      System.out.println("No se ha encontrado al cliente");
    }
    try {
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero("C:\\Users\\Alumnado\\Desktop\\Ejemplo.dat");
      Clientes almacen = new Clientes(proveedor); 
      System.out.println("Introduzca los datos del cliente");
      System.out.print("Nombre (Primera letra de cada palabra en mayúscula)");
      String nombre = sc.nextLine();
      System.out.print("Apellidos (Primera letra de cada palabra en mayúscula)");
      String apellido = sc.nextLine();
      System.out.print("Numero de empledos(Entero mayor que cero)");
      int empleados = sc.nextInt();
      System.out.print("Facturación (valor real superior mayor que cero)");
      double facturacion = sc.nextDouble();
      
      
      Cliente cliente = new Cliente(nif,apellido,nombre,empleados,facturacion,nacionalidad);
      almacen.addCliente(cliente);
      
      
      }catch (IllegalArgumentException e) {
        System.out.print("Ha introducido un dato erroneo");
      } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Operación realizada con éxito");
  }

  private void añadir() {
    Scanner sc = new Scanner(System.in);
    ProveedorAlmacenamientoClientesFichero proveedor;
    System.out.println("AÑADIR CLIENTE");
    System.out.println("--------------");
    try {
      proveedor = new ProveedorAlmacenamientoClientesFichero("C:\\Users\\Alumnado\\Desktop\\Ejemplo.dat");
      try {
      Clientes almacen = new Clientes(proveedor); 
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
      }else if(europeo.toLowerCase().equals("n")){
        europeoSiNo = false;
      }else {
        throw new IllegalArgumentException();
      }
      
      Cliente cliente = new Cliente(nif,apellido,nombre,empleados,facturacion,europeoSiNo);
      almacen.addCliente(cliente);
      System.out.println("Operación realizada con éxito");
      }catch (IllegalArgumentException e) {
        System.out.print("Ha introducido un dato erroneo");
        
      }
      
      } catch (IOException e) {
      e.printStackTrace();
    }
   
    sc.close();

  }

  private void listar() {
    System.out.println("LISTAR CLIENTE");
    System.out.println("--------------");
    try {
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero("C:\\Users\\Alumnado\\Desktop\\Ejemplo.dat");
      Clientes almacen = new Clientes(proveedor); 
      
      almacen.visita(new VisitadorClientes(){

        @Override
        public void visita(Cliente cliente) {
          // TODO Auto-generated method stub       
        }
             
      });
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
