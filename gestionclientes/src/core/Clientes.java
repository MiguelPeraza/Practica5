package core;

import java.util.ArrayList;

public class Clientes {

  private ProveedorAlmacenamientoClientes proveedorAlmacenamientoClientes;

  public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamientoClientes) {
    if (proveedorAlmacenamientoClientes == null) {
      throw new NullPointerException();
    }
    this.proveedorAlmacenamientoClientes = proveedorAlmacenamientoClientes;
  }

  public void addCliente(Cliente cliente) {
    if (cliente == null) {
      throw new NullPointerException();
    }

    Clientes clientes = new Clientes(proveedorAlmacenamientoClientes);
    
    if(clientes.getByNif(cliente.getNif()) != null && proveedorAlmacenamientoClientes.getAll().length > 0){
      throw new ClientesException();
    }
    
    Cliente[] todosLosClientes = proveedorAlmacenamientoClientes.getAll();
    Cliente[] nuevo = new Cliente[(todosLosClientes.length + 1)];
    
    if (todosLosClientes.length > 0) {
      for (int i = 0; i < todosLosClientes.length; i++) {
        nuevo[i] = todosLosClientes[i];
      }
      nuevo[(todosLosClientes.length + 1)] = cliente;
      proveedorAlmacenamientoClientes.saveAll(nuevo);
    } else {
      nuevo[0] = cliente;
      proveedorAlmacenamientoClientes.saveAll(nuevo);
    }
  }

  public Cliente getByNif(String nif) {
    if (nif == null) {
      throw new NullPointerException();
    }
    
    Cliente[] todosLosClientes = proveedorAlmacenamientoClientes.getAll();
    boolean nifEncontrado = false;
    int posicionEncontrada = 0;
    for (int i = 0; i < todosLosClientes.length; i++) {
      if (todosLosClientes[i].getNif().equals(nif)) {
        nifEncontrado = true;
        posicionEncontrada = i;
      }
    }
    if (nifEncontrado) {
      return todosLosClientes[posicionEncontrada];
    }else {
      return null;
    }

  }

  public void removeCliente(String nif) {
    if (nif == null) {
      throw new NullPointerException();
    }
  }

  public void updateCliente(Cliente cliente) {
    if (cliente == null) {
      throw new NullPointerException();
    }
  }

  public void visita(VisitadorClientes visitador) {
    if (visitador == null) {
      throw new NullPointerException();
    }
  }

}
