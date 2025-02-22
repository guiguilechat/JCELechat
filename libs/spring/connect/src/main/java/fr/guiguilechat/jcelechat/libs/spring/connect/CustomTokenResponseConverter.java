package fr.guiguilechat.jcelechat.libs.spring.connect;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * converts a token reponse parameter in a tokenreponse, including
 * <ul>
 * <li>scopes into
 * {@link OAuth2AccessToken#getScopes()}</li>
 * <li>refresh token into
 * {@link OAuth2AccessTokenResponse#getAdditionalParameters()} under the key
 * {@link OAuth2ParameterNames#REFRESH_TOKEN}</li>
 * </ul>
 */
@Slf4j
public class CustomTokenResponseConverter implements
Converter<Map<String, Object>, OAuth2AccessTokenResponse> {

	@Override
	public OAuth2AccessTokenResponse convert(Map<String, Object> tokenResponseParameters) {
		try {
			String accessToken = (String) tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN);
			String refreshToken = (String) tokenResponseParameters.get(OAuth2ParameterNames.REFRESH_TOKEN);
			long expiresIn = ((Number) tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN)).longValue();

			Set<String> scopes = Collections.emptySet();
			if (tokenResponseParameters.containsKey(OAuth2ParameterNames.SCOPE)) {
				String scope = (String) tokenResponseParameters.get(OAuth2ParameterNames.SCOPE);
				scopes = Arrays.stream(StringUtils.delimitedListToStringArray(scope, " "))
						.collect(Collectors.toSet());
			}

			return OAuth2AccessTokenResponse.withToken(accessToken)
					.tokenType(OAuth2AccessToken.TokenType.BEARER)
					.expiresIn(expiresIn)
					.scopes(scopes)
					.refreshToken(refreshToken)
					.additionalParameters(Map.of(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken))
					.build();
		} catch (Exception e) {
			log.error("while converting parameters " + tokenResponseParameters, e);
			return null;
		}
	}
}