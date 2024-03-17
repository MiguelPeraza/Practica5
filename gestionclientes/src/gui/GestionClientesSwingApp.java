package gui;

import core.*;
import impl.ProveedorAlmacenamientoClientesFichero;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GestionClientesSwingApp extends JFrame {
  private JPanel contentPane;
  private JTextField txtNif;
  private JTextField txtNombre;
  private JTextField txtEmpleados;
  private JTextField txtApellidos;
  private JTextField txtFacturacion;
  private JList<String> list;
  private JButton btnEliminar;
  private JButton btnSalir;
  private JButton btnNuevo;
  private JCheckBox chckbxNacionalidadEu;
  private JButton btnActualizar;
  private JButton btnAceptar;
  private JButton btnCancelar;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GestionClientesSwingApp frame = new GestionClientesSwingApp();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public GestionClientesSwingApp() {
    setTitle("Gestion Clientes");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 400);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblClientes = new JLabel("Clientes");
    lblClientes.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblClientes.setBounds(10, 11, 57, 14);
    contentPane.add(lblClientes);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 36, 414, 120);
    contentPane.add(scrollPane);

    list = new JList<String>();
    list.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        String[] datos = list.getSelectedValue().split(":");
        String dni = datos[0];
        try {
          ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(
              "C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
          Cliente[] almacen = proveedor.getAll();
          for (Cliente cliente : almacen) {
            if (cliente.getNif().equals(dni)) {
              txtNif.setText(dni);
              txtNombre.setText(cliente.getNombre());
              txtApellidos.setText(cliente.getApellidos());
              txtEmpleados.setText(String.valueOf(cliente.getEmpleados()));
              txtFacturacion.setText(String.valueOf(cliente.getFacturacion()));
              btnEliminar.setEnabled(true);
              btnActualizar.setEnabled(true);
              txtNif.setEnabled(false);
              btnNuevo.setEnabled(false);

            }
          }

        } catch (IOException e1) {
          System.out.print("Problemas con el fichero");
        }
      }
    });

    DefaultListModel<String> modelo = new DefaultListModel<>();
    try {
      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(
          "C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
      Cliente[] almacen = proveedor.getAll();
      for (Cliente cliente : almacen) {
        modelo.addElement(cliente.getNif() + ": " + cliente.getApellidos() + ", " + cliente.getNombre());
      }
      list.setModel(modelo);
    } catch (IOException e) {
      System.out.print("Problemas con el fichero");
    }

    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    scrollPane.setViewportView(list);

    JLabel lblNif = new JLabel("NIF:");
    lblNif.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblNif.setBounds(10, 167, 34, 14);
    contentPane.add(lblNif);

    txtNif = new JTextField();
    txtNif.setEnabled(false);
    txtNif.setBounds(41, 165, 120, 20);
    contentPane.add(txtNif);
    txtNif.setColumns(10);

    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblNombre.setBounds(171, 167, 57, 14);
    contentPane.add(lblNombre);

    txtNombre = new JTextField();
    txtNombre.setEnabled(false);
    txtNombre.setColumns(10);
    txtNombre.setBounds(228, 165, 120, 20);
    contentPane.add(txtNombre);

    JLabel lblApellidos = new JLabel("Apellidos:");
    lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblApellidos.setBounds(10, 198, 63, 14);
    contentPane.add(lblApellidos);

    txtApellidos = new JTextField();
    txtApellidos.setEnabled(false);
    txtApellidos.setColumns(10);
    txtApellidos.setBounds(83, 196, 265, 20);
    contentPane.add(txtApellidos);

    JLabel lblEmpleados = new JLabel("Empleados:");
    lblEmpleados.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblEmpleados.setBounds(10, 227, 76, 14);
    contentPane.add(lblEmpleados);

    txtEmpleados = new JTextField();
    txtEmpleados.setEnabled(false);
    txtEmpleados.setColumns(10);
    txtEmpleados.setBounds(96, 225, 65, 20);
    contentPane.add(txtEmpleados);

    JLabel lblFacturacion = new JLabel("Facturación:");
    lblFacturacion.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblFacturacion.setBounds(171, 229, 76, 14);
    contentPane.add(lblFacturacion);

    txtFacturacion = new JTextField();
    txtFacturacion.setEnabled(false);
    txtFacturacion.setColumns(10);
    txtFacturacion.setBounds(257, 227, 91, 20);
    contentPane.add(txtFacturacion);

    JLabel lblNacionalidadEu = new JLabel("¿Es nacionalidad la UE?");
    lblNacionalidadEu.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblNacionalidadEu.setBounds(10, 262, 152, 14);
    contentPane.add(lblNacionalidadEu);

    chckbxNacionalidadEu = new JCheckBox("");
    chckbxNacionalidadEu.setEnabled(false);
    chckbxNacionalidadEu.setBounds(165, 258, 97, 23);
    contentPane.add(chckbxNacionalidadEu);

    btnNuevo = new JButton("Nuevo");
    btnNuevo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnAceptar.setEnabled(true);
        txtNif.setEnabled(true);
        txtNombre.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtEmpleados.setEnabled(true);
        txtFacturacion.setEnabled(true);
        chckbxNacionalidadEu.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnNuevo.setEnabled(false);
        
        btnAceptar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            list.setModel(modelo);
            try {
              // Saca la información del archivo
              ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(
                  "C:\\Users\\maper\\OneDrive\\Escritorio\\Clientes.dat");
              Clientes almacen = new Clientes(proveedor);

              try {
                // Se le piden al usuario los datos
                String nif = txtNif.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellidos.getText();
                int empleados = Integer.parseInt(txtEmpleados.getText());
                double facturacion = Double.parseDouble(txtFacturacion.getText());
                Boolean nacionalidadEu = chckbxNacionalidadEu.isSelected();
                // Crea y añade el cliente al archivo
                Cliente cliente = new Cliente(nif, apellido, nombre, empleados, facturacion, nacionalidadEu);
                almacen.addCliente(cliente);
                modelo.addElement(cliente.getNif() + ": " + cliente.getApellidos() + ", " + cliente.getNombre());
                System.out.println("Operación realizada con éxito");
                txtNif.setText("");
                txtNombre.setText("");
                txtApellidos.setText("");
                txtEmpleados.setText("");
                txtFacturacion.setText("");
                if (nacionalidadEu.equals(true)) {
                  chckbxNacionalidadEu.doClick();
                }
              } catch (IllegalArgumentException e31) {
                // Se lanza si el dni es incorrecto
                System.out.print("Ha introducido un dato erroneo");

              } catch (ClientesException e41) {
                // Se lanza si el dni esta repetido
                System.out.print("Ha introducido un dni repetido");

              }

            } catch (IOException e1) {
              e1.printStackTrace();
            }

          }
        });
        btnCancelar.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            btnAceptar.setEnabled(false);
            txtNif.setEnabled(false);
            txtNombre.setEnabled(false);
            txtApellidos.setEnabled(false);
            txtEmpleados.setEnabled(false);
            txtFacturacion.setEnabled(false);
            chckbxNacionalidadEu.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnNuevo.setEnabled(true);
            
          }});
      }

    });

    if (list.getSelectedValue() != null) {
      txtNif.setText("a");
      txtNombre.setText("a");
      txtApellidos.setText("a");
      txtEmpleados.setText("a");
      txtFacturacion.setText("a");
    }
    btnNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnNuevo.setBounds(10, 295, 89, 23);
    contentPane.add(btnNuevo);

    btnEliminar = new JButton("Eliminar");
    btnEliminar.setEnabled(false);
    btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnEliminar.setBounds(109, 295, 89, 23);
    contentPane.add(btnEliminar);

    btnActualizar = new JButton("Actualizar");
    btnActualizar.setEnabled(false);
    btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnActualizar.setBounds(208, 295, 89, 23);
    contentPane.add(btnActualizar);

    btnSalir = new JButton("Salir");
    btnSalir.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnSalir.setBounds(307, 295, 89, 23);
    contentPane.add(btnSalir);

    btnAceptar = new JButton("Aceptar");
    btnAceptar.setEnabled(false);
    btnAceptar.setBounds(10, 327, 89, 23);
    contentPane.add(btnAceptar);

    btnCancelar = new JButton("Cancelar");
    btnCancelar.setEnabled(false);
    btnCancelar.setBounds(109, 329, 89, 23);
    contentPane.add(btnCancelar);
  }
}
