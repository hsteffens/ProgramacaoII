package br.com.editorTexto.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File {
	private Path path;
	
	public Path getPath() {
		return path;
	}

	public  void setPath(Path path) {
		this.path = path;
	}

	public Path criarArquivo(String arquvio) throws IOException{
		setPath(Paths.get(arquvio));
		java.io.File file = new java.io.File(arquvio);
		if (file.exists()) {
			return getPath();
		}
		return Files.createFile(getPath());
	}
	
	public static void deletarArquivo(String arquivo) throws IOException{
		Path path = Paths.get(arquivo);
		Files.delete(path);
	}
	
	public static void criaConteudoArquivo(Path arquivo,String conteudo) throws FileNotFoundException{
		FileOutputStream fileOutputStream = new FileOutputStream(arquivo.toFile());
		byte[] bytes = conteudo.getBytes();
		try {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void criptografarArquivo(Path arquivo, char codigoAscii) throws Exception{
		@SuppressWarnings("resource")
		FileInputStream fileInputStream = new FileInputStream(arquivo.toFile());
		List<Character> valoresAscii = new ArrayList<Character>();
		int conteudo;
		while (-1 != (conteudo = fileInputStream.read())) {
			valoresAscii.add((char) conteudo);
		}
		char[] caracteresCriptoGravados = new char[valoresAscii.size()];
		for (int i = 0; i < valoresAscii.size(); i++) {
			char novoCarater = valoresAscii.get(i);
			if (novoCarater == 106) {
				novoCarater = (char) (novoCarater + codigoAscii);
				if (novoCarater > 255) {
					throw new Exception("O carater base da criptografia resulta em um valor maior que a tabela ascii");
				}
			}
			caracteresCriptoGravados[i] = novoCarater;
		}
		String conteudoCriptografado = new String();
		for (char c : caracteresCriptoGravados) {
			conteudoCriptografado = conteudoCriptografado + c;
		}
		criaConteudoArquivo(arquivo, conteudoCriptografado);
	}
	
	public static void criarCopiaDescriptografada(Path arquivo, char codigoAscii) throws Exception{
		Path copy = Files.copy(arquivo, Paths.get(".\\teste - Copia.txt"));
		descriptogravar(copy,codigoAscii);
	}
	private static void descriptogravar(Path arquivo,char codigoAscii) throws Exception{
		@SuppressWarnings("resource")
		FileInputStream fileInputStream = new FileInputStream(arquivo.toFile());
		List<Character> valoresAscii = new ArrayList<Character>();
		int conteudo;
		while (-1 != (conteudo = fileInputStream.read())) {
			valoresAscii.add((char) conteudo);
		}
		char[] caracteresCriptoGravados = new char[valoresAscii.size()];
		for (int i = 0; i < valoresAscii.size(); i++) {
			char novoCarater = valoresAscii.get(i);
			if (novoCarater == 106 + codigoAscii) {
				novoCarater = (char) (novoCarater - codigoAscii);
				if (novoCarater < 0) {
					throw new Exception("O carater base da criptografia resulta em um valor maior que a tabela ascii");
				}
			}
			caracteresCriptoGravados[i] = novoCarater;
		}
		String conteudoCriptografado = new String();
		for (char c : caracteresCriptoGravados) {
			conteudoCriptografado = conteudoCriptografado + c;
		}
		criaConteudoArquivo(arquivo, conteudoCriptografado);
	}
}
