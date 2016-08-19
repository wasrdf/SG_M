/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartasPAM;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author was
 */
public class CartasPAMService {

    public List<CartasPAM> listarCartas(String pNome) {
        CartasPamDAO cartasPamDAO = new CartasPamDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "missionario", GeneroCondicaoWhere.contem, pNome, TipoCondicaoWhere.Texto);
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.or, "situacao", GeneroCondicaoWhere.contem, pNome, TipoCondicaoWhere.Texto);
        return cartasPamDAO.listarCartas(condicao);
    }

    public List<Parceiro> listarParceiroSemEmail(long pIdUsuario) throws SQLException {
        CartasPamDAO cartasPamDAO = new CartasPamDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "sgm_usuario.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(pIdUsuario), TipoCondicaoWhere.Numero);
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "CaracteristicaPessoa.CdChamada", GeneroCondicaoWhere.diferente, "001320", TipoCondicaoWhere.Texto);
        return cartasPamDAO.ListarDeParceirosSemEmail(condicao);
    }

    public List<Parceiro> listarParceiroComEmail(long pIdUsuario) throws SQLException {
        CartasPamDAO cartasPamDAO = new CartasPamDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "sgm_usuario.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(pIdUsuario), TipoCondicaoWhere.Numero);
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "CaracteristicaPessoa.CdChamada", GeneroCondicaoWhere.igual, "001320", TipoCondicaoWhere.Texto);
        return cartasPamDAO.ListarDeParceirosComEmail(condicao);
    }

    public void alterarCarta(CartasPAM carta) throws SQLException {
        CartasPamDAO cartasPamDAO = new CartasPamDAO();
        cartasPamDAO.alterarCarta(carta);
    }

}
