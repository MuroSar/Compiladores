package interfaz;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import compilador.Lexico;

public class Principal extends JFrame {
	
	//TAMAÑO DE PANTALLA..
	private int comienzaX = 0;
	private int comienzaY = 0;
	private int ancho = 1000;
	private int alto = 800;
	//TAMAÑO DE PANTALLA..
	
	private Lexico lexico;
	private File archivoACargar;
	private boolean archivoCargado = false;
	private JLabel lblPizarra;
	
	public Principal() {
		setResizable(false);
		setBounds(comienzaX, comienzaY, ancho, alto);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/logo.png"));
        setTitle("Compilador Marin-Sarti");
        lexico = new Lexico(this);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mnArchivo.add(mntmNuevo);
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ACA LIMPIAMOS PANTALLA...
				lexico.nuevo();
			}
		});
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mnArchivo.add(mntmCargar);
		mntmCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarArchivo();
			}
		});
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ACA SALIMOS..
			}
		});
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mnAyuda.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "TP Diseño de Compiladores 2017 \n\n  Cynthia Romina Marin - Mauro Sarti");
			}
		});
		
		lblPizarra = new JLabel("aca se va a ir cargandoooo");
		lblPizarra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPizarra.setBackground(Color.WHITE);
				
		JButton btnCargarArchivo = new JButton("Cargar archivo");
		btnCargarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	cargarArchivo();
            }
        });
		
		JButton btnSeguiente = new JButton("Seguiente");
		btnSeguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if(!archivoCargado)
            	{
            		cargarArchivo();
            	}
            	mostrarToken();
            }
        });
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPizarra, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 534, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCargarArchivo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeguiente, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPizarra, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCargarArchivo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSeguiente, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(229, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}

	private void cargarArchivo()
	{
		JFileChooser buscador = new JFileChooser();
		buscador.setVisible(true);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("txt", "txt");
		buscador.setFileFilter(filtro);
		int valorRetorno = buscador.showOpenDialog(null);
		if (valorRetorno == JFileChooser.APPROVE_OPTION) {
			archivoACargar = buscador.getSelectedFile();
			lexico.cargar(archivoACargar);
			this.archivoCargado = true;
		}
	}
	
	private void mostrarToken()
	{
		String token;
		token = lexico.getToken();
		lblPizarra.setText(lblPizarra.getText() + "\n" + token);
	}

	public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { 
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Principal ppal=new Principal();
            	//ppal.setSize(200, 200);
                //ppal.pack();
                ppal.setVisible(true);
            }
        });
    }

}
