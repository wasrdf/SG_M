package Usuario;

import CentroCusto.CentroCusto;
import CentroCusto.CentroCustoDAO;
import CentroCustoMissionario.CentroCustoMissionario;
import CentroCustoMissionario.CentroCustoMissionarioService;
import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import Funcionario.Funcionario;
import Funcionario.FuncionarioDAO;
import PessoaConexus.PessoaConexus;
import PessoaConexus.PessoaConexusDAO;
import Produto.Produto;
import Produto.ProdutoDAO;
import UsuarioProduto.UsuarioProduto;
import UsuarioProduto.UsuarioProdutoService;
import Util.Mensagem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController {

    Usuario usuario = new Usuario();
    Integer tela = 0;
    String pesquisa = "";
    UsuarioService usuarioService = new UsuarioService();
    List<Usuario> listaUsuario = new ArrayList<Usuario>();
    List<Produto> listaProduto = new ArrayList<Produto>();
    List<CentroCusto> listaCentroCusto = new ArrayList<CentroCusto>();
    List<PessoaConexus> listaPessoaConexus = new ArrayList<PessoaConexus>();
    String pesquisaPessoa = "";
    List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
    String pesquisaFuncionario = "";
    String pesquisaProduto = "";
    List<Produto> produtosSelecionados = new ArrayList<Produto>();
    List<CentroCusto> centroCustoSelecionados = new ArrayList<CentroCusto>();

    UsuarioProduto usuarioProduto = new UsuarioProduto();
    CentroCustoMissionario centroCustoMissionario = new CentroCustoMissionario();
    UsuarioProdutoService usuarioProdutoService = new UsuarioProdutoService();
    List<UsuarioProduto> listaUsuarioProduto = new ArrayList<UsuarioProduto>();
    CentroCustoMissionarioService centroCustoMissionarioService = new CentroCustoMissionarioService();
    boolean iniciar = false;

    public void iniciar() {
        if (!iniciar) {
            //carrega a lista de produtos
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ClausulaSQL.ClausulaWhere condicao = new ClausulaWhere();
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nmproduto", GeneroCondicaoWhere.contem, pesquisaProduto, TipoCondicaoWhere.Texto);
            listaProduto = produtoDAO.listarProdutos(condicao);

            //carrega a lista de centro de custo
            CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
            ClausulaSQL.ClausulaWhere condicao2 = new ClausulaWhere();
            condicao2.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nmCentroDeCusto", GeneroCondicaoWhere.contem, "", TipoCondicaoWhere.Texto);
            listaCentroCusto = centroCustoDAO.listarCentroCustos(condicao2);

            iniciar = true;
        }
    }

    public void limparCamposDePesquisa() {
        pesquisa = "";
        pesquisaFuncionario = "";
        pesquisaPessoa = "";
        pesquisaProduto = "";
    }

    public void limparCampos() {
        produtosSelecionados = new ArrayList<Produto>();
        centroCustoSelecionados = new ArrayList<CentroCusto>();
        usuario = new Usuario();
        listaUsuario = new ArrayList<Usuario>();
        usuarioProduto = new UsuarioProduto();
        centroCustoMissionario = new CentroCustoMissionario();
    }

    public void removerProduto(Produto pProduto) {
        produtosSelecionados.remove(pProduto);
        if (usuario.getIdSgmUsuario() != 0) {
            usuarioProdutoService.deletarUsuarioProduto(usuario.getIdSgmUsuario(), pProduto.getIdProduto());
        }

    }

    public void removerCentroCusto(CentroCusto pCentroCusto) {
        System.out.println("Usuário:" + usuario.getIdSgmUsuario() + "Centro de custo:" + pCentroCusto.getIdCentroCusto());
        centroCustoSelecionados.remove(pCentroCusto);
        System.out.println("Tamanho da lista:" + centroCustoSelecionados.size());
        if (usuario.getIdSgmUsuario() != 0) {
            centroCustoMissionarioService.deletarCentroCustoMissionario(usuario.getIdSgmUsuario(), pCentroCusto.getIdCentroCusto());
        }

    }

    public void adicionarProduto() {
        //verifica se o usuário selecionou um produto na lista se sim ele adicionar na lista.
        if (!usuarioProduto.getIdProduto().equals("")) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ClausulaWhere condicao = new ClausulaWhere();
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "idproduto", GeneroCondicaoWhere.igual, usuarioProduto.getIdProduto(), TipoCondicaoWhere.Texto);
            produtosSelecionados.add(produtoDAO.listarProdutoPorId(condicao));
        }
    }

    public void adicionarCentroCusto() {
        //verifica se o usuário selecionou um produto na lista se sim ele adicionar na lista.
        if (!centroCustoMissionario.getIdCusto().equals("")) {
            CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
            ClausulaWhere condicao = new ClausulaWhere();
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "IdCentroDeCusto", GeneroCondicaoWhere.igual, centroCustoMissionario.getIdCusto(), TipoCondicaoWhere.Texto);
            centroCustoSelecionados.add(centroCustoDAO.listarCentroCustoPorId(condicao));
        }
    }

    public void listarPessoaConexus() {
        PessoaConexusDAO pessoaConexus = new PessoaConexusDAO();
        ClausulaSQL.ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nmpessoa", GeneroCondicaoWhere.contem, pesquisaPessoa, TipoCondicaoWhere.Texto);
        listaPessoaConexus = pessoaConexus.listarPessoaConexus(condicao);

    }

    public void listarFuncionario() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClausulaSQL.ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nmfuncionario", GeneroCondicaoWhere.contem, pesquisaFuncionario, TipoCondicaoWhere.Texto);
        listaFuncionario = funcionarioDAO.listarFuncionarios(condicao);

    }

    public void selecionarFuncionario() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClausulaSQL.ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "idfuncionario", GeneroCondicaoWhere.igual, usuario.getIdFuncionarioFP(), TipoCondicaoWhere.Texto);
        List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios(condicao);
        if (!funcionarios.isEmpty()) {
            usuario.setIdFilial(funcionarios.get(0).getFilial());
        }

    }

    public void popularListaPessoaFuncionario() {
        listarFuncionario();
        listarPessoaConexus();
    }

    public void salvar(Integer pTela) {
        System.out.println("Tamanho da lista:" + centroCustoSelecionados.size());
        tela = pTela;
        usuario = usuarioService.salvarUsuario(usuario);
        for (Produto produto : produtosSelecionados) {
            usuarioProdutoService.deletarUsuarioProduto(usuario.getIdSgmUsuario(), produto.getIdProduto());
            usuarioProduto = new UsuarioProduto();
            usuarioProduto.setIdSgmUsuario(usuario.getIdSgmUsuario());
            usuarioProduto.setIdProduto(produto.getIdProduto());
            usuarioProdutoService.salvarUsuarioProduto(usuarioProduto);
        }

        for (CentroCusto cc : centroCustoSelecionados) {
            centroCustoMissionarioService.deletarCentroCustoMissionario(usuario.getIdSgmUsuario(), cc.getIdCentroCusto());
            centroCustoMissionario = new CentroCustoMissionario();
            centroCustoMissionario.setIdSgmUsuario(usuario.getIdSgmUsuario());
            centroCustoMissionario.setIdCusto(cc.getIdCentroCusto());
            centroCustoMissionarioService.salvarCentroCustoMissionario(centroCustoMissionario);
        }

        if (usuario.getIdSgmUsuario() != 0) {
            Mensagem.saveMessage("Usuário salvo com sucesso!", "Sucesso");
            limparCampos();
            tela = pTela;
        } else {
            Mensagem.saveMessage("Ocorreu um erro ao tentar inserir este usuário!", "");
        }
    }

    public void deletar(Integer pTela) {
        try {
            usuarioProdutoService.deletarUsuarioProdutoPorIdUsuario(usuario.getIdSgmUsuario());
            centroCustoMissionarioService.deletarCentroCustoMissionarioPorIdUsuario(usuario.getIdSgmUsuario());
            usuarioService.deletarUsuario(usuario);
            limparCampos();
            Mensagem.saveMessage("Usuário deletado com sucesso.", "Sucesso");
            tela = pTela;
        } catch (Exception e) {
            Mensagem.saveMessage("Usuário não foi encontrado." + e.getMessage(), "");
            throw new RuntimeException("Usuário não foi encontrado" + e.getMessage());
        }

    }

    public void novo() {
        tela = 1;
        limparCampos();
        limparCamposDePesquisa();
        iniciar();
    }

    public void selecionar(Usuario p) {
        produtosSelecionados = new ArrayList<Produto>();
        centroCustoSelecionados = new ArrayList<CentroCusto>();
        this.usuario = p;
        iniciar();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<UsuarioProduto> lstUsuarioProdutos = usuarioProdutoService.listarUsuarioProdutoPorId(p.getIdSgmUsuario());
        for (UsuarioProduto up : lstUsuarioProdutos) {
            ClausulaWhere condicao = new ClausulaWhere();
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "idproduto", GeneroCondicaoWhere.igual, up.getIdProduto(), TipoCondicaoWhere.Texto);
            produtosSelecionados.add(produtoDAO.listarProdutoPorId(condicao));
        }

        CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
        List<CentroCustoMissionario> listaCentroCustoMissionario = centroCustoMissionarioService.listarCentroCustoMissionarioPorId(p.getIdSgmUsuario());
        for (CentroCustoMissionario ccm : listaCentroCustoMissionario) {
            ClausulaWhere condicao = new ClausulaWhere();
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "idCentroDeCusto", GeneroCondicaoWhere.igual, ccm.getIdCusto(), TipoCondicaoWhere.Texto);
            centroCustoSelecionados.add(centroCustoDAO.listarCentroCustoPorId(condicao));
        }

        //bind do selectonemenu pessoa(conexus)
        PessoaConexusDAO pessoaConexusDAO = new PessoaConexusDAO();
        ClausulaWhere pessoaConexusCondicao = new ClausulaWhere();
        pessoaConexusCondicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "idpessoa", GeneroCondicaoWhere.igual, p.getIdPessoa(), TipoCondicaoWhere.Texto);
        listaPessoaConexus = pessoaConexusDAO.listarPessoaConexus(pessoaConexusCondicao);

        //bind do selectonemenu funcionario(pack)
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ClausulaWhere funcionarioCondicao = new ClausulaWhere();
        funcionarioCondicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "idfuncionario", GeneroCondicaoWhere.igual, p.getIdFuncionarioFP(), TipoCondicaoWhere.Texto);
        listaFuncionario = funcionarioDAO.listarFuncionarios(funcionarioCondicao);

        tela = 1;
        System.out.println(">>>Categoria" + usuario.getSituacao());
    }

    public void mudarTela(Integer pTela) {
        tela = pTela;
    }

    public void sair() {
        System.out.println("Saindo");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        s.invalidate();
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/login/login.jsf");

        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarUsuarios() {
        listaUsuario = usuarioService.listarUsuario(pesquisa);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Integer getTela() {
        return tela;
    }

    public void setTela(Integer tela) {
        this.tela = tela;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public List<PessoaConexus> getListaPessoaConexus() {
        return listaPessoaConexus;
    }

    public void setListaPessoaConexus(List<PessoaConexus> listaPessoaConexus) {
        this.listaPessoaConexus = listaPessoaConexus;
    }

    public String getPesquisaPessoa() {
        return pesquisaPessoa;
    }

    public void setPesquisaPessoa(String pesquisaPessoa) {
        this.pesquisaPessoa = pesquisaPessoa;
    }

    public List<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public void setListaFuncionario(List<Funcionario> listaFuncionario) {
        this.listaFuncionario = listaFuncionario;
    }

    public String getPesquisaFuncionario() {
        return pesquisaFuncionario;
    }

    public void setPesquisaFuncionario(String pesquisaFuncionario) {
        this.pesquisaFuncionario = pesquisaFuncionario;
    }

    public String getPesquisaProduto() {
        return pesquisaProduto;
    }

    public void setPesquisaProduto(String pesquisaProduto) {
        this.pesquisaProduto = pesquisaProduto;
    }

    public List<Produto> getProdutosSelecionados() {
        return produtosSelecionados;
    }

    public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
        this.produtosSelecionados = produtosSelecionados;
    }

    public UsuarioProduto getUsuarioProduto() {
        return usuarioProduto;
    }

    public void setUsuarioProduto(UsuarioProduto usuarioProduto) {
        this.usuarioProduto = usuarioProduto;
    }

    public UsuarioProdutoService getUsuarioProdutoService() {
        return usuarioProdutoService;
    }

    public void setUsuarioProdutoService(UsuarioProdutoService usuarioProdutoService) {
        this.usuarioProdutoService = usuarioProdutoService;
    }

    public List<UsuarioProduto> getListaUsuarioProduto() {
        return listaUsuarioProduto;
    }

    public void setListaUsuarioProduto(List<UsuarioProduto> listaUsuarioProduto) {
        this.listaUsuarioProduto = listaUsuarioProduto;
    }

    public List<CentroCusto> getListaCentroCusto() {
        return listaCentroCusto;
    }

    public void setListaCentroCusto(List<CentroCusto> listaCentroCusto) {
        this.listaCentroCusto = listaCentroCusto;
    }

    public List<CentroCusto> getCentroCustoSelecionados() {
        return centroCustoSelecionados;
    }

    public void setCentroCustoSelecionados(List<CentroCusto> centroCustoSelecionados) {
        this.centroCustoSelecionados = centroCustoSelecionados;
    }

    public CentroCustoMissionario getCentroCustoMissionario() {
        return centroCustoMissionario;
    }

    public void setCentroCustoMissionario(CentroCustoMissionario centroCustoMissionario) {
        this.centroCustoMissionario = centroCustoMissionario;
    }

}
