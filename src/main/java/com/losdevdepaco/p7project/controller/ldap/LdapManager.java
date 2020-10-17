package com.losdevdepaco.p7project.controller.ldap;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

@Service
public class LdapManager {
		
	@Value("${ldap.url}")
	private String ldapUrl;
	
	@Value("${ldap.partitionSuffix}")
	private String ldapPSuffix;
	
	@Value("${ldap.principal}")
	private String ldapPrincipal;
	
	@Value("${ldap.password}")
	private String ldapPassword;
	
	private LdapContextSource contextInstance;
	private LdapTemplate templateInstance;
	
	//creacion del contexto LDAP con los datos de nuestro servidor
	@Bean
	public LdapContextSource contextSource() {
		if(this.contextInstance == null) {
			LdapContextSource contextSource = new LdapContextSource();
		    
		    contextSource.setUrl(this.ldapUrl);
		    contextSource.setBase(this.ldapPSuffix);
		    contextSource.setUserDn(this.ldapPrincipal);
		    contextSource.setPassword(this.ldapPassword);
		    
		    this.contextInstance = contextSource;
		}
	    	    
	    return this.contextInstance;
	}
	
	@Bean
	public LdapTemplate ldapTemplate() {
		if(this.templateInstance== null) {
			this.templateInstance = new LdapTemplate(contextSource());
		}
	    return this.templateInstance;
	}
	
	private String buildFullCn(String username) {
		String result = "cn=\"" + username + "\",ou=Users," + this.ldapPSuffix;
		System.out.println("Full cn created: " + result);
		return result;
	}
		//comprobar si existen los datos en el servidor
	public boolean authenticate(String username, String password) {
	    DirContext result = null;
	    
	    try {
			result = this.contextSource().getContext(this.buildFullCn(username), password);
		} catch (Exception e) {
			System.out.println("Error getting the context from LDAP.");
			e.printStackTrace();
		} finally {
			LdapUtils.closeContext(result);
		}
	    return result != null;
		
	}
	
}
