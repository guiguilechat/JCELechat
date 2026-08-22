package fr.guiguilechat.jcelechat.libs.sde.cache.loaders;

/** load a file name into a java type, usually a collection */
public interface FormattedLoader<LoadedType> {

	LoadedType load();

	String getArchiveFileName();

}
