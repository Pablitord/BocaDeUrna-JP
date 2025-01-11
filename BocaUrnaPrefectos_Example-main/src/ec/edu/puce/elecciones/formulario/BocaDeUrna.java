	package ec.edu.puce.elecciones.formulario;
	
	import javax.swing.JInternalFrame;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.JTable;
	import javax.swing.JScrollPane;
	import javax.swing.table.DefaultTableModel;
	
	import ec.edu.puce.elecciones.dominio.Prefecto;
	
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.util.*;
import java.util.Map;

import javax.swing.JPanel;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JComboBox;
	import javax.swing.DefaultComboBoxModel;
	
	public class BocaDeUrna extends JInternalFrame implements ActionListener {
		private static final long serialVersionUID = 1L;
	
		private JTable table;
		private DefaultTableModel model;
	
		private List<Prefecto> prefectos;
		private JPanel panel;
		private JButton btnCancelar;
		private JLabel lblNombres;
		private JComboBox provinciasComboBox;
		private JComboBox ciudadesComboBox;
		private JLabel lblCiudad;
		
		private static final Map<String, List<String>> provinciasYCiudades = new HashMap<>();
	    private static final Map<String, Map<String, Integer>> votosPorProvinciaYCiudad = new HashMap<>();  // Mapa para almacenar votos

		
		static {
	        provinciasYCiudades.put("Azuay", Arrays.asList("Cuenca", "Loja", "Gualaceo"));
	        provinciasYCiudades.put("Bolívar", Arrays.asList("Guaranda", "Chillanes", "Echeandía"));
	        provinciasYCiudades.put("Cañar", Arrays.asList("Azogues","Biblián", "Cañar"));
	        provinciasYCiudades.put("Carchi", Arrays.asList("Tulcán", "Mira", "San Gabriel"));
	        provinciasYCiudades.put("Chimborazo", Arrays.asList("Riobamba", "Alausí", "Guano"));
	        provinciasYCiudades.put("Cotopaxi", Arrays.asList("Latacunga", "Salcedo", "Pujilí"));
	        provinciasYCiudades.put("El Oro", Arrays.asList("Machala", "Pasaje", "Huaquillas"));
	        provinciasYCiudades.put("Esmeraldas", Arrays.asList("Esmeraldas", "Atacames", "Quinindé"));
	        provinciasYCiudades.put("Galápagos", Arrays.asList("Puerto Ayora", "San Cristóbal", "Isabela"));
	        provinciasYCiudades.put("Guayas", Arrays.asList("Guayaquil", "Samborondón", "Durán"));
	        provinciasYCiudades.put("Imbabura", Arrays.asList("Ibarra", "Otavalo", "Cotacachi"));
	        provinciasYCiudades.put("Loja", Arrays.asList("Loja", "Catamayo", "Vilcabamba"));
	        provinciasYCiudades.put("Los Ríos", Arrays.asList("Babahoyo", "Quevedo", "Mocache"));
	        provinciasYCiudades.put("Manabí", Arrays.asList("Manta", "Portoviejo", "Chone"));
	        provinciasYCiudades.put("Morona Santiago", Arrays.asList("Macas", "Sucúa", "Gualaquiza"));
	        provinciasYCiudades.put("Napo", Arrays.asList("Tena", "Archidona", "El Chaco"));
	        provinciasYCiudades.put("Pichincha", Arrays.asList("Quito", "Rumiñahui", "Cayambe"));
	        provinciasYCiudades.put("Santa Elena", Arrays.asList("La Libertad", "Salinas", "Santa Elena"));
	        provinciasYCiudades.put("Santo Domingo de los Tsáchilas", Arrays.asList("Santo Domingo", "La Concordia", "Quevedo"));
	        provinciasYCiudades.put("Francisco de Orellana", Arrays.asList("Puerto Francisco de Orellana", "El Coca", "Joya de los Sachas"));
	        provinciasYCiudades.put("Tungurahua", Arrays.asList("Ambato", "Baños", "Pillaro"));
	        provinciasYCiudades.put("Zamora-Chinchipe", Arrays.asList("Zamora", "Loja", "Yacuambi"));
	        provinciasYCiudades.put("Sucumbíos", Arrays.asList("Nueva Loja", "Cuyabeno", "Lago Agrio"));
	        provinciasYCiudades.put("Pastaza", Arrays.asList("Puyo", "Mera", "Arajuno"));
	        
	        
	    }
	
		public BocaDeUrna(List<Prefecto> prefectos) {
			this.prefectos = prefectos;
			setTitle("BOCA DE URNA - REGISTRO");
			setBounds(100, 100, 600, 427);
			getContentPane().setLayout(null);
	
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 172, 566, 167);
			getContentPane().add(scrollPane);
	
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(model.getValueAt(0, 0));
				}
			});
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Votos" }));
			scrollPane.setViewportView(table);
	
			panel = new JPanel();
			panel.setBounds(12, 76, 566, 84);
			getContentPane().add(panel);
	
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(this);
			btnCancelar.setBounds(238, 361, 117, 25);
			getContentPane().add(btnCancelar);
			
			lblNombres = new JLabel("Selecciona tu provincia");
			lblNombres.setBounds(12, 21, 137, 15);
			getContentPane().add(lblNombres);
			
			provinciasComboBox = new JComboBox();
			provinciasComboBox.setModel(new DefaultComboBoxModel(new String[] {
					"Azuay","Bolívar" ,"Cañar", 
					"Carchi", "Chimborazo", "Cotopaxi", 
					"El Oro", "Esmeraldas", "Galápagos", 
					"Guayas", "Imbabura", "Loja", 
					"Los Ríos", "Manabí", "Morona Santiago", 
					"Napo", "Sucumbíos", "Pastaza", 
					"Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas",
					"Francisco de Orellana", "Tungurahua", "Zamora-Chinchipe"}));
			
			provinciasComboBox.setBounds(12, 41, 231, 24);
			getContentPane().add(provinciasComboBox);
			
			ciudadesComboBox = new JComboBox();
			ciudadesComboBox.setModel(new DefaultComboBoxModel(new String[] {
				    "Cuenca", "Loja", "Gualaceo", 
				    "Guaranda", "Chillanes", "Echeandía", 
				    "Azogues", "Biblián", "Cañar", 
				    "Tulcán", "Mira", "San Gabriel", 
				    "Riobamba", "Alausí", "Guano", 
				    "Latacunga", "Salcedo", "Pujilí", 
				    "Machala", "Pasaje", "Huaquillas", 
				    "Esmeraldas", "Atacames", "Quinindé", 
				    "Puerto Ayora", "San Cristóbal", "Isabela", 
				    "Guayaquil", "Samborondón", "Durán", 
				    "Ibarra", "Otavalo", "Cotacachi", 
				    "Loja", "Catamayo", "Vilcabamba", 
				    "Babahoyo", "Quevedo", "Mocache", 
				    "Manta", "Portoviejo", "Chone", 
				    "Macas", "Sucúa", "Gualaquiza", 
				    "Tena", "Archidona", "El Chaco", 
				    "Quito", "Rumiñahui", "Cayambe", 
				    "La Libertad", "Salinas", "Santa Elena", 
				    "Santo Domingo", "La Concordia", "Quevedo", 
				    "Puerto Francisco de Orellana", "El Coca", "Joya de los Sachas", 
				    "Ambato", "Baños", "Pillaro", 
				    "Zamora", "Loja", "Yacuambi", 
				    "Nueva Loja", "Cuyabeno", "Lago Agrio", 
				    "Puyo", "Mera", "Arajuno"
				}));			ciudadesComboBox.setBounds(330, 41, 231, 24);
			getContentPane().add(ciudadesComboBox);
			
			lblCiudad = new JLabel("Selecciona tu ciudad");
			lblCiudad.setBounds(330, 21, 146, 15);
			getContentPane().add(lblCiudad);
	
			model = (DefaultTableModel) table.getModel();
			cargarCandidatos();
			llenarTabla();
			
			provinciasComboBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					actualizarCiudades();
					
				}
			});
		}
		
		private void cargarCandidatos() {
	        int x = 0;
	        for (Prefecto prefecto : prefectos) {
	            JButton btnPrefecto = new JButton(prefecto.getNombre());
	            btnPrefecto.setBounds(x * 100, 10, 90, 50);
	            btnPrefecto.addActionListener(this);
	            panel.setLayout(null);
	            panel.add(btnPrefecto);
	            x++;
	        }
	    }
	
		private void llenarTabla() {
		    model.setRowCount(0);
		    for (Prefecto prefecto : prefectos) {
		        Object[] fila = new Object[2];
		        fila[0] = prefecto.getNombre();
		        fila[1] = prefecto.getVotos();
		        model.addRow(fila);
		    }

		    //actualizarVotosPorProvinciaYCiudad();
		}

		
		private void actualizarCiudades() {
		    String provinciaSeleccionada = (String) provinciasComboBox.getSelectedItem();
		    if (provinciaSeleccionada != null) {
		        List<String> ciudades = provinciasYCiudades.get(provinciaSeleccionada);
		        ciudadesComboBox.removeAllItems();
		        for (String ciudad : ciudades) {
		            ciudadesComboBox.addItem(ciudad);
		        }

		        votosPorProvinciaYCiudad.computeIfAbsent(provinciaSeleccionada, k -> new HashMap<>());
		        
		        for (String ciudad : provinciasYCiudades.get(provinciaSeleccionada)) {
		            votosPorProvinciaYCiudad.get(provinciaSeleccionada).put(ciudad, 0);
		        }

		        llenarTabla();
		    }
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (e.getSource() == btnCancelar) {
		        dispose();
		    }

		    for (Prefecto prefecto : prefectos) {
		        if (prefecto.getNombre().equals(e.getActionCommand())) {
		            String provinciaSeleccionada = (String) provinciasComboBox.getSelectedItem();
		            String ciudadSeleccionada = (String) ciudadesComboBox.getSelectedItem();

		            if (provinciaSeleccionada != null && ciudadSeleccionada != null) {
		                votosPorProvinciaYCiudad
		                    .computeIfAbsent(provinciaSeleccionada, k -> new HashMap<>())
		                    .merge(ciudadSeleccionada, 1, Integer::sum);

		                votosPorProvinciaYCiudad
		                    .computeIfAbsent(provinciaSeleccionada, k -> new HashMap<>())
		                    .merge("Provincia", 1, Integer::sum);
		            }

		            prefecto.setVotos(prefecto.getVotos() + 1);
		            llenarTabla();
		        }
		    }
		}
		
		private void actualizarVotosPorProvinciaYCiudad() {
		    for (String provincia : votosPorProvinciaYCiudad.keySet()) {
		        Map<String, Integer> votosPorCiudad = votosPorProvinciaYCiudad.get(provincia);
		        
		        for (Map.Entry<String, Integer> entry : votosPorCiudad.entrySet()) {
		            String ciudad = entry.getKey();
		            Integer votos = entry.getValue();
		            
		            Object[] fila = new Object[2];
		            fila[0] = ciudad + " (" + provincia + ")";
		            fila[1] = votos;
		            model.addRow(fila);
		        }
		    }
		}

		
		
	
		public List<Prefecto> getPrefectos() {
			return prefectos;
		}
	
		public void setPrefectos(List<Prefecto> prefectos) {
			this.prefectos = prefectos;
		}
	}
