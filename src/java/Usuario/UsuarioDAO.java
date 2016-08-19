package Usuario;

import ClausulaSQL.ClausulaWhere;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listarUsuarios(ClausulaWhere sClausula) {

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();

            List<Usuario> lstUsuarios = new ArrayList<Usuario>();
            String sql = "SELECT * FROM vw_sgm_usuario " + sClausula.montarsClausula();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            System.out.println("SELECT :" + sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdSgmUsuario(rs.getInt("id_sgm_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setDataCadastro(rs.getDate("data_cadastro"));
                usuario.setSituacao(rs.getString("situacao"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNivel(rs.getString("nivel"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setIdFuncionarioFP(rs.getString("idFuncionarioFP"));
                usuario.setIdPessoa(rs.getString("idpessoa"));
                usuario.setTermoAceitoOk(rs.getString("termo_aceito_ok"));
                //usuario.setC(rs.getString("cdChamadaPACK"));
                usuario.setResponsavelCartaPam(rs.getString("responsavel_carta_pam"));
                usuario.setCartasPam(rs.getString("cartas_pam"));
                usuario.setResponsavelCartaPamEmail(rs.getString("responsavel_carta_pam_email"));
                usuario.setCategoria(rs.getString("categoria"));
                usuario.setCustoFixoPublico(rs.getString("custo_fixo_publico"));
                usuario.setIdFilial(rs.getString("idfilial"));
                lstUsuarios.add(usuario);
            }
            rs.close();

            return lstUsuarios;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuário," + ex.getMessage());
        }
    }

    public long ultimoIdUsuario() {
        try {
            long idUsuario = 0;
            String sql = "select MAX(id_sgm_usuario) id_sgm_usuario from sgm_usuario";
            Conexao conexao = new Conexao();
            Connection conn;
            conn = conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getLong("id_sgm_usuario");
            }
            rs.close();
            ps.close();

            return idUsuario;
        } catch (SQLException ex) {
            throw new RuntimeException("Usuário não foi encontrado" + ex.getMessage());
        }
    }

    public Usuario inserirUsuario(Usuario usuario) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO sgm_usuario ("
                    + "usuario,\n"
                    + "situacao,\n"
                    + "email,\n"
                    + "nivel,\n"
                    + "senha,\n"
                    + "idPessoa,\n"
                    + "idFuncionarioFP,\n"
                    + "categoria,\n"
                    + "custo_fixo_publico,\n"
                    + "idFilial,\n"
                    + "responsavel_carta_pam,\n"
                    + "responsavel_carta_pam_email,\n"
                    + "senha_sgm_interno,\n"
                    + "cartas_pam,\n"
                    + "data_cadastro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdatetime())");

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getSituacao());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getNivel());
            ps.setString(5, usuario.getSenha());
            ps.setString(6, usuario.getIdPessoa());
            ps.setString(7, usuario.getIdFuncionarioFP());
            ps.setString(8, usuario.getCategoria());
            ps.setString(9, usuario.getCustoFixoPublico());
            ps.setString(10, usuario.getIdFilial());
            ps.setString(11, usuario.getResponsavelCartaPam());
            ps.setString(12, usuario.getResponsavelCartaPamEmail());
            ps.setString(13, usuario.getSenhaSgmInterno());
            ps.setString(14, usuario.getCartasPam());
            
            ps.execute();
            ps.close();
           usuario.setIdSgmUsuario(ultimoIdUsuario());
           return usuario;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir usuário," + e.getMessage());
        }

    }

    public void deletarUsuario(Usuario usuario) {
        try {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        PreparedStatement ps;
            ps = conn.prepareStatement("DELETE FROM sgm_usuario WHERE id_sgm_usuario=?");
            ps.setLong(1, usuario.getIdSgmUsuario());
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuário," + ex.getMessage());
        }

    }

    public void alterarUsuario(Usuario usuario) {
        try {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();

        String sql = "UPDATE sgm_usuario set  "
                + " usuario=?,\n"
                + "situacao=?,\n"
                + "email=?,\n"
                + "nivel=?,\n"
                + "senha=?,\n"
                + "idPessoa=?,\n"
                + "idFuncionarioFP=?,\n"
                + "categoria=?,\n"
                + "custo_fixo_publico=?,\n"
                + "idFilial=?,\n"
                + "responsavel_carta_pam=?,\n"
                + "responsavel_carta_pam_email=?,\n"
                + "senha_sgm_interno=?,\n"
                + "cartas_pam=?  WHERE id_sgm_usuario=? ";

        PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            System.out.println("passando pelo dao");
            System.out.println(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getSituacao());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getNivel());
            ps.setString(5, usuario.getSenha());
            ps.setString(6, usuario.getIdPessoa());
            ps.setString(7, usuario.getIdFuncionarioFP());
            ps.setString(8, usuario.getCategoria());
            ps.setString(9, usuario.getCustoFixoPublico());
            ps.setString(10, usuario.getIdFilial());
            ps.setString(11, usuario.getResponsavelCartaPam());
            ps.setString(12, usuario.getResponsavelCartaPamEmail());
            ps.setString(13, usuario.getSenhaSgmInterno());
            ps.setString(14, usuario.getCartasPam());
            ps.setLong(15, usuario.getIdSgmUsuario());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuário," + ex.getMessage());
        }

    }

    public void alterarPassword(Usuario usuario) {
        try {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        System.out.println("Metodo AlterarPassword");
        String sql = "UPDATE sgm_usuario set senha=? WHERE email=?";

        PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            System.out.println("passando pelo dao USUARIO.DAO");
            System.out.println(sql);
            ps.setString(1, usuario.getSenha());
            System.out.println("Senha:" + usuario.getSenha());
            ps.setString(2, usuario.getEmail());
            System.out.println("Email:" + usuario.getEmail());
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuário," + ex.getMessage());
        }

    }

}
