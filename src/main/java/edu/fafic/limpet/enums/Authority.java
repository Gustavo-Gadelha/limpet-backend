package edu.fafic.limpet.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Authority {
    ROLE_CLIENT;

    public GrantedAuthority toGrantedAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}
