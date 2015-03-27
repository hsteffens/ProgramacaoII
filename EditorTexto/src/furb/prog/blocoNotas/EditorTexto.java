package furb.prog.blocoNotas;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 * 
 * @author Helinton e Diogo
 * @date 22/03/2015
 *
 */
public class EditorTexto extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenuItem subMenuNovo;
	private JMenuItem subMenuAbrir;
	private JMenuItem subMenuSalvar;
	private JMenuItem subMenuSalvarComo;
	private JMenuItem subMenuSobre;
	private JMenuItem subMenuFechar;
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JMenuItem getSubMenuNovo() {
		return subMenuNovo;
	}

	public void setSubMenuNovo(JMenuItem subMenuNovo) {
		this.subMenuNovo = subMenuNovo;
	}

	public JMenuItem getSubMenuAbrir() {
		return subMenuAbrir;
	}

	public void setSubMenuAbrir(JMenuItem subMenuAbrir) {
		this.subMenuAbrir = subMenuAbrir;
	}

	public JMenuItem getSubMenuSalvar() {
		return subMenuSalvar;
	}

	public void setSubMenuSalvar(JMenuItem subMenuSalvar) {
		this.subMenuSalvar = subMenuSalvar;
	}

	public JMenuItem getSubMenuSalvarComo() {
		return subMenuSalvarComo;
	}

	public void setSubMenuSalvarComo(JMenuItem subMenuSalvarComo) {
		this.subMenuSalvarComo = subMenuSalvarComo;
	}

	public JMenuItem getSubMenuSobre() {
		return subMenuSobre;
	}

	public void setSubMenuSobre(JMenuItem subMenuSobre) {
		this.subMenuSobre = subMenuSobre;
	}

	public JMenuItem getSubMenuFechar() {
		return subMenuFechar;
	}

	public void setSubMenuFechar(JMenuItem subMenuFechar) {
		this.subMenuFechar = subMenuFechar;
	}

	public JMenu getMenuArquivo() {
		return menuArquivo;
	}
	
	public void setMenuArquivo(JMenu menuArquivo) {
		this.menuArquivo = menuArquivo;
	}
	
	public JMenu getMenuEditar() {
		return menuEditar;
	}
	
	public void setMenuEditar(JMenu menuEditar) {
		this.menuEditar = menuEditar;
	}
}
