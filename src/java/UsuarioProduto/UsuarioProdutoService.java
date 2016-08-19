/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioProduto;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author was
 */
public class UsuarioProdutoService {
    
    public UsuarioProduto salvarUsuarioProduto(UsuarioProduto usuarioProduto) {
        UsuarioProdutoDAO dao = new UsuarioProdutoDAO();
        if (usuarioProduto.getIdSgmUsuarioProduto() == 0) {
            dao.inserirUsuarioProduto(usuarioProduto);
            return usuarioProduto;
        } else {
            dao.alterarUsuarioProduto(usuarioProduto);
            return usuarioProduto;
        }
    }
  

    public List<UsuarioProduto> listarUsuarioProdutoPorId(long pIdUsuario) {
        UsuarioProdutoDAO dao = new UsuarioProdutoDAO();
        List<UsuarioProduto> listaUsuarioProduto = new ArrayList<UsuarioProduto>();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "sgm_usuario_produto.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(pIdUsuario), TipoCondicaoWhere.Numero);
        listaUsuarioProduto = dao.listarUsuarioProduto(condicao);
        return listaUsuarioProduto;
    }

    public boolean deletarUsuarioProduto(long idUsuario,String idProduto) {
        UsuarioProdutoDAO dao = new UsuarioProdutoDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "sgm_usuario_produto.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(idUsuario), TipoCondicaoWhere.Numero);
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "sgm_usuario_produto.id_produto", GeneroCondicaoWhere.igual, String.valueOf(idProduto), TipoCondicaoWhere.Texto);
        return dao.deletarUsuarioProduto(condicao);
    }
    
     public boolean deletarUsuarioProdutoPorIdUsuario(long idUsuario) {
        UsuarioProdutoDAO dao = new UsuarioProdutoDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "sgm_usuario_produto.id_sgm_usuario", GeneroCondicaoWhere.igual, String.valueOf(idUsuario), TipoCondicaoWhere.Numero);
        return dao.deletarUsuarioProduto(condicao);
    }
    
}
