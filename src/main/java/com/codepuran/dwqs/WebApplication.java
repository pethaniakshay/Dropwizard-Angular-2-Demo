
package com.codepuran.dwqs;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import com.codepuran.dwqs.health.TemplateHealthCheck;
import com.codepuran.dwqs.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebApplication extends Application<WebConfiguration> {
	public static void main(String[] args) throws Exception {
		new WebApplication().run(args);
	}
	
	@Override
	public String getName() {
		return "Dropwizard Quick Start";
	}

	@Override
	public void initialize(Bootstrap<WebConfiguration> bootstrap) {		
		bootstrap.addBundle(new ConfiguredAssetsBundle("/webapp", "/", "index.html"));
	}

	@Override
	public void run(WebConfiguration configuration, Environment environment) throws Exception {
		ApplicationContext.init(configuration);
		
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(),
				configuration.getDefaultName(),
				configuration.getEnviroment()
				);
		environment.jersey().register(resource);
		
		environment.healthChecks().register("template", new TemplateHealthCheck());
		environment.jersey().register(MultiPartFeature.class);

				
		environment.jersey().register(RolesAllowedDynamicFeature.class);
	}
}
