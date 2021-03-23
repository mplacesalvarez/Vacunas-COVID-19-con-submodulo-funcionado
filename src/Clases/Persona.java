package Clases;

public class Persona {
    /**
     * Esta clase se utiliza para crear una persona con los atributos nombre,
     * apellidos, DNI, edad y si es persona de riesgo
     *
     * @author Manuel Places Alvarez
     */

    private int edad, num_vac_inyect = 0, vac_quedan;
    private String riesgo;
    private String nombre, apellidos, dni;
    private String nombrevac;

    public Persona() {
    }

    public Persona(String nombre, String apellidos, String dni, int edad, String riesgo, String nombrevac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.edad = edad;
        this.riesgo = riesgo;
        this.nombrevac = nombrevac;
    }

    public int getEdad() {
        return edad;
    }

    public int getVac_quedan() {
        return vac_quedan;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNum_vac_inyect() {
        return num_vac_inyect;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNum_vac_inyect(int num_vac_inyect) {
        this.num_vac_inyect = num_vac_inyect;
    }

    public void setVac_quedan(int vac_quedan) {
        this.vac_quedan = vac_quedan;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombrevac() {
        return nombrevac;
    }

    public void setNombrevac(String nombrevac) {
        this.nombrevac = nombrevac;
    }

    public String toString() {

        return "Nombre= " + getNombre() + " \tApellidos= " + getApellidos() + " \tDNI= " + getDni() + " \tEdad= " + getEdad() + " \tRiesgo= " + getRiesgo() + " \tVacuna= " + getNombrevac() + " \tNúmero de vacunas inyectadas= " + getNum_vac_inyect();

    }
}
