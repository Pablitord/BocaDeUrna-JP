package ec.edu.puce.elecciones.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Prefecto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CrearPrefecto extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;

	private Prefecto prefecto;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JComboBox<String> comboBox;

	private List<Prefecto> prefectos;
	private int idPrefecto;
	private JTextField txtEdad;
	
	public CrearPrefecto(List<Prefecto> prefectos) {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		idPrefecto=1;
		this.prefectos=prefectos;
		setTitle("CANDIDATOS A PREFECTO");
		setBounds(100, 100, 529, 385);
		getContentPane().setLayout(null);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(71, 49, 70, 15);
		getContentPane().add(lblNombres);

		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarPrefecto();
			}
		});
		txtNombre.setBounds(138, 47, 86, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(68, 78, 117, 25);
		getContentPane().add(btnNuevo);

		btnGuardar = new JButton("Agregar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(195, 78, 117, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(324, 78, 117, 25);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 121, 416, 224);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Edad", "Provincia" }));
		scrollPane.setViewportView(table);
		
		JLabel lblNombres_1 = new JLabel("Provincia");
		lblNombres_1.setBounds(71, 20, 70, 15);
		getContentPane().add(lblNombres_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Azuay","Bolivar" ,"Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Sucumbíos", "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo", "Francisco De Orellana", "Tungurahua", "Zamora Chinchipe"}));
		comboBox.setBounds(138, 11, 231, 24);
		getContentPane().add(comboBox);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(253, 49, 70, 15);
		getContentPane().add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(311, 46, 86, 19);
		getContentPane().add(txtEdad);

		model = (DefaultTableModel) table.getModel();
		agregarFila();
	}

	private void nuevo() {
		prefecto = new Prefecto();
		txtNombre.setText(prefecto.getNombre());
		txtEdad.setText(prefecto.getEdad());
	}

	private void agregarPrefecto() {
		try {
	        String edadTexto = txtEdad.getText().trim();
	        if (edadTexto.isEmpty()) {
	            throw new IllegalArgumentException("La edad no puede estar vacía.");
	        }

	        int edad = Integer.parseInt(edadTexto);

	        if (edad <= 0) {
	            throw new IllegalArgumentException("La edad debe ser mayor que 0.");
	        }
	        if (edad <= 16) {
	            throw new IllegalArgumentException("Debe ser mayor de 18 años.");
	        }
	        if (edadTexto.length() > 2) {
	            throw new IllegalArgumentException("La edad no puede tener más de 2 dígitos.");
	        }
	        

	        prefecto = new Prefecto();
	        prefecto.setId(idPrefecto);
	        prefecto.setNombre(txtNombre.getText().trim());
	        prefecto.setEdad(edadTexto);
	        prefecto.setProvincia((String) comboBox.getSelectedItem().toString());
	        prefectos.add(prefecto);
	        agregarFila();

	        txtNombre.setText("");
	        txtEdad.setText("");
	        idPrefecto++;
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void agregarFila() {
		model.setRowCount(0);
		for (Prefecto prefecto : prefectos) {
			Object[] fila = new Object[3];
			fila[0] = prefecto.getNombre();
			fila[1] = prefecto.getEdad();
			fila[2] = prefecto.getProvincia();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo) {
			nuevo();
		} else if (e.getSource() == btnGuardar) {
			agregarPrefecto();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
		else if (e.getSource() == txtNombre) {
			agregarPrefecto();
		}
	}

	public List<Prefecto> getPrefectos() {
		return prefectos;
	}

	public void setPrefectos(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
	}
}
