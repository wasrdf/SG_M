/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartasPAM;

import java.util.Date;
import javax.xml.crypto.Data;

/**
 *
 * @author was
 */
public class CartasPAM {

    private String Missionario;
    private String Situacao;
  
    private String Versiculo;
    private String Saudacao;
    private String Conteudo;
    private String Agradecimento;
    private long IdCarta;
    private long idSgmUsuario;
    private String Parecer;
    private String Path;
    private String EmailDoParceiro;
    private String CartaCompleta;
    private Date dtCadastro;
    private Date dtEnvio;
    private Date dtEdicao;

    public Date getDtEdicao() {
        return dtEdicao;
    }

    public void setDtEdicao(Date dtEdicao) {
        this.dtEdicao = dtEdicao;
    }


    
    public String getMissionario() {
        return Missionario;
    }

    public void setMissionario(String Missionario) {
        this.Missionario = Missionario;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }

    public String getVersiculo() {
        return Versiculo;
    }

    public void setVersiculo(String Versiculo) {
        this.Versiculo = Versiculo;
    }

    public String getSaudacao() {
        return Saudacao;
    }

    public void setSaudacao(String Saudacao) {
        this.Saudacao = Saudacao;
    }

    public String getConteudo() {
        return Conteudo;
    }

    public void setConteudo(String Conteudo) {
        this.Conteudo = Conteudo;
    }

    public String getAgradecimento() {
        return Agradecimento;
    }

    public void setAgradecimento(String Agradecimento) {
        this.Agradecimento = Agradecimento;
    }

    public long getIdCarta() {
        return IdCarta;
    }

    public void setIdCarta(long IdCarta) {
        this.IdCarta = IdCarta;
    }

    public long getIdSgmUsuario() {
        return idSgmUsuario;
    }

    public void setIdSgmUsuario(long idSgmUsuario) {
        this.idSgmUsuario = idSgmUsuario;
    }


    public String getParecer() {
        return Parecer;
    }

    public void setParecer(String Parecer) {
        this.Parecer = Parecer;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getEmailDoParceiro() {
        return EmailDoParceiro;
    }

    public void setEmailDoParceiro(String EmailDoParceiro) {
        this.EmailDoParceiro = EmailDoParceiro;
    }

    public String getCartaCompleta() {
        return CartaCompleta;
    }

    public void setCartaCompleta(String CartaCompleta) {
        this.CartaCompleta = CartaCompleta;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtEnvio() {
        return dtEnvio;
    }

    public void setDtEnvio(Date dtEnvio) {
        this.dtEnvio = dtEnvio;
    }
    
    
    
}
