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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import compilador.Lexico;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollBar;

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
	
	private JTextArea txtListaTokens;
	private JTextArea txtArchivoCodigo;
	
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
				lexico.nuevo();
				txtListaTokens.setText("");
				txtArchivoCodigo.setText("");
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
            		int resp = JOptionPane.showConfirmDialog(null, "No se ha cargado ningun archivo con un c\u00F3digo ¿Quiere cargar uno?", "Alerta!", JOptionPane.YES_NO_OPTION);
            		//resp == 0 --> Puso que SI
            		//resp == 1 --> Puso que NO
            		if (resp == 0)
            		{
            			cargarArchivo();
                    	mostrarToken();	
            		}
            	}
            	else
            	{
                	mostrarToken();	
            	}
            }
        });
		
		JLabel lblTituloCodigo = new JLabel("C\u00F3digo");
		lblTituloCodigo.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblTituloToken = new JLabel("Tokens");
		lblTituloToken.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		txtListaTokens = new JTextArea();
		txtListaTokens.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtListaTokens.setEditable(false);
		
		txtArchivoCodigo = new JTextArea();
		txtArchivoCodigo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtArchivoCodigo.setEditable(false);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTituloCodigo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtArchivoCodigo, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnCargarArchivo, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addComponent(btnSeguiente, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTituloToken, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtListaTokens, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTituloCodigo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTituloToken, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCargarArchivo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSeguiente, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtArchivoCodigo, GroupLayout.PREFERRED_SIZE, 591, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtListaTokens, GroupLayout.PREFERRED_SIZE, 591, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}

	private void cargarArchivo()
	{
		//ACA CARGAMOS EL ARCHIVO...
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
		
		//ACA LO MOSTRAMOS POR PANTALLA..
		try {
			BufferedReader leer = new BufferedReader(new FileReader(archivoACargar));
			String line = leer.readLine();
			while (line != null)
			{
				txtArchivoCodigo.append(line + "\n");
				line = leer.readLine();
			}
			leer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resaltarCodigo(List<String> lines)
	{
		txtArchivoCodigo.setText("");
		txtArchivoCodigo.setForeground(Color.RED);
		for (String loc : lines) {
			if(loc.equals("estas bien??"))
			{
				txtArchivoCodigo.append(loc + "\n");
			}

		}
	}
	
	private void mostrarToken()
	{
		String token;
		token = lexico.getToken();
		txtListaTokens.append(token + "\n");
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
