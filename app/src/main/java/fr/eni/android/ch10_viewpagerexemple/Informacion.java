package fr.eni.android.ch10_viewpagerexemple;

public class Informacion {
    private static final Informacion ourInstance = new Informacion();

    private String email;
    private int telefono;
    private String coordenadas;

    public static Informacion getInstance() {
        return ourInstance;
    }

    private Informacion() {
        email = "ejemplo@gmail.com";
        telefono = 123456789;
        coordenadas = "46.414382,10.013988";
    }

    public String getCoordenadas() {

        return coordenadas;
    }

    public String getEmail() {

        return email;
    }

    public int getTelefono() {

        return telefono;
    }
}
