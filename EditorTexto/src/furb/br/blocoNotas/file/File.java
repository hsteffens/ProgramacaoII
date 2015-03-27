package furb.br.blocoNotas.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Helinton e Diogo
 * @date 22/03/2015
 *
 */
public class File {
	private Path path;
	
	public Path getPath() {
		return path;
	}

	public  void setPath(Path path) {
		this.path = path;
	}

	/**
	 * Cria o arquivo, com base no caminho passado como parametro, 
	 * caso já exista o arquivo retorna a instancia existente, se não cria uma nova e a devolve.
	 * 
	 * @param arquvio
	 * @return
	 * @throws IOException
	 */
	public Path criarArquivo(String arquvio) throws IOException{
		//Armazena a informação de qual arquivo está sendo editado
		setPath(Paths.get(arquvio));
		java.io.File file = new java.io.File(arquvio);
		if (file.exists()) {
			return getPath();
		}
		return Files.createFile(getPath());
	}
	
	/**
	 * Deleta o arquivo criado com base no caminho passado por paremetro
	 * 
	 * @param arquivo
	 * @throws IOException
	 */
	public void deletarArquivo(String arquivo) throws IOException{
		Path path = Paths.get(arquivo);
		Files.delete(path);
		java.io.File file = new java.io.File(arquivo);
		if (!file.exists()) {
			setPath(null);
		}
	}
	
	/**
	 * Cria o conteudo do arquivo
	 * 
	 * @param arquivo
	 * @param conteudo
	 * @throws IOException 
	 */
	public static void criaConteudoArquivo(Path arquivo,String conteudo) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(arquivo.toFile());
		byte[] bytes = conteudo.getBytes();
		try {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			fileOutputStream.close();
		}
	}
}
