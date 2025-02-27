package fr.guiguilechat.jcelechat.libs.spring.items.type;

public interface TypeSelector<T> {

	NamedTypelist list(TypeRepository repo, T filter);

}
