package fr.eni.android.ch10_viewpagerexemple;

public class Informacion {
    private static final Informacion ourInstance = new Informacion();

    private String email;
    private String telefono;
    private String direccion;

    public static Informacion getInstance() {
        return ourInstance;
    }

    private Informacion() {
        email = "";
        telefono ="123456789";
        direccion = "";
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }
}
