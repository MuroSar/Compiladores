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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

import compilador.GenCodigo;
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
	private int ancho = 1050;
	private int alto = 800;
	//TAMAÑO DE PANTALLA..
	
	private Lexico lexico;
	private Parser parser;
	private Sintactico sintactico;
	private GenCodigo generador;
	private File archivoACargar;
	private boolean archivoCargado = false;
	
	private JTextArea txtListaTokens;
    private JTextPane tpArchivoCodigo;
    private JTextPane tpNumeracion;
	
	public Principal() {
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(comienzaX, comienzaY, ancho, alto);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagenes/logo.png"));
        setTitle("Compilador Marin-Sarti");
        this.lexico = new Lexico(this);
        this.parser = new Parser();
        this.parser.setLexico(lexico);
        this.generador = new GenCodigo();
        this.sintactico = new Sintactico(this, this.lexico, this.parser, this.generador);
        this.parser.setSintactico(this.sintactico);
        
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mnArchivo.add(mntmNuevo);
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
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
				System.exit(0);
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
		
		JButton btnGetToken = new JButton("Lexico");
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
		
		tpNumeracion = new JTextPane();
		tpNumeracion.setFont(new Font("Monospaced", Font.PLAIN, 13));
		tpNumeracion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tpNumeracion.setEditable(false);
		
		JScrollPane scrollNumeracion = new JScrollPane(tpNumeracion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollArchivoCodigo = new JScrollPane(tpArchivoCodigo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollListaTokens= new JScrollPane(txtListaTokens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JButton btnRun = new JButton("Parser");
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
							.addComponent(scrollNumeracion, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(scrollNumeracion)
						.addComponent(scrollArchivoCodigo))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}

	private void nuevo() {
		lexico.nuevoArchivo();
		txtListaTokens.setText("");
		tpArchivoCodigo.setText("");
		tpNumeracion.setText("");
		archivoCargado = false;
	}
	
	private void cargarArchivo()
	{
		if(this.archivoCargado)
		{
			this.nuevo();
		}
		
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
			int nroLinea = 1;			
			tpArchivoCodigo.setEditable(true);
			BufferedReader leer = new BufferedReader(new FileReader(archivoACargar));
			String line = leer.readLine();
			while (line != null)
			{
				tpNumeracion.setText(tpNumeracion.getText() + nroLinea + "->" + "\n");
				tpArchivoCodigo.setText(tpArchivoCodigo.getText() + line + "\n");
				line = leer.readLine();
				nroLinea++;
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
	

	public void mostrarToken()
	{
		String lexema = "";
		Token token = lexico.getToken();
		
		if(token.getKey() != 0)
		{
			lexema = token.getType() + ": " + token.getLexema();
		}
		else
		{
			lexema = "---------------- Fin del archivo ----------------";
			ArrayList<ErrorToken> errores = this.lexico.getErrores();
			for(ErrorToken error : errores)
			{
				lexema = lexema + "\n" + error.toString();
			}
			
			lexema = lexema + "\n";
			lexema = lexema + "--------------- Tabla de Simbolos ---------------";
			lexema = lexema + "\n";
			
			lexema = lexema + this.lexico.printTSimbolos();
		}
		
		txtListaTokens.append(lexema + "\n");
	}
	
	public void mostrarMensaje(String mensaje)
	{
		txtListaTokens.append(mensaje + "\n");
	}
	
	private void runParser()
	{
		this.txtListaTokens.setText("");
		this.lexico.empiezaDeNuevo();
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
