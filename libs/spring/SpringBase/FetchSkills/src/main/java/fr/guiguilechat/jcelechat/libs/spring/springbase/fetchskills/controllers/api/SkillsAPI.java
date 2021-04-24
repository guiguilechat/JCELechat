package fr.guiguilechat.jcelechat.libs.spring.springbase.fetchskills.controllers.api;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skills;

@RestController
@RequestMapping("api/skills")
public class SkillsAPI {

	@RequestMapping("active")
	public Map<Integer, Integer> getActive(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OAuth2User user) {
		String accessToken = client.getAccessToken().getTokenValue();
		int charID = user.getAttribute("CharacterID");
		RestTemplate rs = new RestTemplate();
		rs.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + accessToken);
			return execution.execute(request, body);
		});
		String skillsURL = "https://esi.evetech.net/v4/characters/{character_id}/skills/".replace("{character_id}",
				"" + charID);
		R_get_characters_character_id_skills skills = rs.getForObject(skillsURL,
				R_get_characters_character_id_skills.class);
		return Stream.of(skills.skills).collect(Collectors.toMap(s -> s.skill_id, s -> s.active_skill_level));
	}

	@RequestMapping("trained")
	public Map<Integer, Integer> getTrained(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OAuth2User user) {
		String accessToken = client.getAccessToken().getTokenValue();
		int charID = user.getAttribute("CharacterID");
		RestTemplate rs = new RestTemplate();
		rs.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + accessToken);
			return execution.execute(request, body);
		});
		String skillsURL = "https://esi.evetech.net/v4/characters/{character_id}/skills/".replace("{character_id}",
				"" + charID);
		R_get_characters_character_id_skills skills = rs.getForObject(skillsURL,
				R_get_characters_character_id_skills.class);
		return Stream.of(skills.skills).collect(Collectors.toMap(s -> s.skill_id, s -> s.trained_skill_level));
	}

}
