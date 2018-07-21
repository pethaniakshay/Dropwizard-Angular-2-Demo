package com.codepuran.dwqs;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.dhatim.dropwizard.jwt.cookie.authentication.JwtCookieAuthConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
public class WebConfiguration extends Configuration implements AssetsBundleConfiguration {

	@Valid
	@NotNull
	private JwtCookieAuthConfiguration jwtCookieAuth = new JwtCookieAuthConfiguration();

	/****** Responsible for assets folder outside of jar *****/
	@Valid
	@NotNull
	@JsonProperty("assets")
	private final AssetsConfiguration assets = new AssetsConfiguration();
	
	@Valid
	@NotNull
	@JsonProperty("configProperties")
	private final ConfigProperties configProperties = new ConfigProperties();
	
	
	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName = "Stranger";

	private String enviroment;

	@Override
	public AssetsConfiguration getAssetsConfiguration() {
		return assets;
	}

	public ConfigProperties getConfigProperties() {
		return configProperties;
	}
	
	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public String getEnviroment() {
		return enviroment;
	}

	@JsonProperty
	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
	}	
}
