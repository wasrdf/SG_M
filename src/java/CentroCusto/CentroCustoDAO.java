/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CentroCusto;

import ClausulaSQL.ClausulaWhere;
import CentroCusto.CentroCusto;
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
public class CentroCustoDAO {
    
    public List<CentroCusto> listarCentroCustos(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            List<CentroCusto> lstCentroCustos = new ArrayList<CentroCusto>();
            String sql = "SELECT idCentroDeCusto,CdChamada,NmCentroDeCusto FROM centroDeCusto "+sClausula.montarsClausula() ;
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CentroCusto centroCusto = new CentroCusto();
                centroCusto.setIdCentroCusto(rs.getString("idcentrodeCusto"));
                centroCusto.setNmCentroDeCusto(rs.getString("NmCentroDeCusto"));
                centroCusto.setCdChamada(rs.getLong("cdchamada"));
                lstCentroCustos.add(centroCusto);
            }
            rs.close();
            return lstCentroCustos;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar centro de custos.," + ex.getMessage());
        }
    }
    
    public CentroCusto listarCentroCustoPorId(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            String sql = "SELECT * FROM CentroDeCusto "+sClausula.montarsClausula() ;
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();
            CentroCusto centroCusto = new CentroCusto();   
            if (rs.next()) {
                centroCusto.setIdCentroCusto(rs.getString("IdCentroDeCusto"));
                centroCusto.setNmCentroDeCusto(rs.getString("nmCentroDeCusto"));
                centroCusto.setCdChamada(rs.getLong("cdchamada"));
            }
            rs.close();
            return centroCusto;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar centroCusto.," + ex.getMessage());
        }
    }

}
