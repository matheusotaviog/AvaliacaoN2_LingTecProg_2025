package model;

public class Cursos {

    private int id;
    private String nome;
    private int cargaHora;
    private String area;

    public Cursos() {

    }

    public Cursos(String nome, int cargaHora, String area) {
        super();
        this.nome = nome;
        this.cargaHora = cargaHora;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHora() {
        return cargaHora;
    }

    public void setCargaHora(int cargaHora) {
        this.cargaHora = cargaHora;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
