package com.losdevdepaco.p7project.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneradorTabla {
	
	private enum Direccion {
		ARRIBA {
			@Override
			public int avanceX(int x) {
				return x;
			}

			@Override
			public int avanceY(int y) {
				return y + 1;
			}
		},
		ARRIBA_DERECHA {
			@Override
			public int avanceX(int x) {
				return x + 1;
			}

			@Override
			public int avanceY(int y) {
				return y + 1;
			}
		},
		DERECHA {
			@Override
			public int avanceX(int x) {
				return x + 1;
			}

			@Override
			public int avanceY(int y) {
				return y;
			}
		},		
		DERECHA_ABAJO {
			@Override
			public int avanceX(int x) {
				return x + 1;
			}

			@Override
			public int avanceY(int y) {
				return y - 1;
			}
		},
 		ABAJO {
			@Override
			public int avanceX(int x) {
				return x;
			}

			@Override
			public int avanceY(int y) {
				return y - 1;
			}
		},	
 		IZQ_ABAJO {
			@Override
			public int avanceX(int x) {
				return x - 1;
			}

			@Override
			public int avanceY(int y) {
				return y - 1;
			}
		},		
		IZQ {
			@Override
			public int avanceX(int x) {
				return x - 1;
			}

			@Override
			public int avanceY(int y) {
				return y;
			}
		},		
		IZQ_ARRIBA {
			@Override
			public int avanceX(int x) {
				return x - 1;
			}

			@Override
			public int avanceY(int y) {
				return y - 1;
			}
		};
		
		public abstract int avanceX(int x);
		public abstract int avanceY(int y);
	}
	
	private static final int REINTENTOS = 10000000;
	
	private static Direccion direccionAleatoria() {
		int cant = Direccion.values().length;
		Random aleatorio = new Random();
		return Direccion.values()[aleatorio.nextInt(cant)];
	}
	
	private static int coordAleatoria(int max) {
		Random aleatorio = new Random();
		return aleatorio.nextInt(max);
	}
	
	public static char[][] crearTabla(int alto, int ancho, List<String> palabras){
		char[][] tabla = new char[alto][ancho];
		//repetir el proceso para cada una de las palabras
		for(String pal : palabras) {
			Direccion dir = null;
			int coordX = 0;
			int coordY = 0;
			//comprobar y reintentar n veces si se puede escribir en la tabla
			int reintentos = REINTENTOS;
			boolean sePuedeMeter = false;
			while(!sePuedeMeter && reintentos > 0) {
				dir = GeneradorTabla.direccionAleatoria();
				coordX = GeneradorTabla.coordAleatoria(ancho);
				coordY = GeneradorTabla.coordAleatoria(alto);
				sePuedeMeter = probarSiEntra(tabla, pal, dir, coordX, coordY, ancho, alto);
				if(sePuedeMeter) {
					System.out.println("La palabra " + pal 
							+ " entra en coords (x, y) " 
							+ coordY + "," 
							+ coordX 
							+ " con dir: "
							+ dir.toString());
				} else {
					System.out.println("FALLO: La palabra " + pal 
							+ " NO entra en coords (x, y) " 
							+ coordY + "," 
							+ coordX
							+ " con dir: "
							+ dir.toString());
				}
				reintentos--;
			}
			if(!sePuedeMeter) {
				System.out.println("Agotados los reintentos para poder introducir la palabra en el mapa:" + pal);
				return null;
			} else {
				//meter los chars en la tabla
				meterCharsTabla(tabla, pal, dir, coordX, coordY);
			}
		}
		//una vez metidas todas las palabras, rellenar con caracteres aleatorios
		tabla = rellenarHuecosAleatoriamente(tabla, alto, ancho);
		return tabla;
	}
	
	private static boolean probarSiEntra(char[][] tabla, String palabra, Direccion dir,
			int coordX, int coordY, int maxX, int maxY) {
		int x = coordX;
		int y = coordY;
		char[] pal = palabra.toCharArray();
		for(int i=0; i < pal.length; i++) {
			//series de comprobaciones hasta que alguna devuelva false
			// si pasa todas las condiciones, pasamos al siguiente caracter
			if(x >= maxX || y >= maxY || y < 0 || x < 0) {
				return false;
			}
			char cAhora = tabla[y][x];
			// comprobar que si no esta vacio y es distinto. Si es igual nos vale
			if(cAhora != 0 && cAhora != pal[i]) {
				return false;
			}
			x = dir.avanceX(x);
			y = dir.avanceY(y);
		}		
		return true;
	}
	
	private static char[][] meterCharsTabla(char[][] tabla, String palabra, Direccion dir,
			int coordX, int coordY){
		int x = coordX;
		int y = coordY;
		char[] pal = palabra.toCharArray();
		for(int i=0; i < pal.length; i++) {
			tabla[y][x] = Character.toUpperCase(pal[i]);
			x = dir.avanceX(x);
			y = dir.avanceY(y);
		}		
		return tabla;
	}
	
	private static char[][] rellenarHuecosAleatoriamente(char[][] tabla, int alto, int ancho){
		for(int i=0; i < ancho; i++) {
			for(int j=0; j < alto; j++) {
				if(tabla[j][i] == 0) {
					tabla[j][i] = Character.toUpperCase(caracterAleatorio());
				}
			}
		}		
		return tabla;
	}
	
	private static char caracterAleatorio() {
		Random r = new Random();
		return (char)(r.nextInt(26) + 'a');
	}
	
	public static void imprimirTabla(int alto, int ancho, char[][] tabla) {
		System.out.println("Imprimiendo tabla");
		for(int i=0; i < alto; i++) {
			for(int j=0; j < ancho; j++) {
				System.out.print('|');
				System.out.print(tabla[i][j]);
			}
			System.out.print('|');
			System.out.print('\n');
		}	
	}
	//Para hacer pruebas de si funciona el generador
	public static void main (String[] args) {
		List<String> palabras = Arrays.asList("aristocracia",
											  "burguesia",
											  "clorofila",
											  "comercializacion",
											  "desarrollador",
											  "hambruna");
		char[][] tabla = GeneradorTabla.crearTabla(15, 20, palabras);
		System.out.println("Tabla generada");
		GeneradorTabla.imprimirTabla(15, 20, tabla);
	}
}
