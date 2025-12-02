package br.ucs.poo.exerciteaki.file;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import br.ucs.poo.exerciteaki.utils.JsonTimeModule;

import br.ucs.poo.exerciteaki.entities.Academia;

public abstract class Storage {
    
	public static final String FILE_NAME = "academia.json";
    
	private static final ObjectMapper MAPPER = createMapper();
    
	private Storage() {}
    
	private static ObjectMapper createMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JsonTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}
    
	public static Academia carregarDados() {
		try {
			File f = new File(FILE_NAME);
			return MAPPER.readValue(f, Academia.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
	public static void salvarDados(Academia academia) {
		try {
			File f = new File(FILE_NAME);
			MAPPER.writeValue(f, academia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public static boolean arquivoExiste() {
		File f = new File(FILE_NAME);
		return f.exists();
	}
    
	public static boolean deletarArquivo() {
		File f = new File(FILE_NAME);
		if (f.exists()) {
			return f.delete();
		} 
		return false;
	}
    
}
