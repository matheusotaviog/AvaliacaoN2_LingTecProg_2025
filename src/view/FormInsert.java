package view;

import dao.DaoCursos;
import model.Cursos;

public class FormInsert {

    public static void main(String[] args) {

        Cursos cursos = new Cursos();
        cursos.setNome("Administração");
        cursos.setCargaHora(2000);
        cursos.setArea("Tecnologia da Informação");

        DaoCursos dao = new DaoCursos();
        dao.insert(cursos);

    }
    
}
