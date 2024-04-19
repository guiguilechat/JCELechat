package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class Oauth2UserService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		return processOAuth2User(oAuth2UserRequest, oAuth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		System.out.println("user attributes : " + oAuth2User.getAttributes());
		System.out.println("access token is " + oAuth2UserRequest.getAccessToken().getTokenValue());
		System.out.println(
				"refresh token is : " + oAuth2UserRequest.getAdditionalParameters().get(OAuth2ParameterNames.REFRESH_TOKEN));
		System.out.println(
				"additional parameters are : " + oAuth2UserRequest.getAdditionalParameters());
		return oAuth2User;
	}
}
