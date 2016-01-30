// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.exc.domain;

import com.exc.domain.DateForExchange;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DateForExchange_Roo_Jpa_ActiveRecord {
    
    public static final List<String> DateForExchange.fieldNames4OrderClauseFilter = java.util.Arrays.asList("datePerExchange", "exchangeCurrencySet");
    
    public static long DateForExchange.countDateForExchanges() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DateForExchange o", Long.class).getSingleResult();
    }
    
    public static List<DateForExchange> DateForExchange.findAllDateForExchanges() {
        return entityManager().createQuery("SELECT o FROM DateForExchange o", DateForExchange.class).getResultList();
    }
    
    public static List<DateForExchange> DateForExchange.findAllDateForExchanges(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM DateForExchange o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, DateForExchange.class).getResultList();
    }
    
    public static DateForExchange DateForExchange.findDateForExchange(Long id) {
        if (id == null) return null;
        return entityManager().find(DateForExchange.class, id);
    }
    
    public static List<DateForExchange> DateForExchange.findDateForExchangeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DateForExchange o", DateForExchange.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<DateForExchange> DateForExchange.findDateForExchangeEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM DateForExchange o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, DateForExchange.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public DateForExchange DateForExchange.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DateForExchange merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
