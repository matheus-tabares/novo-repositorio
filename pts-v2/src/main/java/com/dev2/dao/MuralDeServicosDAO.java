package com.dev2.dao;

import com.dev2.model.MensagemMural;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MuralDeServicosDAO {
    
    private final Session sessao;

    public MuralDeServicosDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void publicarSolicitacao(MensagemMural mensagemMural) {
        Transaction t = sessao.beginTransaction();
        sessao.save(mensagemMural);
        t.commit();
    }
    
    public ArrayList<MensagemMural> listarSolicitacoesPorCategoria() {
        return (ArrayList<MensagemMural>) sessao.createCriteria(MensagemMural.class)
                //.add(Restrictions.like("categoria.id", idCategoria))
                .list();
    }
}

