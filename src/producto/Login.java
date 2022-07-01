package producto;

public class Login {

	private String usuario = "admin";
	private String pass = "12345";

	/*
	 * Valida el usuario y contraseña
	 */
	public boolean validarLogin(String user, String password) {
		if (usuario.equals(user) && pass.equals(password))
			return true;
		return false;
	}
}
