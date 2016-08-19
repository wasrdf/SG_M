package Usuario;

import Produto.Produto;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author was
 */
public class Usuario  {

    private long idSgmUsuario;
    private String usuario;
    private String situacao;
    private String email;
    private String nivel;
    private String senha;
    private String idPessoa;
    private String idFuncionarioFP;
    private String termoAceitoDataOk;
    private String termoAceitoOk;
    private String infoAceite;
    private String categoria;
    private String custoFixoPublico;
    private String idFilial;
    private String responsavelCartaPam;
    private String responsavelCartaPamEmail;
    private String senhaSgmInterno;
    private String cartasPam;
    private String sgmLider;
    private String sgmSede;
    private String sgmMissionario;
    private String nome;
    private Date dataCadastro;
    private List<Produto> listaProduto;

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }
    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    
    public long getIdSgmUsuario() {
        return idSgmUsuario;
    }

    public void setIdSgmUsuario(long idSgmUsuario) {
        this.idSgmUsuario = idSgmUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getIdFuncionarioFP() {
        return idFuncionarioFP;
    }

    public void setIdFuncionarioFP(String idFuncionarioFP) {
        this.idFuncionarioFP = idFuncionarioFP;
    }

    public String getTermoAceitoDataOk() {
        return termoAceitoDataOk;
    }

    public void setTermoAceitoDataOk(String termoAceitoDataOk) {
        this.termoAceitoDataOk = termoAceitoDataOk;
    }

    public String getTermoAceitoOk() {
        return termoAceitoOk;
    }

    public void setTermoAceitoOk(String termoAceitoOk) {
        this.termoAceitoOk = termoAceitoOk;
    }

    public String getInfoAceite() {
        return infoAceite;
    }

    public void setInfoAceite(String infoAceite) {
        this.infoAceite = infoAceite;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCustoFixoPublico() {
        return custoFixoPublico;
    }

    public void setCustoFixoPublico(String custoFixoPublico) {
        this.custoFixoPublico = custoFixoPublico;
    }

    public String getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(String idFilial) {
        this.idFilial = idFilial;
    }

    public String getResponsavelCartaPam() {
        return responsavelCartaPam;
    }

    public void setResponsavelCartaPam(String responsavelCartaPam) {
        this.responsavelCartaPam = responsavelCartaPam;
    }

    public String getResponsavelCartaPamEmail() {
        return responsavelCartaPamEmail;
    }

    public void setResponsavelCartaPamEmail(String responsavelCartaPamEmail) {
        this.responsavelCartaPamEmail = responsavelCartaPamEmail;
    }

    public String getSenhaSgmInterno() {
        return senhaSgmInterno;
    }

    public void setSenhaSgmInterno(String senhaSgmInterno) {
        this.senhaSgmInterno = senhaSgmInterno;
    }

    public String getCartasPam() {
        return cartasPam;
    }

    public void setCartasPam(String cartasPam) {
        this.cartasPam = cartasPam;
    }

    public String getSgmLider() {
        return sgmLider;
    }

    public void setSgmLider(String sgmLider) {
        this.sgmLider = sgmLider;
    }

    public String getSgmSede() {
        return sgmSede;
    }

    public void setSgmSede(String sgmSede) {
        this.sgmSede = sgmSede;
    }

    public String getSgmMissionario() {
        return sgmMissionario;
    }

    public void setSgmMissionario(String sgmMissionario) {
        this.sgmMissionario = sgmMissionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
}
