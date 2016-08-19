/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CentroCustoMissionario;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import java.util.ArrayList;
import java.util.List;


public class CentroCustoMissionarioService {

    public CentroCustoMissionario salvarCentroCustoMissionario(CentroCustoMissionario usuarioProduto) {
        CentroCustoMissionarioDAO dao = new CentroCustoMissionarioDAO();
        dao.inserirCentroCustoMissionario(usuarioProduto);
        return usuarioProduto;
    }

    public List<CentroCustoMissionario> listarCentroCustoMissionarioPorId(long pIdUsuario) {
        CentroCustoMissionarioDAO dao = new CentroCustoMissionarioDAO();
        List<CentroCustoMissionario> listaCentroCustoMissionario = new ArrayList<CentroCustoMissionario>();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "sgm_ccusto_mis.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(pIdUsuario), TipoCondicaoWhere.Numero);
        listaCentroCustoMissionario = dao.listarCentroCustoMissionario(condicao);
        return listaCentroCustoMissionario;
    }

    public boolean deletarCentroCustoMissionario(long idUsuario, String idCcusto) {
        CentroCustoMissionarioDAO dao = new CentroCustoMissionarioDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "sgm_ccusto_mis.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(idUsuario), TipoCondicaoWhere.Numero);
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "sgm_ccusto_mis.id_ccusto", GeneroCondicaoWhere.igual, String.valueOf(idCcusto), TipoCondicaoWhere.Texto);
        return dao.deletarCentroCustoMissionario(condicao);
    }

    public boolean deletarCentroCustoMissionarioPorIdUsuario(long idUsuario) {
        CentroCustoMissionarioDAO dao = new CentroCustoMissionarioDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "sgm_ccusto_mis.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(idUsuario), TipoCondicaoWhere.Numero);
        return dao.deletarCentroCustoMissionario(condicao);
    }

}
