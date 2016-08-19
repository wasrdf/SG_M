/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import UsuarioProduto.UsuarioProduto;
import UsuarioProduto.UsuarioProdutoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author was
 */
public class UsuarioProdutoTestes {
    public static void salvarUsuarioProduto() {
        UsuarioProdutoService usuarioProdutoService = new UsuarioProdutoService();
        UsuarioProduto usuarioProduto = new UsuarioProduto();
        usuarioProduto.setIdSgmUsuarioProduto(1375);
        usuarioProduto.setIdSgmUsuario(878787);
        usuarioProduto.setIdProduto("Teste 000");
        usuarioProduto =  usuarioProdutoService.salvarUsuarioProduto(usuarioProduto);
        System.out.println(">>>>> ID = " + usuarioProduto.getIdSgmUsuarioProduto());
    }
    
    public static void deletarUsuarioProduto() {
            UsuarioProdutoService usuarioProdutoService = new UsuarioProdutoService();
            UsuarioProduto usuarioProduto = new UsuarioProduto();
            usuarioProduto.setIdSgmUsuarioProduto(1374);
            usuarioProdutoService.deletarUsuarioProduto(usuarioProduto);
    }
    
    public static void listarUsuarioProduto() {
            UsuarioProdutoService usuarioProdutoService = new UsuarioProdutoService();
            List<UsuarioProduto> listaUsuarioProduto = new ArrayList<UsuarioProduto>();
            listaUsuarioProduto = usuarioProdutoService.listarUsuarioProdutoPorId(708);
            for (UsuarioProduto usuarioProduto : listaUsuarioProduto) {
                System.out.println(usuarioProduto.getNomeProduto());
        }
    }
    public static void main(String[] args) {
      
        //deletarUsuarioProduto();
    }
}
