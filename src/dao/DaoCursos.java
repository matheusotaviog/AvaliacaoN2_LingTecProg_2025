package dao;

import db.DB;
import model.Cursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaoCursos {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public DaoCursos() {
        conn = DB.getConnection();
        st = null;
        rs = null;
    }

    public void insert(Cursos cursos) {
        String sql = "insert into Cursos (nome, carga_hora, area) values (?, ?, ?)";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, cursos.getNome());
            st.setInt(2, cursos.getCargaHora());
            st.setString(3, cursos.getArea());
            st.execute();
            System.out.println("Registro inserido com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha na inclusão do Registro");

        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public void delete(int id) {
        String sql = "delete from AULA_JAVA.Cursos where id = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
            System.out.println("Registro excluído com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha na exclusão do Registro");

        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public List<Cursos> select() {
        String sql = "SELECT * FROM Cursos;";
        List<Cursos> listaCursos = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            Cursos cursos;
            while (rs.next()) {
                cursos = new Cursos();
                cursos.setId(rs.getInt("id"));
                cursos.setNome(rs.getString("nome"));
                cursos.setCargaHora(rs.getInt("carga_hora"));
                cursos.setArea(rs.getString("area"));

                listaCursos.add(cursos);

            }

            for(Cursos cursosSelect : listaCursos) {

                System.out.println("\nID: " + cursosSelect.getId() +
                        "; NOME: " + cursosSelect.getNome() +
                        "; CARGA HORA: " + cursosSelect.getCargaHora() +
                        "; AREA: " + cursosSelect.getArea());

            }

        } catch (Exception e) {
            e.printStackTrace();
            listaCursos = null;
            System.out.println("Falha na seleção dos Registros");

        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }

        return listaCursos;
    }

    public void update() {
        String sql = "UPDATE Cursos SET nome = ?, carga_hora = ?, area = ? WHERE id = ?";

        /*SELECT ABAIXO DESABILITADO, POIS ESTAVA FECHANDO A CONEXÃO
        * COM O DB, OCASIONANDO EM ERRO NO UPDATE.*/
        //select();

        Scanner sc = new Scanner(System.in);

        System.out.print("\nQual ID deseja realizar o UPDATE? ");
        int id = sc.nextInt();

        /*PENSADO EM FAZER A VALIDAÇÃO DO "ID NÃO ENCONTRADO" AQUI TAMBÉM,
        * DEMANDARIA CRIAR UMA FUNÇÃO PARA NÃO REPETIÇÃO DE CÓDIGO.*/

        sc.nextLine();
        System.out.print("\nDigite o novo nome do curso: ");
        String novoNome = sc.nextLine();

        System.out.print("\nDigite a nova Carga Horária do curso: ");
        int novaCargaHoraria = sc.nextInt();

        sc.nextLine();
        System.out.print("\nDigite a nova Área do curso: ");
        String novaArea = sc.nextLine();

        sc.close();

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, novoNome);
            st.setInt(2, novaCargaHoraria);
            st.setString(3, novaArea);
            st.setInt(4, id);
            st.execute();

            sql = "SELECT * FROM Cursos WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            Cursos cursos = null;
            while (rs.next()) {
                cursos = new Cursos();
                cursos.setId(rs.getInt("id"));
                cursos.setNome(rs.getString("nome"));
                cursos.setCargaHora(rs.getInt("carga_hora"));
                cursos.setArea(rs.getString("area"));
            }

            if(cursos == null) {
                System.out.println("ID não encontrado.");
                return;
            }

            System.out.println("Registro Alterado com sucesso");

            System.out.println("Novos dados:");
            System.out.println("ID: " + cursos.getId() +
                    "; NOME: " + cursos.getNome() +
                    "; CARGA HORA: " + cursos.getCargaHora() +
                    "; AREA: " + cursos.getArea());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha na alteração do Registro");

        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection(); // <- Fecha tudo no final
        }

    }

}
