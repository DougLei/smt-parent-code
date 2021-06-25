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
	private String licenseFilepath; // 授权文件的绝对路径; 可不配置, 默认在当前项目的资源文件夹下寻找(.license)后缀结尾的文件
	private String publicKey; // 公钥

	public String getLicenseFilepath() {
		return licenseFilepath;
	}
	public void setLicenseFilepath(String licenseFilepath) {
		this.licenseFilepath = licenseFilepath;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
