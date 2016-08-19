/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartasPAM;

import ClausulaSQL.ClausulaWhere;
import Usuario.Usuario;
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
public class CartasPamDAO {
    /// <summary>
    /// Carrega todo detalhe da Carta
    /// </summary>
    /// <param name="Condição"></param>
    /// <returns></returns>

    public List<CartasPAM> listarCartas(ClausulaWhere sClausula) {
        String sql = "select * from sgm_cartas " + sClausula.montarsClausula();
        Conexao conexao = new Conexao();
        Connection conn;
        try {
            conn = conexao.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<CartasPAM> cartas = new ArrayList<CartasPAM>();
            while (rs.next()) {
                CartasPAM carta = new CartasPAM();
                carta.setIdCarta(rs.getLong("id_sgm_cartas"));
                carta.setMissionario(rs.getString("missionario"));
                carta.setSaudacao(rs.getString("saudacao"));
                carta.setSituacao(rs.getString("situacao"));
                carta.setVersiculo(rs.getString("versiculo"));
                carta.setConteudo(rs.getString("conteudo"));
                carta.setAgradecimento(rs.getString("agradecimento"));
                carta.setParecer(rs.getString("parecer"));
                carta.setPath(rs.getString("path"));
                carta.setCartaCompleta(rs.getString("carta_completa"));
                carta.setDtCadastro(rs.getDate("data_cadastro"));
                carta.setDtEnvio(rs.getDate("data_envio"));
                carta.setDtEdicao(rs.getDate("data_edicao"));
                carta.setIdSgmUsuario(rs.getLong("id_sgm_usuario"));
                cartas.add(carta);
            }
            rs.close();
            ps.close();
            return cartas;

        } catch (SQLException ex) {
            throw new RuntimeException("erro ao listar cartas." + ex.getMessage());
        }
    }

    public void alterarCarta(CartasPAM pCarta) throws SQLException {

        String sql = "UPDATE sgm_cartas SET versiculo=? ,saudacao= ? ,  conteudo=?,agradecimento=? ,carta_completa = ? ,situacao=? ,data_edicao=?,path=? WHERE id_sgm_cartas=? ";
        Conexao conexao = new Conexao();
        Connection conn;
        conn = conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, pCarta.getVersiculo());
        ps.setString(2, pCarta.getSaudacao());
        ps.setString(3, pCarta.getConteudo());
        ps.setString(4, pCarta.getAgradecimento());
        ps.setString(5, pCarta.getCartaCompleta());
        ps.setString(6, pCarta.getSituacao());
        try {
            ps.setDate(7, new java.sql.Date(pCarta.getDtEdicao().getTime()));
        } catch (Exception e) {
            ps.setDate(7, null);
        }
        ps.setString(8, pCarta.getPath());
        ps.setLong(9, pCarta.getIdCarta());
        ps.execute();
    }

    public void deletarCarta(CartasPAM pCartasPAM) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("DELETE FROM sgm_cartas WHERE id_sgm_cartas=?");
            ps.setLong(1, pCartasPAM.getIdCarta());
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar carta." + ex.getMessage());
        }
    }

    public List<Parceiro> ListarDeParceirosSemEmail(ClausulaWhere sClausula) throws SQLException {

        String sql = "select distinct  pessoa.CdChamada codigo, Pessoa.NmPessoa nome, PessoaEndereco.TpLogradouro logradouro_tipo, PessoaEndereco.NmLogradouro as Logradouro,\n"
                + "       PessoaEndereco.NrLogradouro logradouro_numero, PessoaEndereco.DsComplemento Complemento, bairro.NmBairro Bairro, Cidade.NmCidade Cidade,\n"
                + "       uf.nmuf uf, Pais.NmPais Pais, '21' DDD, '123456789' TELEFONE, email.DsContato email, sgm_usuario_produto.id_sgm_usuario,\n"
                + "       Missionario.NmPessoa NomeMiss, PessoaEndereco.CdCEP CEP \n"
                + "       From MovimentoCobranca\n"
                + "       inner join Pessoa_Categoria_Produto_Ser on Pessoa_Categoria_Produto_Ser.IdMovimentoCobranca = MovimentoCobranca.IdMovimentoCobranca\n"
                + "       inner join Pessoa_Categoria_Produto on Pessoa_Categoria_Produto.IdPessoa_Categoria_Produto = Pessoa_Categoria_Produto_Ser.IdPessoa_Categoria_Produto\n"
                + "       inner join Produto on produto.IdProduto = Pessoa_Categoria_Produto.IdProduto\n"
                + "       inner join sgm_usuario_produto on sgm_usuario_produto.id_produto = Produto.IdProduto\n"
                + "       inner join sgm_usuario on sgm_usuario.id_sgm_usuario = sgm_usuario_produto.id_sgm_usuario\n"
                + "       inner join Pessoa Missionario on Missionario.IdPessoa = sgm_usuario.idPessoa\n"
                + "       LEFT join Pessoa on Pessoa.IdPessoa = MovimentoCobranca.IdPessoa\n"
                + "       left join PessoaEndereco on PessoaEndereco.IdPessoa = Pessoa.IdPessoa\n"
                + "       left join Bairro on Bairro.IdBairro = PessoaEndereco.IdBairro\n"
                + "       left join Cidade on cidade.IdCidade = PessoaEndereco.IdCidade\n"
                + "       left join uf on uf.iduf = PessoaEndereco.IdUF\n"
                + "       left join Pais on pais.IdPais = PessoaEndereco.IdPais\n"
                + "       left join PESSOAENDERECO_TIPOCONTATO email ON email.IdPessoa = Pessoa.IdPessoa and email.IdTipoContato = '0000000004' and email.CdEndereco = '01'  and email.IdPessoaEndereco_Contato is not null\n"
                + "       inner join Pessoa_CaracteristicaPessoa on Pessoa_CaracteristicaPessoa.IdPessoa = Pessoa.IdPessoa\n"
                + "       inner join CaracteristicaPessoa on CaracteristicaPessoa.IdCaracteristicaPessoa = Pessoa_CaracteristicaPessoa.IdCaracteristicaPessoa\n"
                + "       WHERE  Pessoa_Categoria_Produto_Ser.StCobrancaSuspensa = 'N' and Pessoa_Categoria_Produto_Ser.StCobrancaParalisada = 'N' and Pessoa_Categoria_Produto_Ser.StDoacao = 'N' and PessoaEndereco.StEnderecoPrincipal = 'S' " + sClausula.montarsClausula();
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        List<Parceiro> parceiros = new ArrayList<Parceiro>();
        PreparedStatement ps = conn.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Parceiro parceiro = new Parceiro();
            parceiro.setNome(rs.getString("nome"));
            parceiro.setIdSgmUsuario(rs.getLong("id_sgm_usuario"));
            parceiro.setCdChamada(rs.getLong("codigo"));
            parceiro.setEmail(rs.getString("email"));
            parceiro.setCep(rs.getString("cep"));            
            parceiro.setUf(rs.getString("uf"));
            parceiro.setCidade(rs.getString("cidade"));
            parceiro.setBairro(rs.getString("bairro"));
            parceiro.setLogradouro(rs.getString("logradouro"));
            parceiro.setLogradouroTipo(rs.getString("logradouro_tipo"));
            parceiro.setLogradouroNumero(rs.getString("logradouro_numero"));
            parceiro.setComplemento(rs.getString("complemento"));
            parceiros.add(parceiro);

        }
        rs.close();
        ps.close();
        return parceiros;
    }

   
    public List<Parceiro> ListarDeParceirosComEmail(ClausulaWhere sClausula) throws SQLException {
        String sql = " select distinct Pessoa.NmPessoa nome ,email.DsContato email \n" +
"       From MovimentoCobranca\n" +
"       inner join Pessoa_Categoria_Produto_Ser on Pessoa_Categoria_Produto_Ser.IdMovimentoCobranca = MovimentoCobranca.IdMovimentoCobranca\n" +
"       inner join Pessoa_Categoria_Produto on Pessoa_Categoria_Produto.IdPessoa_Categoria_Produto = Pessoa_Categoria_Produto_Ser.IdPessoa_Categoria_Produto\n" +
"       inner join Produto on produto.IdProduto = Pessoa_Categoria_Produto.IdProduto\n" +
"       inner join sgm_usuario_produto on sgm_usuario_produto.id_produto = Produto.IdProduto\n" +
"       inner join sgm_usuario on sgm_usuario.id_sgm_usuario = sgm_usuario_produto.id_sgm_usuario\n" +
"       inner join Pessoa Missionario on Missionario.IdPessoa = sgm_usuario.idPessoa\n" +
"       LEFT join Pessoa on Pessoa.IdPessoa = MovimentoCobranca.IdPessoa\n" +
"       left join PessoaEndereco on PessoaEndereco.IdPessoa = Pessoa.IdPessoa\n" +
"       left join Bairro on Bairro.IdBairro = PessoaEndereco.IdBairro\n" +
"       left join Cidade on cidade.IdCidade = PessoaEndereco.IdCidade\n" +
"       left join uf on uf.iduf = PessoaEndereco.IdUF\n" +
"       left join Pais on pais.IdPais = PessoaEndereco.IdPais\n" +
"       left join PESSOAENDERECO_TIPOCONTATO email ON email.IdPessoa = Pessoa.IdPessoa and email.IdTipoContato = '0000000004' and email.CdEndereco = '01'  and email.IdPessoaEndereco_Contato is not null\n" +
"       inner join Pessoa_CaracteristicaPessoa on Pessoa_CaracteristicaPessoa.IdPessoa = Pessoa.IdPessoa\n" +
"       inner join CaracteristicaPessoa on CaracteristicaPessoa.IdCaracteristicaPessoa = Pessoa_CaracteristicaPessoa.IdCaracteristicaPessoa\n" +
"       WHERE  Pessoa_Categoria_Produto_Ser.StCobrancaSuspensa = 'N' \n" +
"       and Pessoa_Categoria_Produto_Ser.StCobrancaParalisada = 'N' \n" +
"       and Pessoa_Categoria_Produto_Ser.StDoacao = 'N' \n" +
"       and PessoaEndereco.StEnderecoPrincipal = 'S'\n" +
"       and email.DsContato is not null  " + sClausula.montarsClausula();
        
        List<Parceiro> parceiros = new ArrayList<Parceiro>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        PreparedStatement ps =conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Parceiro parceiro = new Parceiro();
            parceiro.setNome(rs.getString("nome"));
            parceiro.setEmail(rs.getString("email"));
            parceiros.add(parceiro);

        }
        rs.close();
        ps.close();
        return parceiros;
    }
    
}
