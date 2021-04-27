package com.spring.rest.app.models;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")//autoriza entrada
@PostAuthorize("hasRole('ROLE_ADMIN') and 7 <8")//autoriza salida
public @interface BimbsSecurityRule {

}
