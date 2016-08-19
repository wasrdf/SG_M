/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartasPAM;

import Util.Mensagem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author was
 */
@ManagedBean(name = "cartasPamController")
@ViewScoped
public class CartasPamController {
    Integer tela = 0;
    CartasPAM cartasPAM = new CartasPAM();
    List<CartasPAM> listaCarta = new ArrayList<CartasPAM>();
    String pesquisa = "";
    CartasPAMService cartasPAMService = new CartasPAMService();
    //0 - não mostra a foto na tela , 1 - mostras
    Integer foto = 0;
    List<Parceiro> listaParceiroComEmail = new ArrayList<Parceiro>();
    List<Parceiro> listaParceiroSemEmail = new ArrayList<Parceiro>();
    
    public void selecionar(CartasPAM pCartasPAM) {
        cartasPAM = pCartasPAM;
        listarParceirosSemEmail(pCartasPAM.getIdSgmUsuario());
        tela = 1;
    }
    
    public void listarCartas() {
        listaCarta = cartasPAMService.listarCartas(pesquisa);            
    }
    
     public void listarCartasPorSituação(String pSituacao) {
        listaCarta = cartasPAMService.listarCartas(pSituacao);            
    }
    
    public void alterarCarta(Integer pTela) {
        try {    
        cartasPAM.setDtEdicao(new Date());
        cartasPAMService.alterarCarta(cartasPAM);
          Mensagem.saveMessage("Carta foi altera com sucesso.", "Sucesso");
        } catch (SQLException ex) {
            Mensagem.saveMessage("Ocorreu um erro inesperado ao tentar salvar está carta. \n Motivo:"+ ex.getMessage(),"");
            throw  new RuntimeException("Ocorreu um erro inesperado ao tentar salvar está carta. \n Motivo:"+ex.getMessage());
        }
        tela = pTela;
      
    }
    
   public void deletarCarta(Integer pTela) {
       CartasPamDAO cartasPamDAO = new CartasPamDAO();
       cartasPamDAO.deletarCarta(cartasPAM);
       tela = pTela;
       listarCartas();
       Mensagem.saveMessage("Carta deletada com sucesso.", "Sucesso");
   }
    public void mudarTela(Integer pTela) {
        tela = pTela;
    }
    
     public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("O arquivo ",
                event.getFile().getFileName() + " foi salvo!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(),
                    event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
     
      public void copyFile(String fileName, InputStream in) {
        try {
            String caminho = "C:\\temp\\PRODUCAO\\var\\webapp\\images\\";
            String nomeDoArquivo = String.valueOf(Math.random());
            OutputStream out = new FileOutputStream(new File(caminho+nomeDoArquivo+fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            cartasPAM.setPath(nomeDoArquivo+fileName);                
            foto = 1;
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

      public void listarParceirosComEmail() {
        try {
            listaParceiroComEmail = cartasPAMService.listarParceiroComEmail(cartasPAM.getIdSgmUsuario());
        } catch (SQLException ex) {
            Mensagem.saveMessage("Ocorreu um erro inesperado ao tentar listar os parceiros com email."+ ex.getMessage(), "");
            throw  new RuntimeException("Ocorreu um erro inesperado ao tentar listar os parceiros com email."+ex.getMessage());
        }
      }
    
      public void listarParceirosSemEmail(long pIdUsuario) {
        try {
            listaParceiroSemEmail = cartasPAMService.listarParceiroSemEmail(pIdUsuario);
        } catch (SQLException ex) {
            Mensagem.saveMessage("Ocorreu um erro inesperado ao tentar listar os parceiros sem email."+ ex.getMessage(), "");
            throw  new RuntimeException("Ocorreu um erro inesperado ao tentar listar os parceiros sem email."+ex.getMessage());
        }
      }
    
    //get set

    public Integer getTela() {
        return tela;
    }

    public void setTela(Integer tela) {
        this.tela = tela;
    }

    public CartasPAM getCartasPAM() {
        return cartasPAM;
    }

    public void setCartasPAM(CartasPAM cartasPAM) {
        this.cartasPAM = cartasPAM;
    }

    public List<CartasPAM> getListaCarta() {
        return listaCarta;
    }

    public void setListaCarta(List<CartasPAM> listaCarta) {
        this.listaCarta = listaCarta;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public CartasPAMService getCartasPAMService() {
        return cartasPAMService;
    }

    public void setCartasPAMService(CartasPAMService cartasPAMService) {
        this.cartasPAMService = cartasPAMService;
    }

    public Integer getFoto() {
        return foto;
    }

    public void setFoto(Integer foto) {
        this.foto = foto;
    }

    public List<Parceiro> getListaParceiroComEmail() {
        return listaParceiroComEmail;
    }

    public void setListaParceiroComEmail(List<Parceiro> listaParceiroComEmail) {
        this.listaParceiroComEmail = listaParceiroComEmail;
    }

    public List<Parceiro> getListaParceiroSemEmail() {
        return listaParceiroSemEmail;
    }

    public void setListaParceiroSemEmail(List<Parceiro> listaParceiroSemEmail) {
        this.listaParceiroSemEmail = listaParceiroSemEmail;
    }
    
    
    
}
