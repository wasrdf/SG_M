/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

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
public class ProdutoDAO {

    public List<Produto> listarProdutos(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            List<Produto> lstProdutos = new ArrayList<Produto>();
            String sql = "SELECT * FROM produto "+sClausula.montarsClausula() ;
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getString("idproduto"));
                produto.setNomeProduto(rs.getString("nmProduto"));
                lstProdutos.add(produto);
            }
            rs.close();
            return lstProdutos;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar produtos.," + ex.getMessage());
        }
    }
    
    public Produto listarProdutoPorId(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "SELECT * FROM produto "+sClausula.montarsClausula() ;
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();
            Produto produto = new Produto();   
            if (rs.next()) {
               
                produto.setIdProduto(rs.getString("idproduto"));
                produto.setNomeProduto(rs.getString("nmProduto"));
            }
            rs.close();
            return produto;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar produto.," + ex.getMessage());
        }
    }
    
    
    
}
