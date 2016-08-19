/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PessoaConexus;

import Util.Conexao;
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
public class PessoaConexusDAO {
    
    public List<PessoaConexus> listarPessoaConexus(ClausulaSQL.ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            List<PessoaConexus> lstPessoaConexuss = new ArrayList<PessoaConexus>();
            String sql = "SELECT * FROM pessoa " + sClausula.montarsClausula();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PessoaConexus pessoaConexus = new PessoaConexus();
                pessoaConexus.setIdPessoa(rs.getString("idpessoa"));
                pessoaConexus.setCdChamada(rs.getString("cdchamada"));
                pessoaConexus.setNmPessoa(rs.getString("nmpessoa"));
                lstPessoaConexuss.add(pessoaConexus);
            }
            rs.close();
            return lstPessoaConexuss;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar pessoa Conexuss.," + ex.getMessage());
        }
    }
    

}
