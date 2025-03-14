package fr.guiguilechat.jcelechat.libs.spring.connect;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.RestClientAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.connect")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.connect")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.connect")
@PropertySource("classpath:esiconnect.properties")
public class ConnectConfiguration {

	/**
	 * register a responseclient to convert http params to oauth2 token, that also
	 * stores the correct scopes and refresh token
	 */
	@Bean
	OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {

		RestClientAuthorizationCodeTokenResponseClient accessTokenResponseClient = new RestClientAuthorizationCodeTokenResponseClient();

		OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
		tokenResponseHttpMessageConverter.setAccessTokenResponseConverter(new CustomTokenResponseConverter());

		RestTemplate restTemplate = new RestTemplate(Arrays.asList(
				new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
		accessTokenResponseClient.setRestClient(RestClient.create(restTemplate));

		return accessTokenResponseClient;
	}

}
