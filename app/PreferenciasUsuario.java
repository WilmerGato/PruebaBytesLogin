import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasUsuario {
    private static final String PREFERENCIAS_NOMBRE = "MisPreferencias";
    private static final String CLAVE_NOMBRE_USUARIO = "nombreUsuario";
    private static final String CLAVE_PASS = "pass";

    private SharedPreferences sharedPreferences;

    public PreferenciasUsuario(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCIAS_NOMBRE, Context.MODE_PRIVATE);
    }

    public void guardarUsuario(String nombreUsuario, String pass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CLAVE_NOMBRE_USUARIO, nombreUsuario);
        editor.putString(CLAVE_PASS, pass);
        editor.apply();
    }

    public String obtenerNombreUsuario() {
        return sharedPreferences.getString(CLAVE_NOMBRE_USUARIO, "");
    }

    public String obtenerPass() {
        return sharedPreferences.getString(CLAVE_PASS, "");
    }
}
