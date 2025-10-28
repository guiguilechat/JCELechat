package fr.guiguilechat.jcelechat.libs.spring.update.tools;

import java.io.IOException;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * Allow to deduce properties from a yaml configuration
 *
 * @see https://github.com/spring-projects/spring-framework/issues/18486#issuecomment-1209158520
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(encodedResource.getResource());

		return new PropertiesPropertySource(encodedResource.getResource().getFilename(), factory.getObject());
	}
}
