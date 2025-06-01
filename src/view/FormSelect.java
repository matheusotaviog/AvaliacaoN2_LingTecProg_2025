package view;

import dao.DaoCursos;
import model.Cursos;

import java.util.ArrayList;
import java.util.List;

public class FormSelect {

    public static void main(String[] args) {

        List<Cursos> listaCursos = new ArrayList<>();

        DaoCursos dao = new DaoCursos();
        listaCursos = dao.select();

    }
}
