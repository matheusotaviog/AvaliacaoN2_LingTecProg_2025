package view;

import dao.DaoCursos;

public class FormDelete {

    public static void main(String[] args) {

        DaoCursos dao = new DaoCursos();
        dao.delete(3);
    }
    
}
