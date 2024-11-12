package com.projeto.contratualize.contratualize.dao;
import com.projeto.contratualize.contratualize.model.Contract;
import java.util.Collections;
import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Gerencia as operações de banco de dados para a entidade Contract
 * @author gabri
 */

public class ContractDAO extends GenericDAO<Contract> {
    private static final String[] VALID_EXTENSIONS = {".pdf", ".jpg", ".jpeg", ".png"};
    
    public ContractDAO() {
        super(Contract.class);
    }
   
    //Verificar se tem link duplicado
    public boolean contractLinkExists(String contractLink) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Contract c WHERE c.contractLink = :contractLink", Long.class);
        query.setParameter("contractLink", contractLink);
        long count = query.getSingleResult();
        em.close();
        return count > 0;
    }
    
    //Verificar se o link é uma URL
    private boolean isValidUrl(String contractLink) {
        return contractLink.startsWith("http://") || contractLink.startsWith("https://");
    }
    
    //Verificar se o link é de fato um documento
    private boolean isValidExtension(String contractLink) {
        for(String extension : VALID_EXTENSIONS) {
            if (contractLink.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
     
    @Override
public void insert(Contract contract) {
    if (contractLinkExists(contract.getContractLink())) {
        throw new IllegalArgumentException("Este link já existe em nossa base de dados!");
    }
    if (!isValidUrl(contract.getContractLink())) {
        throw new IllegalArgumentException("O link deve ser uma URL válida!");
    }
    if (!isValidExtension(contract.getContractLink())) {
        throw new IllegalArgumentException("Contrato Inválido. Deve ser um dos tipos de arquivos: .pdf, .jpg, .jpeg, .png");
    }
    
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        em.persist(contract);
        em.getTransaction().commit();
        
    } catch(Exception e) {
        if(em.getTransaction().isActive()){
            em.getTransaction().rollback();
        }
        throw e;
        
    } finally {
        em.close();
    }
}

    
    public void delete(Contract contract){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Contract contractToRemove = em.find(Contract.class, contract.getId());
            if(contractToRemove !=null){
                em.remove(contractToRemove);
            } else {
                throw new IllegalArgumentException("Contrato não encontrado.");
            }
            em.getTransaction().commit();
            
        } catch(Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally{
            em.close();
        }
    }
    
    
    public List<Contract> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Contract> query = em.createQuery("SELECT c FROM Contract c", Contract.class);
        List<Contract> contracts = query.getResultList();
        em.close();
        return contracts;
    }
    
    public List<Contract> findAllWithClients() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT c FROM Contract c", Contract.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Adicione logs ou manipulação de erro aqui
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
    //para o o allUserScreen
    public List<Object[]> findClientNamesWithContractCount(){
        EntityManager em= emf.createEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT c.clientName, COUNT(c) FROM Contract c GROUP BY" +
                    " c.clientName", Object[].class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    
    public List<Contract> findContractsByClientName(String clientName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT c FROM Contract c WHERE c.clientName = :clientName", Contract.class);
            query.setParameter("clientName", clientName);
            return query.getResultList();
        } catch(NoResultException e) {
            return null;
        } finally {
            em.close();

        }
       
    } 
    
    
    
       
}
