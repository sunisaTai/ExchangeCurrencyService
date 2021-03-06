package com.exc.domain;
import com.exc.base.BaseEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Bank extends BaseEntity {

    /**
     */
    private String bank_Name;

    /**
     */
    private String code;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    private Set<ExchangeCurrency> exchangeCurrency = new HashSet<ExchangeCurrency>();

    /**
     */
    private String image_name;
}
