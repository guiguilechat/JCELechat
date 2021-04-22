package fr.guiguilechat.jcelechat.libs.spring.springbase.fetchskills.controllers.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/skills")
public class SkillsAPI {

	@RequestMapping("all")
	public Map<Integer, Integer> getAll() {
		return Collections.emptyMap();
	}

}
