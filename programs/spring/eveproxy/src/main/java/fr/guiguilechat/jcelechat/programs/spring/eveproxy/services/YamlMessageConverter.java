package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;


import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// https://rm3l.org/handling-yaml-in-spring-rest-apis/

@Component
public class YamlMessageConverter extends AbstractJackson2HttpMessageConverter {

	static ObjectMapper mapper() {
		ObjectMapper ret = new ObjectMapper(new YAMLFactory());
		ret.registerModule(new JavaTimeModule());
		return ret;
	}

	public static MediaType APPLICATION_YAML = new MediaType("application", "yaml", StandardCharsets.UTF_8);

	YamlMessageConverter() {
		super(mapper(),
				APPLICATION_YAML,
				new MediaType("text", "yaml", StandardCharsets.UTF_8),
				new MediaType("application", "*+yaml", StandardCharsets.UTF_8),
				new MediaType("text", "*+yaml", StandardCharsets.UTF_8),
				new MediaType("application", "yml", StandardCharsets.UTF_8),
				new MediaType("text", "yml", StandardCharsets.UTF_8),
				new MediaType("application", "*+yaml", StandardCharsets.UTF_8),
				new MediaType("text", "*+yaml", StandardCharsets.UTF_8));
	}

	@Override
	public void setObjectMapper(final ObjectMapper objectMapper) {
		if (!(objectMapper.getFactory() instanceof YAMLFactory)) {
			// Sanity check to make sure we do have an ObjectMapper configured
			// with YAML support, just in case someone attempts to call
			// this method elsewhere.
			throw new IllegalArgumentException(
					"ObjectMapper must be configured with an instance of YAMLFactory");
		}
		super.setObjectMapper(objectMapper);
	}
}
