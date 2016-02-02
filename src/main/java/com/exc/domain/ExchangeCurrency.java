package com.exc.domain;
import com.exc.base.BaseEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
@RooJson(deepSerialize = true)
public class ExchangeCurrency extends BaseEntity {

    /**
     */
    private Float exchange_Rates;

    /**
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank")
    private Bank bank;

    /**
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateForExchange")
    private DateForExchange dateForExchange;

    /**
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency")
    private Currency currency;

    /**
     */
    private String type_code;

    /**
     */
    private String type_name;
}
