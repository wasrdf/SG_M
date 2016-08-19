/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CentroCustoMissionario;

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
public class CentroCustoMissionarioDAO {

    public List<CentroCustoMissionario> listarCentroCustoMissionario(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            List<CentroCustoMissionario> lstCentroCustoMissionario = new ArrayList<CentroCustoMissionario>();
            String sql = "select sgm_ccusto_mis.*,CentroDeCusto.NmCentroDeCusto from sgm_ccusto_mis\n"
                    + " inner join CentroDeCusto on CentroDeCusto.IdCentroDeCusto = sgm_ccusto_mis.id_ccusto " + sClausula.montarsClausula();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CentroCustoMissionario centroCustoMissionario = new CentroCustoMissionario();
                centroCustoMissionario.setIdCusto(rs.getString("id_ccusto"));
                centroCustoMissionario.setIdSgmCcustoMissionario(rs.getLong("id_sgm_ccusto_mis"));
                centroCustoMissionario.setIdSgmUsuario(rs.getLong("id_sgm_usuario"));
                lstCentroCustoMissionario.add(centroCustoMissionario);
            }
            rs.close();
            rs.close();
            return lstCentroCustoMissionario;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar centro de custo," + ex.getMessage());
        }
    }

    public CentroCustoMissionario inserirCentroCustoMissionario(CentroCustoMissionario centroCustoMissionario) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(" INSERT INTO sgm_ccusto_mis (id_sgm_usuario,id_ccusto) VALUES (?,?) ");

            ps.setLong(1, centroCustoMissionario.getIdSgmUsuario());
            ps.setString(2, centroCustoMissionario.getIdCusto());

            ps.execute();
            ps.close();
            return centroCustoMissionario;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir usuário produto" + e.getMessage());
        }

    }

    public boolean deletarCentroCustoMissionario(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PreparedStatement ps;
            String sql = "DELETE FROM sgm_ccusto_mis " + sClausula.montarsClausula();
            ps = conn.prepareStatement(sql);
            
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar centro de custo," + ex.getMessage());
        }

    }


}
