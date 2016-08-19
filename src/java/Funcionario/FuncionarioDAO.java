/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionario;

import Util.Conexao;
import Util.Conexao2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author was
 */
public class FuncionarioDAO {

    public List<Funcionario> listarFuncionarios(ClausulaSQL.ClausulaWhere sClausula) {

        try {
            Conexao2 conexao = new Conexao2();
            Connection conn = conexao.getConnection();

            List<Funcionario> lstFuncionarios = new ArrayList<Funcionario>();
            String sql = "select  * From vw_sgm_funcionario "+ sClausula.montarsClausula();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdPessoa(rs.getString("idfuncionario"));
                funcionario.setCdChamada(rs.getString("cdchamada"));
                funcionario.setNmPessoa(rs.getString("nmfuncionario"));
                funcionario.setFilial(rs.getString("filial"));
                lstFuncionarios.add(funcionario);
            }
            rs.close();
            return lstFuncionarios;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar funcionário.," + ex.getMessage());
        }
    }

}
