package br.ucs.poo.exerciteaki;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

abstract class Storage {
	
	public static final String FILE_NAME = "academia.json";
	
	public static Academia carregarDados() {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	File f = new File(FILE_NAME);
	        return mapper.readValue(f, Academia.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void salvarDados(Academia academia) {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	File f = new File(FILE_NAME);
	        mapper.writeValue(f, academia);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean arquivoExiste() {
		File f = new File(FILE_NAME);
		return f.exists();
	}
	
}
