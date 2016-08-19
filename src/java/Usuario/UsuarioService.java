package Usuario;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import java.util.ArrayList;
import java.util.List;
import Util.Util;

public class UsuarioService {
  
    public List<Usuario> listarUsuario(String nome) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "vw_sgm_usuario.nome", GeneroCondicaoWhere.contem, nome, TipoCondicaoWhere.Texto);
        usuarios = usuarioDAO.listarUsuarios(condicao);
        return usuarios;
    }

    public Usuario logar(String pUsuario, String pSenha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println("Iniando metodo logar da classe UsuarioService...");
        List<Usuario> usuarios = new ArrayList<Usuario>();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "vw_sgm_usuario.usuario", GeneroCondicaoWhere.igual, pUsuario.trim(), TipoCondicaoWhere.Texto);
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "vw_sgm_usuario.senha", GeneroCondicaoWhere.igual, pSenha.trim(), TipoCondicaoWhere.Texto);
        Usuario usuario = new Usuario();
        usuarios = usuarioDAO.listarUsuarios(condicao);
        if (!usuarios.isEmpty()) {
            usuario = usuarios.get(0);
            System.out.println("Iniando metodo iniciando metodo listarUsuarios:" + usuario.getNome());
        }
        return usuario;
    }
    public Usuario verificarUsuario(String pSenha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println("Iniando metodo logar da classe UsuarioService...");
        List<Usuario> usuarios = new ArrayList<Usuario>();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "vw_sgm_usuario.senha", GeneroCondicaoWhere.igual, pSenha.trim(), TipoCondicaoWhere.Texto);
        Usuario usuario = new Usuario();
        usuarios = usuarioDAO.listarUsuarios(condicao);
        if (!usuarios.isEmpty()) {
            usuario = usuarios.get(0);
            System.out.println("Iniando metodo iniciando metodo listarUsuarios:" + usuario.getNome());
        }
        return usuario;
    }

    public boolean enviarEmailSenha(String pEmail, String pSenha) {
        boolean retorno = false;
        try {
            Usuario usuario = new Usuario();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = new ArrayList<Usuario>();
            ClausulaWhere condicao = new ClausulaWhere();

            condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "vw_sgm_usuario.email", GeneroCondicaoWhere.igual, pEmail.trim(), TipoCondicaoWhere.Texto);
            usuarios = usuarioDAO.listarUsuarios(condicao);

            if (usuarios.size() > 0) {

                String msg;
                usuario = usuarios.get(0);
                msg = "Sua senha foi solicitada no SGM, abaixo informações de acesso";
                msg = msg + " Usuário:" + usuario.getUsuario();

                msg = msg + " Nova Senha:" + pSenha;
                Util util = new Util();
                retorno = util.enviarEmail(pEmail, msg, "Solicitação de senha");
            }
            return retorno;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar a senha," + e.getMessage());
        }

    }


    public void alterarPassword(Usuario pUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.alterarPassword(pUsuario);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuario.getIdSgmUsuario() == 0) {
            usuarioDAO.inserirUsuario(usuario);
        } else {
            usuarioDAO.alterarUsuario(usuario);
        }
        return usuario;
    }

    public void deletarUsuario(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.deletarUsuario(usuario);
    }
}
