package producto;

import java.io.*;

public class ControlPac {
	
	private static final String menuPrincipal = "   .............................................." + "\n" + 
												"   :-:        C E N T R O  M E D I C O        :-:" + "\n" + 
												"   :-:   >>>>     U N L A M   <<<<            :-:" + "\n" + 
												"   :-:  C O N T R O L  D E  P A C I E N T E S :-:" + "\n" + 
												"   :-:........................................:-:" + "\n" + 
												"   :-: 1.  Ingreso de datos                   :-:" + "\n" +
												"   :-: 2.  Informes                           :-:" + "\n" + 
												"   :-: 3.  Salir                              :-:" + "\n" +
												"   .............................................." + "\n" + 
												"   ....Elija la opcion deseada: " ;

	private static final String menuIngresoDeDatos = "   ..............................................."+"\n" +
													 "   :-: -I N G R E S O  D E  P A C I E N T E S- :-:"+"\n" +
													 "   :-:.........................................:-:"+"\n" +
													 "   :-: 1.  Datos del paciente                  :-:"+"\n" +
													 "   :-: 2.  Situacion del paciente              :-:"+"\n" +
													 "   :-: 3.  Datos del medico                    :-:"+"\n" +
													 "   :-: 4.  Anterior                            :-:"+"\n" +
													 "   ..............................................."+"\n" +
													 "   ....Elija la opcion deseada: " ;
	
	private static final String menuInformes = "   ..............................................."+"\n" +
											   "   :-:  C O N T R O L  D E  P A C I E N T E S  :-:"+"\n" +
											   "   ..............................................."+"\n" +
											   "   :-:           - I N F O R M E S -           :-:"+"\n" +
											   "   :-:.........................................:-:"+"\n" +
											   "   :-: 1. Listado de pacientes por medico      :-:"+"\n" +
											   "   :-: 2. Enfermedades que atiende cada medico :-:"+"\n" +
											   "   :-: 3. Anterior                             :-:"+"\n" +
											   "   ..............................................."+"\n" +
											   "   ....Elija la opcion deseada: " ;
	
	private static final String cabeceraMedico = 	"   ....................................................."+"\n" +
													"   :-:      - D A T O S   D E L   M E D I C O -      :-:"+"\n" +
													"   :-:...............................................:-:"+"\n" ;
			
	private static final String cabeceraSituacionPaciente =  "   ....................................................."+"\n" +
															 "   :-: - S I T U A C I O N  D E L  P A C I E N T E - :-:"+"\n" +
															 "   :-:...............................................:-:"+"\n" ;												
	
	private static final String cabeceraPaciente =  "   ..............................................."+"\n" +
  	 							   					"   :-:  - D A T O S  D E L  P A C I E N T E -  :-:"+"\n" +
  	 							   					"   :-:.........................................:-:"+"\n" ;
	
	private static final String rutaArchivoPacientes = "D:\\datopac.txt";
	private static final String rutaArchivoSituacionPacientes = "D:\\situpac.txt";
	private static final String rutaArchivoMedicos = "D:\\datomed.txt";
	
	public static void main(String args[]) {			
		mostrarMenuPrincipal();		
	}		
     	
    
	private static void mostrarMenuPrincipal() {

		int opcionIngresada = 0;

		do {
			
			System.out.println(menuPrincipal);
			
			opcionIngresada = leerEntero();

			switch (opcionIngresada) {
			case 1:
				mostrarMenuIngresoDeDatos();
				break;
			case 2:
				mostrarMenuDeInformes();
				break;
			case 3:
				break;
			default:
				System.out.println("Ingrese una opcion correcta");
			}
			
		} while (opcionIngresada != 3);

	}
    
	
	private static void mostrarMenuIngresoDeDatos() {

		int opcionIngresada = 0;

		do {

			System.out.println(menuIngresoDeDatos);

			opcionIngresada = leerEntero();

			switch (opcionIngresada) {
			case 1:
				ingresarPaciente();
				break;
			case 2:
				ingresarSituacionDelPaciente();
				break;
			case 3:
				ingresarMedico();
				break;
			case 4:
				break;
			default:
				System.out.println("Ingrese una opcion correcta");
			}

		} while (opcionIngresada != 4);

	}
	
	
	private static void mostrarMenuDeInformes() {

		int opcionIngresada = 0;

		do {

			System.out.println(menuInformes);

			opcionIngresada = leerEntero();

			switch (opcionIngresada) {
			case 1:
				mostrarPacientesPorMedico();
				break;
			case 2:
				mostrarEnfermedadesPorMedico();
				break;
			case 3:
				break;
			default:
				System.out.println("Ingrese una opcion correcta");
			}

		} while (opcionIngresada != 3);

	}
	
	
	private static void ingresarPaciente() {

		String codpac, nompac, op;
		DataOutputStream datopac = null;
		
		try {
			datopac = new DataOutputStream(new FileOutputStream(rutaArchivoPacientes));
			do {
				System.out.println(cabeceraPaciente);
				
				System.out.println("Digite el codigo del paciente: ");
				codpac = leerCadena();
				datopac.writeUTF(codpac);
				
				System.out.println("Digite el nombre del paciente: ");
				nompac = leerCadena();
				datopac.writeUTF(nompac);

				System.out.println("Desea ingresar otro paciente? S/N" + "\n");
				op = leerCadena();

			} while (op.equals("S") || op.equals("s"));
			
			datopac.close();

		} catch (IOException e) {}

	}


	private static void ingresarSituacionDelPaciente() {

		String codp, codm, enfpac, op;
		DataOutputStream situpac = null;		

		try {
			situpac = new DataOutputStream(new FileOutputStream(rutaArchivoSituacionPacientes));
			do {
				System.out.println(cabeceraSituacionPaciente);

				System.out.println("Digite el codigo del paciente: ");
				codp = leerCadena();
				situpac.writeUTF(codp);
				
				System.out.println("Digite el codigo del medico: ");
				codm = leerCadena();
				situpac.writeUTF(codm);
				
				System.out.println("Digite el diagnostico del medico: ");
				enfpac = leerCadena();
				situpac.writeUTF(enfpac);

				System.out.println("Desea ingresar otro registro al historial? S/N\n");
				op = leerCadena();

			} while (op.equals("S") || op.equals("s"));
			
			situpac.close();
			
		} catch (IOException e) {}

	}


    
	private static void ingresarMedico() {

		String codmed, espmed, nommed, op;
		DataOutputStream datomed = null;
		
		try {
			datomed = new DataOutputStream(new FileOutputStream(rutaArchivoMedicos));
			do {
				System.out.println(cabeceraMedico);

				System.out.println("Digite el codigo del medico: ");
				codmed = leerCadena();
				datomed.writeUTF(codmed);

				System.out.println("Digite el nombre del medico: ");
				nommed = leerCadena();
				datomed.writeUTF(nommed);

				System.out.println("Digite la especializacion del medico: ");
				espmed = leerCadena();
				datomed.writeUTF(espmed);

				System.out.println("Desea ingresar otro medico? S/N\n");
				op = leerCadena();

			} while (op.equals("S") || op.equals("s"));
			
			datomed.close();
			
		} catch (IOException e) {}		

	}	
	
    
	@SuppressWarnings("unused")
	private static void mostrarEnfermedadesPorMedico() {

		String codtem, codm, nomm, espm, codp, codme, enfp;
		boolean finArchMed = false, finArchSitPac = false;
		DataInputStream datomed = null;
		DataInputStream situpac = null;	
		
		try {
			datomed = new DataInputStream(new FileInputStream(rutaArchivoMedicos));
			situpac = new DataInputStream(new FileInputStream(rutaArchivoSituacionPacientes));
		} catch (FileNotFoundException e) {
			System.out.println("Error: archivo no encontrado");
		}

		System.out.println("Digite el codigo del medico que desea consultar: ");
		codtem = leerCadena();
		while (!finArchMed) {
			try {
				codm = datomed.readUTF();
				nomm = datomed.readUTF();
				espm = datomed.readUTF();

				if (codm.equals(codtem)) { // compara el codigo digitado con el codigo del medico de la tabla "datomed"
					System.out.println("El medico " + nomm + " trata las siguientes enfermedades:" + "\n");	
					while (!finArchSitPac) {
						try {
							codp = situpac.readUTF();
							codme = situpac.readUTF();
							enfp = situpac.readUTF();

							if (codtem.equals(codme)) { // compara el codigo del medico de la tabla "datomed" con el codigo del medico en la tabla "situpac"
								System.out.println(">>>> " + enfp + "\n");
							}
						} catch (IOException e) {
							finArchSitPac = true;
						}
					}
				}
			} catch (IOException e) {
				finArchMed = true;
			}
		}		
		try {
			datomed.close();
			situpac.close();
		} catch (IOException e) {}
	}
	
	
	@SuppressWarnings("unused")
	private static void mostrarPacientesPorMedico() {

		String codm, nomm, espm, codtem, codp, codme, enfp, codpa, nompa;
		boolean finArchMed = false, finArchSitPac = false, finArchPac = false;
		DataInputStream datomed = null;
		DataInputStream situpac = null;
		DataInputStream datopac = null;

		try {
			datomed = new DataInputStream(new FileInputStream(rutaArchivoMedicos));
			situpac = new DataInputStream(new FileInputStream(rutaArchivoSituacionPacientes));
			datopac = new DataInputStream(new FileInputStream(rutaArchivoPacientes));
		} catch (FileNotFoundException e) {
			System.out.println("Error: archivo no encontrado");
		}

		
		System.out.println("Digite el codigo del medico que desea consultar: ");
		codtem = leerCadena();
		while (!finArchMed) {
			try {
				codm = datomed.readUTF();
				nomm = datomed.readUTF();
				espm = datomed.readUTF();

				if (codm.equals(codtem)) { // compara el codigo extraido de la tabla "datomed" con el codigo digitado
					
					System.out.println("::: El medico " + nomm + " atiende a los siguientes pacientes: " + "\n");
					while (!finArchSitPac) {
						try {
							codp = situpac.readUTF();
							codme = situpac.readUTF();
							enfp = situpac.readUTF();
							if (codme.equals(codtem)) { // compara el codigo medico de la tabla "datomed" con el de la tabla "situpac"
								while (!finArchPac) {
									try {
										codpa = datopac.readUTF();
										nompa = datopac.readUTF();
										if (codpa.equals(codp)) { // compara el codigo del paciente de la tabla "situpac" con el codigo del paciente de la tabla "datopac"
											System.out.println("::: Paciente: " + nompa + "\n");
										}
									} catch (IOException e) {
										finArchPac = true;
									}
								}
							}
						} catch (IOException e) {
							finArchSitPac = true;
						}
					}
				}
			}
			catch (IOException e) {
				finArchMed = true;
			}
		}
		try{
			datomed.close();
			situpac.close();
			datopac.close();
		} catch(IOException e){}
	}
	
	
	private static int leerEntero() {		
		String linea = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ne = 0;
		
		try {			
			linea = br.readLine();
		} catch (IOException e) {}
		
		try {
			ne = Integer.parseInt(linea);
		} catch (NumberFormatException e) {
			System.out.println("Ingrese un numero entero por favor");
		}
		
		return ne;
	}
	
    
	private static String leerCadena() {
		String linea = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {			
			linea = br.readLine();
		} catch (IOException e) {}
		
		return (linea);
	}		    
} 
