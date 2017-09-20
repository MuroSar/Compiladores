package interfaz;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import compilador.Lexico;
import compilador.Parser;
import compilador.Sintactico;
import complementos.ErrorToken;
import complementos.Token;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class Principal extends JFrame {
	
	//TAMAÑO DE PANTALLA..
	private int comienzaX = 0;
	private int comienzaY = 0;
	private int ancho = 1000;
	private int alto = 800;
	//TAMAÑO DE PANTALLA..
	
	private Lexico lexico;
	private Parser parser;
	private Sintactico sintactico;
	private File archivoACargar;
	private boolean archivoCargado = false;
	
	private JTextArea txtListaTokens;
    private JTextPane tpArchivoCodigo;
	
	public Principal() {
		setResizable(false);
		setBounds(comienzaX, comienzaY, ancho, alto);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/logo.png"));
        setTitle("Compilador Marin-Sarti");
        lexico = new Lexico(this);
        parser = new Parser();
        parser.setLexico(lexico);
        this.sintactico = new Sintactico(this, this.lexico, this.parser);
        parser.setSintactico(this.sintactico);
		
		
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
				tpArchivoCodigo.setText("");
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
		
		JButton btnGetToken = new JButton("getToken()");
		btnGetToken.addActionListener(new ActionListener() {
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
		
		tpArchivoCodigo = new JTextPane();
		tpArchivoCodigo.setFont(new Font("Monospaced", Font.PLAIN, 13));
		tpArchivoCodigo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tpArchivoCodigo.setEditable(false);
		
		JScrollPane scrollArchivoCodigo = new JScrollPane(tpArchivoCodigo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollListaTokens= new JScrollPane(txtListaTokens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JButton btnRun = new JButton("run()");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!archivoCargado)
            	{
            		int resp = JOptionPane.showConfirmDialog(null, "No se ha cargado ningun archivo con un c\u00F3digo ¿Quiere cargar uno?", "Alerta!", JOptionPane.YES_NO_OPTION);
            		//resp == 0 --> Puso que SI
            		//resp == 1 --> Puso que NO
            		if (resp == 0)
            		{
            			cargarArchivo();
                    	runParser();
            		}
            	}
            	else
            	{
            		runParser();	
            	}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTituloCodigo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollArchivoCodigo, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnCargarArchivo, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addComponent(btnGetToken, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTituloToken, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollListaTokens, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCargarArchivo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGetToken, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollListaTokens, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
						.addComponent(scrollArchivoCodigo))
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
		try 
		{
			tpArchivoCodigo.setEditable(true);
			BufferedReader leer = new BufferedReader(new FileReader(archivoACargar));
			String line = leer.readLine();
			while (line != null)
			{
				tpArchivoCodigo.setText(tpArchivoCodigo.getText() + line + "\n");
				line = leer.readLine();
			}
			leer.close();
			tpArchivoCodigo.setEditable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resaltarCodigo(List<String> lines, int fila, int posInicial, int pos)
	{
		String previo = "";
		String actual = "";
		String resto = "";
		tpArchivoCodigo.setText("");
		for (int loc=0; loc<lines.size(); loc++) 
		{
			if(loc < fila)
			{
				previo = previo + lines.get(loc) + "\n";
			}
			if(loc == fila)
			{
				int col = 0;
				String linea = lines.get(loc);
				String aux = "";
				while(col < posInicial)
				{
					aux += linea.charAt(col);
					col++;
				}
				previo = previo + aux;
				if(col == posInicial)
				{
					actual = lines.get(loc).substring(posInicial, pos);
				}
				resto = lines.get(loc).substring(pos) + "\n";
			}
			if(loc > fila)
			{
				resto = resto + lines.get(loc) + "\n";
			}
		}

		tpArchivoCodigo.setEditable(true);
		
		appendToPane(tpArchivoCodigo, previo, Color.BLUE);
		appendToPane(tpArchivoCodigo, actual, Color.RED);
		appendToPane(tpArchivoCodigo, resto, Color.BLACK);
		
		tpArchivoCodigo.setEditable(false);
	}
	
	public void appendToPane(JTextPane tpArchivoCodigo, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        int len = tpArchivoCodigo.getDocument().getLength();
        tpArchivoCodigo.setCaretPosition(len);
        tpArchivoCodigo.setCharacterAttributes(aset, false);
        tpArchivoCodigo.replaceSelection(msg);
    }
	

	private void mostrarToken()
	{
		String lexema = new String();
		Token token = lexico.getToken();
		
		if(token.getKey() != 0)
		{
			int key = token.getKey();
			lexema = lexico.getType(key) + ": " + lexico.getLexema(key);
		}
		else
		{
			lexema = "----------------- Fin del archivo -----------------";
			ArrayList<ErrorToken> errores = this.lexico.getErrores();
			for(ErrorToken error : errores)
			{
				lexema = lexema + "\n" + error.toString();
			}
		}
		
		txtListaTokens.append(lexema + "\n");
	}
	
	public void mostrarErrorSintactico(String error)
	{
		txtListaTokens.append(error + "\n");
	}
	
	private void runParser()
	{
		this.sintactico.start();
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
                ppal.setVisible(true);
            }
        });
    }
}
