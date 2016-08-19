/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import Login.LoginController;
import Usuario.Usuario;
import Usuario.UsuarioService;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author was
 */
public class UsuarioTestes {
    
    public static void salvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("TESTE 222");
        usuario.setIdSgmUsuario(951);
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.salvarUsuario(usuario);
    }
    
    public static void listarUsuario() {
           UsuarioService usuarioService = new UsuarioService();
           List<Usuario> usuarios = usuarioService.listarUsuario("teste");
           for (Usuario usuario : usuarios) {
               System.out.println(">>>>" + usuario.getSituacao());
        }
    }
    
    public static void deletarUsuario() {
           UsuarioService usuarioService = new UsuarioService();
           Usuario usuario = new Usuario();
           usuario.setIdSgmUsuario(951);
           usuarioService.deletarUsuario(usuario);
    }
    
    public static void logar(String usuarioLogin,String senha) {
     Usuario usuario = new Usuario();
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
                System.out.print("Bem vindo");
            } else {
                System.out.print("Usuário ou senha inválidos,por favor tente novamente.");
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
    public static void main(String[] args) {
        listarUsuario();
    }
}
