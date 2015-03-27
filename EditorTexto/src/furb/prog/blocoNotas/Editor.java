package furb.prog.blocoNotas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import furb.br.blocoNotas.file.File;

/**
 * 
 * @author Helinton e Diogo
 * @date 22/03/2015
 *
 */
public class Editor extends EditorTexto implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static String MENU_ARQUIVO = "Arquivo";
	private static String MENU_EDITAR = "Editar";
	private static String SUB_MENU_NOVO = "Novo";
	private static String SUB_MENU_ABRIR = "Abrir";
	private static String SUB_MENU_SALVAR = "Salvar";
	private static String SUB_MENU_SALVAR_COMO = "Salvar Como";
	private static String SUB_MENU_FECHAR = "Fechar";
	private static String SUB_MENU_SOBRE = "Sobre";
	private File arquivo = new File();
	private boolean isEditado;

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	public boolean isEditado() {
		return isEditado;
	}

	public void setEditado(boolean isEditado) {
		this.isEditado = isEditado;
	}

	public Editor() {
		setEditado(false);
		setArquivo(new File());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("Bloco de notas");        

		carregarInfoTextArea();

		carregarInfoMenu();
	}

	/**
	 * Cria e defini comportamentos da textArea do bloco de notas
	 */
	private void carregarInfoTextArea() {
		setTextArea(new JTextArea());
		getTextArea().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {
				char keyCode = event.getKeyChar();
				//Valida se o codigo ascii se encotra entre os valores validos
				if (keyCode > 32 && keyCode < 127 ) {
					setEditado(true);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		add(getTextArea());
	}

	/**
	 * Carrega todas as informações e ações referentes ao bloco de notas
	 */
	private void carregarInfoMenu() {
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);

		setMenuArquivo(new JMenu(MENU_ARQUIVO));
		setMenuEditar(new JMenu(MENU_EDITAR));
		menu.add(getMenuArquivo());
		menu.add(getMenuEditar());
		getMenuArquivo().setMnemonic(KeyEvent.VK_A);
		getMenuEditar().setMnemonic(KeyEvent.VK_E);

		acoesBotoes();

		getMenuArquivo().add(getSubMenuNovo());
		getMenuArquivo().add(getSubMenuAbrir());
		getMenuArquivo().add(getSubMenuSalvar());
		getMenuArquivo().add(getSubMenuSalvarComo());
		getMenuArquivo().add(getSubMenuFechar());
		getMenuEditar().add(getSubMenuSobre());
		
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
	}

	public void acoesBotoes(){
		setSubMenuNovo(new JMenuItem(SUB_MENU_NOVO));
		getSubMenuNovo().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

		setSubMenuAbrir(new JMenuItem(SUB_MENU_ABRIR));
		getSubMenuAbrir().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

		setSubMenuSalvar(new JMenuItem(SUB_MENU_SALVAR));
		getSubMenuSalvar().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

		setSubMenuSalvarComo(new JMenuItem(SUB_MENU_SALVAR_COMO));
		getSubMenuSalvarComo().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+KeyEvent.SHIFT_MASK));

		setSubMenuFechar(new JMenuItem(SUB_MENU_FECHAR));
		getSubMenuFechar().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

		setSubMenuSobre(new JMenuItem(SUB_MENU_SOBRE));
		getSubMenuSobre().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));

		getSubMenuNovo().addActionListener(this);
		getSubMenuAbrir().addActionListener(this);
		getSubMenuSalvar().addActionListener(this);
		getSubMenuSalvarComo().addActionListener(this);
		getSubMenuFechar().addActionListener(this);
		getSubMenuSobre().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == getSubMenuNovo()){
				novo();

			} else if (evento.getSource() == getSubMenuAbrir()){
				abrir();

			} else if (evento.getSource() == getSubMenuSalvar()){
				salvar();
				
			} else if (evento.getSource() == getSubMenuSalvarComo()){
				salvarComo();

			} else if (evento.getSource() == getSubMenuFechar()){
				fechar();

			} else if (evento.getSource() == getSubMenuSobre()){
				 sobre();
	   
			}
	}

	private void sobre() {
		JOptionPane.showMessageDialog(this, "Trabalho de Programação \n\n Curso de Ciências da Computação \n\n Professor: Matheus Carvalho Viana\n"
		            + " Alunos: Hélinton Pereira Steffens e Diogo Lehner");
	}

	private void fechar() {
		if (isEditado()) {
			int option = JOptionPane.showConfirmDialog(this, "Deseja salvar as alterações?","Bloco de notas", JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == option) {
				salvar();
				limpar();
			}
			if (JOptionPane.NO_OPTION == option) {
				System.exit(0);
			}
		}else{
			System.exit(0);
		}
		
	}

	private void abrir() {
		if (isEditado()) {
			int option = JOptionPane.showConfirmDialog(this, "Deseja salvar as alterações?","Bloco de notas", JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == option) {
				salvar();
				abri();
			}
			if (JOptionPane.NO_OPTION == option) {
				abri();
			}
		}else{
			abri();
		}
		
	}

	public void salvar(){
		if (getArquivo().getPath() != null) {
			try {
				File.criaConteudoArquivo(getArquivo().getPath(), getTextArea().getText());
				setEditado(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jFileChooser.showSaveDialog(this);
			java.io.File file = jFileChooser.getSelectedFile();
			try {
				if (file != null) {
					getArquivo().criarArquivo(Paths.get(file.getAbsolutePath()).toString());
					File.criaConteudoArquivo(getArquivo().getPath(), getTextArea().getText());
					setEditado(false);
				}
			} catch (Exception ioe){
				JOptionPane.showMessageDialog(this, ioe);
			}
		}
	}
	
	public void salvarComo(){
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jFileChooser.showSaveDialog(this);
			java.io.File file = jFileChooser.getSelectedFile();
			try {
				getArquivo().criarArquivo(Paths.get(file.getAbsolutePath()).toString());
				File.criaConteudoArquivo(getArquivo().getPath(), getTextArea().getText());
			} catch (Exception ioe){
				JOptionPane.showMessageDialog(this, ioe);
			}
			setEditado(false);
	}
	public void novo(){
		if (isEditado()) {
			int option = JOptionPane.showConfirmDialog(this, "Deseja salvar as alterações?","Bloco de notas", JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == option) {
				salvar();
				limpar();
			}
			if (JOptionPane.NO_OPTION == option) {
				limpar();
			}
		}else{
			limpar();
		}
		setEditado(false);
	}

	private void limpar() {
		setEditado(false);
		setArquivo(new File());
		getTextArea().setText("");
	}
	
	public void abri(){
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jFileChooser.showOpenDialog(this);
		java.io.File file = jFileChooser.getSelectedFile();
		try {
			if (file != null) {
				getArquivo().setPath(Paths.get(file.getAbsolutePath()));
				String retorno = new String(Files.readAllBytes(getArquivo().getPath()));
				getTextArea().setText(retorno);
				setEditado(false);
			}
		} catch (Exception ioe){
			JOptionPane.showMessageDialog(this, ioe);
		}
	}
}