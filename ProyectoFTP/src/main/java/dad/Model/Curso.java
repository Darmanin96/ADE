package dad.Model;

public enum Curso {
    PRIMERO_DAM(1, "DAM"),
    SEGUNDO_DAM(2, "DAM"),
    PRIMERO_ASIR(1, "ASIR"),
    SEGUNDO_ASIR(2, "ASIR"),
    PRIMERO_DAW(1, "DAW"),
    SEGUNDO_DAW(2, "DAW");

    private final int año;
    private final String nombreCurso;

    // Constructor del enum
    Curso(int año, String nombreCurso) {
        this.año = año;
        this.nombreCurso = nombreCurso;
    }

    // Getter para obtener el año
    public int getAño() {
        return año;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    // Método toString() para cambiar el formato del enum
    @Override
    public String toString() {
        return año + " " + nombreCurso;
    }
}
