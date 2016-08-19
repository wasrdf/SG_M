package Login;

import Usuario.Usuario;
import Usuario.UsuarioController;
import Usuario.UsuarioService;
import Util.Mensagem;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    private HttpSession session;
    String usuarioLogin;
    String senha;
    String emailRecuperacao;
    String cor = "";
    String novaSenha;
    String repetirSenha;
    Usuario usuario = new Usuario();

    @PostConstruct
    public void init() {
        novaSenha = "";
        repetirSenha = "";
    }

    public void logar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigestSenhaAtual[] = algorithm.digest(senha.getBytes("UTF-8"));
            StringBuilder hexStringSenhaAtual = new StringBuilder();
            for (byte b : messageDigestSenhaAtual) {
                hexStringSenhaAtual.append(String.format("%02X", 0xFF & b));
            }
            //SENHA ATUAL EM HEXADECIMAL
            String senhaHexAtual = hexStringSenhaAtual.toString();

            UsuarioService usuarioService = new UsuarioService();
            usuario = usuarioService.logar(usuarioLogin, senhaHexAtual);
            if (usuario.getIdSgmUsuario() != 0) {
                session = (HttpSession) ctx.getExternalContext().getSession(false);
                session.setAttribute("currentUser", usuario);
                session.setAttribute("context", ctx.getExternalContext().getRequestContextPath());

                FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/principal/principal.jsf");
            } else {
                Mensagem.saveMessage("Usuário ou senha inválidos", "");
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usuario.setSenha("");
    }

    public void mudarPagina(String pPage) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + pPage);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String criptografarSenha(String pSenha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigestSenhaAtual[] = algorithm.digest(pSenha.getBytes("UTF-8"));
        StringBuilder hexStringSenhaAtual = new StringBuilder();
        for (byte b : messageDigestSenhaAtual) {
            hexStringSenhaAtual.append(String.format("%02X", 0xFF & b));
        }
        return hexStringSenhaAtual.toString();

    }

    public void enviarSenhaEmail() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        int randomNumber = random.nextInt(max - min) + min;

        UsuarioService usuarioService = new UsuarioService();
        try {

            usuario.setSenha(criptografarSenha(String.valueOf(randomNumber)));

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        usuario.setEmail(emailRecuperacao);
        usuarioService.alterarPassword(usuario);
        boolean retorno = usuarioService.enviarEmailSenha(emailRecuperacao, String.valueOf(randomNumber));

        if (retorno = false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Email não encontrado. Por favor, tente novamente!", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "email enviado com sucesso!", ""));
        }

    }

    public void alterarSenha() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        UsuarioService usuarioService = new UsuarioService();
        Usuario verificarUsuario = usuarioService.verificarUsuario(criptografarSenha(usuario.getSenha()));
        if (verificarUsuario.getIdSgmUsuario() != 0) {
            if (novaSenha.equals(repetirSenha)) {
                try {
                    usuario.setSenha(criptografarSenha(repetirSenha));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuarioService.alterarPassword(usuario);
                Mensagem.saveMessage("Senha alterada com sucesso.", "Sucesso");
                mudarPagina("/");
                
                novaSenha = "";
                repetirSenha = "";
                usuario.setSenha("");
            } else {
                Mensagem.saveMessage("As senhas devem ser iguais.", "");
            }
        } else {
            Mensagem.saveMessage("Senha atual incorreta.", "");
        }
    }

    public void sair() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        s.invalidate();
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* GETs SETs*/
    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmailRecuperacao() {
        return emailRecuperacao;
    }

    public void setEmailRecuperacao(String emailRecuperacao) {
        this.emailRecuperacao = emailRecuperacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
