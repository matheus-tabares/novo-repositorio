package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.MuralDeServicosDAO;
import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Categoria;
import com.dev2.model.MensagemMural;
import com.dev2.model.Usuario;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public final class MuralDeServicos {
    private int idCategoriaSelecionada = 0;
    private ArrayList<MensagemMural> listaSolicitacoes = new ArrayList<>();
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private MensagemMural mensagemMural = new MensagemMural();
    private MuralDeServicosDAO muralDeServicosDAO = new MuralDeServicosDAO();
    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    public void publicarSolicitacao() {
        if(idCategoriaSelecionada == 0 || mensagemMural.getTitulo() == null 
                || mensagemMural.getDescricao() == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CAMPOS INVÁLIDOS", ""));
        } else {
            this.usuario = new Usuario();
            this.usuarioDAO = new UsuarioDAO();
            this.categoria = new Categoria();
            this.categoriaDAO = new CategoriaDAO();
            
            mensagemMural.setUsuario(loginBean.getUsuario());
            categoria = categoriaDAO.carregar(idCategoriaSelecionada);
            mensagemMural.setCategoria(categoria);
            muralDeServicosDAO.publicarSolicitacao(mensagemMural);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SOLICITAÇÃO PUBLICADA", ""));
            
            listarSolicitacoesPorCategoria();
            this.mensagemMural = new MensagemMural();
            this.idCategoriaSelecionada = 0;
        }
    }
    
    public ArrayList<MensagemMural> listarSolicitacoesPorCategoria() {
        try {
            this.muralDeServicosDAO = new MuralDeServicosDAO();
            this.listaSolicitacoes = muralDeServicosDAO.listarSolicitacoesPorCategoria();           
            
        }
        catch (Exception e) {
          System.out.println("ERRO AO LISTAR SOLICITAÇÕES: " + e.getMessage());
        }
        return listaSolicitacoes;
    }
    

    
    
    public MuralDeServicos() {
        listarSolicitacoesPorCategoria();
    }
    
    public int getIdCategoriaSelecionada() {
        return idCategoriaSelecionada;
    }

    public void setIdCategoriaSelecionada(int idCategoriaSelecionada) {
        this.idCategoriaSelecionada = idCategoriaSelecionada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public MensagemMural getMensagemMural() {
        return mensagemMural;
    }

    public void setMensagemMural(MensagemMural mensagemMural) {
        this.mensagemMural = mensagemMural;
    }

    public MuralDeServicosDAO getMuralDeServicosDAO() {
        return muralDeServicosDAO;
    }

    public void setMuralDeServicosDAO(MuralDeServicosDAO muralDeServicosDAO) {
        this.muralDeServicosDAO = muralDeServicosDAO;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public ArrayList<MensagemMural> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(ArrayList<MensagemMural> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
}
