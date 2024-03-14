package consola;

import java.util.Scanner;

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
      System.out.println("2. Buscar clientes por nombre de pila");
      System.out.println("3. Buscar clientes por apellidos");
      System.out.println("4. Buscar clientes por DNI");
      System.out.println("5. Buscar clientes por edad");
      System.out.println("0. Salir del programa");
      System.out.print("Elija una opción ( 0 - 4 ): ");
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
    }while (opcion!=0);
  }

  private void eliminar() {
    // TODO Auto-generated method stub

  }

  private void actualizar() {
    // TODO Auto-generated method stub

  }

  private void añadir() {
    // TODO Auto-generated method stub

  }

  private void listar() {
    // TODO Auto-generated method stub

  }

}
