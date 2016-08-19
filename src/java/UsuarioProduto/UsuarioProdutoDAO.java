/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioProduto;

import ClausulaSQL.ClausulaWhere;
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
public class UsuarioProdutoDAO {

    public List<UsuarioProduto> listarUsuarioProduto(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            List<UsuarioProduto> lstUsuarioProduto = new ArrayList<UsuarioProduto>();
            String sql = " SELECT sgm_usuario_produto.*,Produto.NmProduto nomeProduto FROM sgm_usuario_produto \n"
                    + " inner join Produto on Produto.IdProduto = sgm_usuario_produto.id_produto " + sClausula.montarsClausula();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioProduto usuarioProduto = new UsuarioProduto();
                usuarioProduto.setIdSgmUsuarioProduto(rs.getLong("id_sgm_usuario_produto"));
                usuarioProduto.setIdSgmUsuario(rs.getLong("id_sgm_usuario"));
                usuarioProduto.setIdProduto(rs.getString("id_produto"));
                usuarioProduto.setNomeProduto(rs.getString("nomeProduto"));
                lstUsuarioProduto.add(usuarioProduto);
            }
            rs.close();
            rs.close();

            return lstUsuarioProduto;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuário produto," + ex.getMessage());
        }
    }

    public UsuarioProduto inserirUsuarioProduto(UsuarioProduto usuarioProduto) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(" INSERT INTO sgm_usuario_produto\n"
                    + "           (id_sgm_usuario\n"
                    + "           ,id_produto\n"
                    + "           ,data_cadastro)\n"
                    + "     VALUES\n"
                    + "           (?,?,sysdatetime())");

            ps.setLong(1, usuarioProduto.getIdSgmUsuario());
            ps.setString(2, usuarioProduto.getIdProduto());
           
            ps.execute();
            ps.close();
          
            return usuarioProduto;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir usuário produto" + e.getMessage());
        }

    }

    public boolean deletarUsuarioProduto(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PreparedStatement ps;
            String sql = "DELETE FROM sgm_usuario_produto "+sClausula.montarsClausula();
            ps = conn.prepareStatement(sql);
            
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar usuário," + ex.getMessage());
        }

    }

    public void alterarUsuarioProduto(UsuarioProduto usuarioProduto) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            String sql = "UPDATE sgm_usuario_produto SET id_produto = ? where id_sgm_usuario_produto = ? ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            System.out.println(sql);
            ps.setString(1, usuarioProduto.getIdProduto());
            ps.setLong(2, usuarioProduto.getIdSgmUsuarioProduto());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuário," + ex.getMessage());
        }

    }

}
