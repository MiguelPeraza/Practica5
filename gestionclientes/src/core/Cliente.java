package core;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/*
 * Clase Cliente: en esta clase se crean los objetos cliente y se verifican todos los datos de este
 */

public class Cliente {

  // Atributos
  // DNI del cliente
  private String nif;
  // Nombre de pila del cliente
  private String nombre;
  // Apellidos del cliente
  private String apellidos;
  // Cantidad de empleados del cliente
  private int empleados;
  // Facturación del cliente
  private double facturacion;
  // Nacionalidad europea si o no
  private boolean nacionalUe;

  /*
   * Constructor
   * 
   * @param nif: numero de identificación del cliente. Debe componerce de 8
   * numeros y una letra, que concuenrde con el número, además de ser único.
   * 
   * @param nombre: nombre del cliente, no puede estar vacío ni blancos y debe
   * empezar por mayúsculas.
   * 
   * @param apellidos: apellidos del cliente, no puede estar vacío ni blancos y
   * debe empezar por mayúsculas.
   * 
   * @param empleados: numeros de empleados del clientre,Entero superior a cero.
   * 
   * @param facturacion: facturación anual del cliente, real superior a cero.
   * 
   * @param nacionalUe: indica si el cliente es de nacionalidad europea o no.
   */
  public Cliente(String nif, String apellidos, String nombre, int empleados, double facturacion, boolean nacionalUe) {

    // Verifica que apellido ni sea nulo, ni esté vacío y cumpla con que debe estar
    // en mayúscula la primera letra
    if (apellidos == null || apellidos.trim().isEmpty() || !letra1Correcto(apellidos) || !mayusculasBien(apellidos)) {
      throw new IllegalArgumentException();
    }
    // Verifica que nombre ni sea nulo, ni esté vacío y cumpla con que de tener la
    // primera letra en mayúsculas
    if (nombre == null || nombre.trim().isEmpty() || !letra1Correcto(nombre) || !mayusculasBien(nombre)) {
      throw new IllegalArgumentException();
    }
    // Verifica que nif sea correcto,inclutyendo la letra, además de que no sea nulo
    // ni esté vacío
    if (nif == null || nif.trim().isEmpty() || !validarDni(nif)) {
      throw new IllegalArgumentException();
    }
    // Verifica que facturación sea mayor que cero
    if (facturacion <= 0) {
      throw new IllegalArgumentException();
    }
    // Verifica que empleados sea mayor que cero
    if (empleados <= 0) {
      throw new IllegalArgumentException();
    }

    this.apellidos = apellidos;
    this.nombre = nombre;
    this.facturacion = facturacion;
    this.nif = nif;
    this.empleados = empleados;
    this.nacionalUe = nacionalUe;
  }

  /*
   * Método getApellidos:
   * 
   * @return Este método devuelve los apellidos del cliente
   */
  public String getApellidos() {
    return this.apellidos;
  }

  /*
   * Método getNombre:
   * 
   * @return Este método devuelve el nombre del cliente
   */
  public String getNombre() {
    return this.nombre;
  }

  /*
   * Método getEdad:
   * 
   * @return Este método devuelve la edad del cliente
   */
  public double getFacturacion() {
    return this.facturacion;
  }

  /*
   * Método getEdad:
   * 
   * @return Este método devuelve la edad del cliente
   */
  public int getEmpleados() {
    return this.empleados;
  }

  /*
   * Método getDni:
   * 
   * @return Este método devuelve el DNI del cliente
   */
  public String getNif() {
    return this.nif;
  }

  /*
   * Método getDni:
   * 
   * @return Este método devuelve el DNI del cliente
   */
  public boolean isNacionalUe() {
    return this.nacionalUe;
  }

  /*
   * Método letra1Correcto():
   * 
   * @return Verifica que la primera letra de la cadena sea mayúscula
   */
  private boolean letra1Correcto(String cadena) {
    // Saca la primera letra de la cadena
    char letra1 = cadena.charAt(0);
    // Comprueba que sea mayúscula
    if (Character.isUpperCase(letra1)) {
      return true;
    } else {
      return false;
    }

  }

  
  /*
   * Método privado mayuscyulasBien():
   * @return Verifica que después de cada espaciio haya una mayuscula en la cadena
   */
  private boolean mayusculasBien(String cadena) {
    int cantidadMayusculas = 0;
    int cantidadpalabras = 0;
    //Saca la cabtidad de palabras que tiene la cadena, identificandolas por los espacios
    for (int i = 0; i < cadena.length(); i++) {
      char caracter = cadena.charAt(i);
      if (caracter == ' ') {
        cantidadpalabras++;
        //verifica si la letra siguiente al espacio es mayúscula
        if (Character.isUpperCase(cadena.charAt((i + 1)))) {
          cantidadMayusculas++;
          //añade si es mayuscula, por lo que deberá ser tantos igual a las palabras la cadena
        }
      }

    }
    //se verifica que la cantidad de mayúsculas sea igual a cantidad de palabras 
    if (cantidadpalabras == cantidadMayusculas) {
      return true;
    } else {
      return false;
    }
  }
  
  /*
   * Método privado validarDni():
   * 
   * @return Verifica si el dni es un dni valido
   */
  private boolean validarDni(String nif) {
    String patronDNI = "\\d{8}[a-zA-Z]";
    // Se valida el patrón
    Pattern pattern = Pattern.compile(patronDNI);
    Matcher matcher = pattern.matcher(nif);
    if (!matcher.matches()) {
      return false;
    }
    // Se extraer el número y la letra del DNI
    String numeroString = nif.substring(0, 8);
    int numero = Integer.parseInt(numeroString);
    char letra = calcularLetraDNI(numero);
    // Se verifica la letra del DNI
    char letraProporcionada = nif.charAt(8);
    return letra == letraProporcionada;

  }

  /*
   * Método privado calcularLetraDNI:
   * 
   * @return Este método devuelve la letra que correspponde con el dni. Para esto
   * hay que pasarle el numero de este y el método la calcula.
   */
  private static char calcularLetraDNI(int numeroDNI) {
    // Comprueba que la letra sea igual al numero total de los numeros del dni
    // partido por 23
    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    int indiceLetra = numeroDNI % 23;
    return letras.charAt(indiceLetra);
  }
}
