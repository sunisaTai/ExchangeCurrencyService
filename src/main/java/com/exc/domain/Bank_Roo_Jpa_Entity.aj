// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.exc.domain;

import com.exc.domain.Bank;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

privileged aspect Bank_Roo_Jpa_Entity {
    
    declare @type: Bank: @Entity;
    
    declare @type: Bank: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
}
