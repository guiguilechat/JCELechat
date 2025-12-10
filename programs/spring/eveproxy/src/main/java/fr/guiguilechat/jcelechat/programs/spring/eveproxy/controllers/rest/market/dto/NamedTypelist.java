package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.dto;

import java.util.List;

/**
 * a list of type ids with the name of the selection
 */
public record NamedTypelist(String name, List<Integer> typeIds) {
}