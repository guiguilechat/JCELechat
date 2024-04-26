package fr.guiguilechat.jcelechat.libs.spring.connect;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

public class CustomTokenResponseParametersConverter
		implements Converter<OAuth2AccessTokenResponse, Map<String, Object>> {

	private final DefaultOAuth2AccessTokenResponseMapConverter delegate = new DefaultOAuth2AccessTokenResponseMapConverter();
	@Override
	public Map<String, Object> convert(OAuth2AccessTokenResponse source) {
		System.err.println("received " + source);
		return delegate.convert(source);
	}

}
