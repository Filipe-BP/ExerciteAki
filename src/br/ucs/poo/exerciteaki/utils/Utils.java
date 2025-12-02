package br.ucs.poo.exerciteaki.utils;

import java.util.Collection;

public class Utils {
	
	private Utils() {}
	
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static boolean isNotNull(Object o) {
		return o != null;
	}

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			return ((String) o).isBlank();
		}
		if (o instanceof Collection) {
            return ((Collection<?>) o).isEmpty();
        }
		return false;
	}
	
	public static boolean isNotEmpty(Object o) {
		return !Utils.isEmpty(o);
	}
	
	public static boolean equalsIgnoreCase(String s, String s2) {
		if (Utils.isEmpty(s) || Utils.isEmpty(s2)) return false;
		return s.equalsIgnoreCase(s2);
	}
}
