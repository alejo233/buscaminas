/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de soluci�n laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package modelo;


public class Buscaminas {


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) {
		this.nivel = nivel;
		perdio = false;
		inicializarPartida();

	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {
		switch(nivel) {
		case 1:
			
			casillas = new Casilla[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];
			for (int i = 0; i < casillas.length; i++) {
				for (int j = 0; j < casillas[i].length; j++) {
					Casilla casilla = new Casilla(Casilla.LIBRE);
					casillas[i][j] = casilla;
				}
			}

			break;
			
		case 2:
		
			casillas= new Casilla[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];
			for (int i = 0; i < casillas.length; i++) {
				for (int j = 0; j < casillas[i].length; j++) {
					Casilla casilla = new Casilla(Casilla.LIBRE);
					casillas[i][j] = casilla;
				
				}
			}
			break;
			
		case 3:
		
			casillas= new Casilla[FILAS_EXPERTO][COLUMNAS_EXPERTO];
			for (int i = 0; i < casillas.length; i++) {
				for (int j = 0; j <casillas[i].length; j++) {
					Casilla casilla = new Casilla(Casilla.LIBRE);
					casillas[i][j] = casilla;
					
				}
			}
			break;
		}
		

	}


	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {

		// TODO

	}


	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una casillas
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int i, int j) {		
		int numeroMinas=0;
		if (!casillas[i][j].esMina()) {
			for (int fila=i-1; fila <= i+1; i++) {
				if(fila>=0 && fila<i) {
				for (int columna=j-1; columna <= j+1; j++) {
					if(columna>=0&&columna<j) {
						if (casillas[i][j].esMina()) {
						numeroMinas++;
						}
					}
				}
				}
			}
		}else {
			numeroMinas=-1;
		}
		
		return numeroMinas;
	}

	/**
	 * M�todo que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {

		switch(nivel) {
		case 1:
			int m = 0;
			while (m < CANTIDAD_MINAS_PRINCIPANTE) {
				int fila = (int) ((Math.random() * FILAS_PRINCIPIANTE-1) + 1);
				int columna = (int) ((Math.random() * FILAS_PRINCIPIANTE-1) + 1);
				if (!casillas[fila][columna].esMina()) {
					casillas[fila][columna].modificarMina(true);
					m++;
				}
			}
			break;
			
		case 2:
				int n = 0;
			while (n < 	CANTIDAD_MINAS_INTERMEDIO) {
				int fila = (int) ((Math.random() * FILAS_INTERMEDIO-1) + 1);
				int columna = (int) ((Math.random() * COLUMNAS_INTERMEDIO-1) + 1);
				if (!casillas[fila][columna].esMina()) {
					casillas[fila][columna].modificarMina(true);
					n++;
				}
			}
			break;
			
		case 3:
			int o = 0;
			while (o < CANTIDAD_MINAS_EXPERTO) {
				int fila = (int) ((Math.random() * FILAS_EXPERTO-1) + 1);
				int columna = (int) ((Math.random() * COLUMNAS_EXPERTO-1) + 1);
				if (!casillas[fila][columna].esMina()) {
					casillas[fila][columna].modificarMina(true);
					o++;
				}
			}
			break;
		}
		
	}


	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en pantalla
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {
		String casilla = "";
		
		for (int i = 0; i < casillas[0].length; i++) {
			
		if(i<9) {
			casilla +="   "+(i+1);
		}	else {
			casilla +="  "+(i+1);

		}
			
			
		}    
		casilla += "\n";	
		for (int i = 0; i < casillas.length; i++) {
			if(i<9) {
			casilla +=i+1+" ";
			}else {
				casilla +=i+1+"";

			}
			for (int j = 0; j < casillas[i].length; j++) {
				
				
			casilla +=" "+casillas[i][j].mostrarValorCasilla()+ "  ";
			}
			casilla += "\n";
		}  

		return casilla;
	}


	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver()   {

		
				

	}
		

	/**
	 * Metodo dar del atributo casillas
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas(){
		return casillas;
	}


	/**
	 * Este metodo se encargaa de abrir una casilla
	 * Si se abre una casilla de tipo Mina, se marca que el jugador perdio el juego.
	 * @param i - la fila donde esta la casilla 
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int i, int j) {
		// TODO
		return true;
	}


	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
		// TODO
		return true;
	}


	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo valor sea Mayor que 0
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber casillas posibles para dar una pista, retorna el mensaje no hay pistas para dar
	 */
	public String darPista() {

		// TODO
		return null;
	}
	
	/***
	 * Metodo dar del atributo perdio
	 * @return boolean el atributo
	 */
	public boolean darPerdio(){
		return perdio;
	}

}