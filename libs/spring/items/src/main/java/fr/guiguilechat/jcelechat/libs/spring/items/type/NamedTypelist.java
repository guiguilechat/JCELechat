package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

/**
 * a list of type ids with the name of the selection
 */
public record NamedTypelist(String name, List<Integer> typeIds) {
}