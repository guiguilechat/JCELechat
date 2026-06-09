package fr.guiguilechat.jcelechat.libs.sde.cache.yaml;

public interface YamlLoader<LoadedType> {

	LoadedType load();

	String getArchiveFileName();

}
