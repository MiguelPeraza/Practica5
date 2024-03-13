package core;

import java.util.ArrayList;
import java.util.List;

/*
 * Clase Clientes: en esta clase se administra el almacenamirnto los objetos cliente. Tambien se pueden visitar 
 */
public class Clientes {

  private ProveedorAlmacenamientoClientes proveedorAlmacenamientoClientes;

  /*
   * Constructor
   * 
   * @param proveedorAlmacenamientoClientes: Proveedor de almacenamiento a emplear
   * para leer y escribir los clientes desde o hacia almacenamiento secundario
   */
  public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamientoClientes) {
    // Verifica que proveedorAlmacenamientoClientes no sea nulo
    if (proveedorAlmacenamientoClientes == null) {
      throw new NullPointerException();
    }
    this.proveedorAlmacenamientoClientes = proveedorAlmacenamientoClientes;
  }

  /*
   * Método addCliente: Este método se encarga de añadir a
   * proovedorAlmacenamientoClientes los clientes
   */
  public void addCliente(Cliente cliente) {
    // Verifica que cliente no sea nulo para poder usar el método
    if (cliente == null) {
      throw new NullPointerException();
    }
    // Verifica si existe algun cliente contiene el dni del cliente ya qie los dni
    // son únicos por lo que solo puede haber uno
    Cliente clienteExistente = getByNif(cliente.getNif());
    if (clienteExistente != null) {
      throw new ClientesException();
    }

    Cliente[] todosLosClientes = proveedorAlmacenamientoClientes.getAll();
    Cliente[] nuevo = new Cliente[todosLosClientes.length + 1];
    // Añade todos los cliente que esten almacenados en
    // proveedorAlmacenamientoClientes al nuevo contenedor
    for (int i = 0; i < todosLosClientes.length; i++) {
      nuevo[i] = todosLosClientes[i];
    }
    // Añade el nuevo cliente al contenedor
    nuevo[nuevo.length - 1] = cliente;
    proveedorAlmacenamientoClientes.saveAll(nuevo);
  }

  /*
   * Método getByNif:
   * 
   * @return Este método devuelve un cliente si hay alguno que tiene el mismo nif
   * que el cliente sino devuelve null
   */
  public Cliente getByNif(String nif) {
    // Verifica que el nif que se da no sea nulo
    if (nif == null) {
      throw new NullPointerException();
    }
    // Recorre todo los nif del contenedor para ver si hay alguno que coincida
    Cliente[] todosLosClientes = proveedorAlmacenamientoClientes.getAll();
    for (Cliente cliente : todosLosClientes) {
      if (cliente.getNif().equals(nif)) {
        // Si se encuentra un cliente con el nif dado devolvera ese cliente
        return cliente;
      }
    }
    // Si no devuelve null
    return null;
  }

  /*
   * Método removeCliente: Este método se encarga de eliminar un cliente de el
   * contenedor y actualizar el tamaño de este
   */
  public void removeCliente(String nif) {
    // Verifica que el nif dado no sea nulo
    if (nif == null) {
      throw new NullPointerException();
    }
    // Saca los clientes desde proveedorAlmacenamientoClientes
    Cliente[] todosLosClientes = proveedorAlmacenamientoClientes.getAll();
    List<Cliente> clientesActualizados = new ArrayList<>();
    boolean clienteActualizado = false;
    // Añade a la lista todos los clientes que no tengan el nif proporcionado
    for (Cliente cliente : todosLosClientes) {
      if (!cliente.getNif().equals(nif)) {
        clientesActualizados.add(cliente);
        
      }else {
        clienteActualizado = true;
      }
    }
    // Sinno se ecuentra ningún cliente con el nif proporcionado salta ClientesException
    if (!clienteActualizado) {
      throw new ClientesException();
    }
    
    // Crea un array, del tamaño de la lista actualizada con todos los clientes.
    Cliente[] clientesActualizadosArray = new Cliente[clientesActualizados.size()];
    // Pasa los datos de la lista al array
    clientesActualizados.toArray(clientesActualizadosArray);
    proveedorAlmacenamientoClientes.saveAll(clientesActualizadosArray);
    
  }

  /*
   * Método updateCliente:Actualiza un cliente ya existente. El cliente se
   * actualiza inmediatamente en el almacenamiento secundario
   */
  public void updateCliente(Cliente cliente) {
    // Verifica que el nif dado no sea nulo
    if (cliente == null) {
      throw new NullPointerException();
    }
    // Saca los clientes desde proveedorAlmacenamientoClientes
    Cliente[] todosLosClientes = proveedorAlmacenamientoClientes.getAll();
    List<Cliente> clientesActualizados = new ArrayList<>();
    boolean clienteActualizado = false;
    // Añade a la lista todos los clientes que no tengan el nif del cliente
    // proporcionado
    for (Cliente clienteActual : todosLosClientes) {
      // Cuando encuentra el cliente que tenga el mismo nif que el cliente
      // proporcionado se añade el cleinte proporcionado, en vez del aniguo
      if (clienteActual.getNif().equals(cliente.getNif())) {
        clientesActualizados.add(cliente);
        clienteActualizado = true;
      } else {
        clientesActualizados.add(clienteActual);
      }
    }
    // Sinno se ecuentra ningún cliente con el nif proporcionado salta ClientesException
    if (!clienteActualizado) {
      throw new ClientesException();
    }
    // Crea un array, del tamaño de la lista actualizada con todos los clientes
    Cliente[] clientesActualizadosArray = new Cliente[clientesActualizados.size()];
    // Pasa los datos de la lista al array
    clientesActualizados.toArray(clientesActualizadosArray);
    proveedorAlmacenamientoClientes.saveAll(clientesActualizadosArray);
  }

  public void visita(VisitadorClientes visitador) {
    if (visitador == null) {
      throw new NullPointerException();
    }
  }

}
