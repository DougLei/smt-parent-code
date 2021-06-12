package com.smt.parent.code.filters.license;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author DougLei
 */
@Component
@ConfigurationProperties(prefix="smt.parent.code.filter.license")
public class LicenseConfigurationProperties {
	private String publicKey; // 公钥

	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
