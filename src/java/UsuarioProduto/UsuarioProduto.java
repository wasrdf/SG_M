/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioProduto;

import java.util.Date;

/**
 *
 * @author was
 */
public class UsuarioProduto {
    private long idSgmUsuarioProduto;
    private long idSgmUsuario;
    private String idProduto;
    private Date dataCadastro;
    private String nomeProduto;

    public long getIdSgmUsuarioProduto() {
        return idSgmUsuarioProduto;
    }

    public void setIdSgmUsuarioProduto(long idSgmUsuarioProduto) {
        this.idSgmUsuarioProduto = idSgmUsuarioProduto;
    }

    public long getIdSgmUsuario() {
        return idSgmUsuario;
    }

    public void setIdSgmUsuario(long idSgmUsuario) {
        this.idSgmUsuario = idSgmUsuario;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    
}
