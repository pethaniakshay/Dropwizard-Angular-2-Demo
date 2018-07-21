package com.codepuran.dwqs.resources;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class HelloWorldResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;
	private final String enviroment;

	public HelloWorldResource(String template, String defaultName,String enviroment) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
		this.enviroment = enviroment;
	}

	@GET
	@Timed
	@Path("/say-hello")
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.orElse(defaultName));
		return new Saying(counter.incrementAndGet(), value);
	}

	@GET
	@Timed
	@Path("/enviroment")
	public String enviroment() {
		return enviroment;
	}
}