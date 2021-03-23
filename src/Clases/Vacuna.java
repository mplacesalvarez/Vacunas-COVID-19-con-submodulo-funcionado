package Clases;

/**
 * Esta clase se utiliza para crear una vacuna con los atributos nombre;
 * número de dosis necesarias, número total de vacunas disponibles y  número de vacunas administradas
 *
 * @author Manuel Places Alvarez
 */

public class Vacuna {
    private String nombre;
    private int num_dosis;
    private int disponibles;
    private int administradas;

    public Vacuna() {
    }

    public Vacuna(String nombre, int num_dosis, int disponibles, int administradas) {
        this.nombre = nombre;
        this.num_dosis = num_dosis;
        this.disponibles = disponibles;
        this.administradas = administradas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNum_dosis() {
        return num_dosis;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setNum_dosis(int num_dosis) {
        this.num_dosis = num_dosis;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }


    public String toString() {

        return "Nombre= " + getNombre() + " \tNúmero de dosis disponibles= " + getDisponibles() + " \tNúmero de dosis necesarias= " + getNum_dosis();

    }
}
