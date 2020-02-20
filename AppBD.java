package baseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppBD {

	public static void main(String[] args) throws ParseException {

		Connection conexion = null;
		conexion = AdminApp.obtenerConexion();

		try {

			Scanner scan = new Scanner(System.in);

			int opcionesMenu = 0;
			opcionesMenu = mostrarMenu(scan);

			while (opcionesMenu != 0) {
				switch (opcionesMenu) {

				case 1:
					listado(conexion);
					break;
				case 2:
					alta(conexion, scan);
					break;
				case 3:
					modificar(conexion, scan);
					break;
				case 4:
					baja(conexion, scan);
					break;

				case 5:
					buscar(conexion, scan);
					break;

				case 6:
					ventas(conexion, scan);
					break;

				}

				opcionesMenu = mostrarMenu(scan);
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void ventas(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

	}

	private static void listado(Connection conexion) {
		// TODO Auto-generated method stub

		System.out.println("Bienvenido al listado de personas");
		System.out.println();

		Statement stmt;

		try {

			conexion = AdminApp.obtenerConexion();
			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONA");

			while (rs.next()) {
				Date fechaNa = rs.getDate(4);

				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + fechaNa);
			}

			System.out.println("-----------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void alta(Connection conexion, Scanner scan) throws ParseException {
		// TODO Auto-generated method stub

		System.out.println("ALTA DE PERSONAS");
		System.out.println("----------------");
		System.out.println();

		Statement stmt;

		try {
			conexion = AdminApp.obtenerConexion();
			stmt = conexion.createStatement();

			System.out.println("Ingrese nombre");
			String nombre = scan.next();

			System.out.println("Ingrese edad");
			int edad = scan.nextInt();

			System.out.println("Ingrese fecha de naciemiento");
			String fechaNacimiento = scan.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = sdf.parse(fechaNacimiento);

			String insert = "INSERT INTO PERSONA (NOMBRE, EDAD, FECHA_NACIMIENTO) VALUES ('" + nombre + "', '" + edad
					+ "','" + fechaNacimiento + "')";
			stmt.executeUpdate(insert);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	private static void modificar(Connection conexion, Scanner scan) throws ParseException {
		// TODO Auto-generated method stub

		System.out.println("MODIFICACION DEL REGISTRO DE PERSONAS");
		System.out.println("-------------------------------------");
		System.out.println();

		System.out.println("Ingrese ID ");
		int id = scan.nextInt();

		Statement stmt;

		try {
			conexion = AdminApp.obtenerConexion();
			stmt = conexion.createStatement();

			ResultSet rs = stmt.executeQuery(" SELECT * FROM PERSONA WHERE IDPERSONA = " + id + " ; ");

			System.out.println("Seleccione la opcion que desea modificar");
			System.out.println("Opcion 1 : Nombre");
			System.out.println("Opcion 2 : Edad ");
			System.out.println("Opcion 3 : Fecha de Nacimiento ");
			int opcion = scan.nextInt();

			switch (opcion) {

			case 1:
				System.out.println("Ingrese nuevo nombre");
				String nombreNuevo = scan.next();
				String update = "UPDATE PERSONA SET NOMBRE = '" + nombreNuevo + "' WHERE IDPERSONA = " + id + " ";
				stmt.executeUpdate(update);
				System.out.println("El nombre ha sido modificado");
				break;

			case 2:
				System.out.println("Ingrese nueva edad");
				String edadNueva = scan.next();
				String update1 = "UPDATE PERSONA SET EDAD = '" + edadNueva + "' WHERE IDPERSONA =  " + id + " ";
				stmt.executeUpdate(update1);
				System.out.println("La edad ha sido modificada");
				break;

			case 3:
				System.out.println("Ingrese nueva fecha de nacimiento");
				String fechaNueva = scan.next();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fecha = sdf.parse(fechaNueva);

				String update2 = "UPDATE PERSONA SET FECHA_NACIMIENTO = '" + fechaNueva + "' WHERE IDPERSONA = " + id
						+ " ";
				stmt.executeUpdate(update2);
				System.out.println("La fecha ha sido modificada");
				break;

			default:
				break;
			}
			opcion = mostrarMenu(scan);

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void baja(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("BAJA DE PERSONAS");
		System.out.println("----------------");
		System.out.println();

		System.out.println("Ingrese ID");
		int id = scan.nextInt();

		Statement stmt;
		try {
			conexion = AdminApp.obtenerConexion();
			stmt = conexion.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONA WHERE IDPERSONA =" + id);
			rs.next();
			String delete = "DELETE FROM PERSONA WHERE IDPERSONA = " + id;
			stmt.executeUpdate(delete);
			System.out.println("El registro ha sido eliminado");

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int mostrarMenu(Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("BIENVENIDO AL SISTEMA DE REGISTRO DE PERSONAS");
		System.out.println("---------------------------------------------");
		System.out.println();

		System.out.println("1) Ver Listado");
		System.out.println("2) Dar de alta");
		System.out.println("3) Modificar usuario");
		System.out.println("4) Dar de baja");
		System.out.println("5) Buscar");
		System.out.println("0) Salir");

		int opMenu = 0;
		opMenu = scan.nextInt();
		return opMenu;

	}

	private static void buscar(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		
	}
}