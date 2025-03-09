package fr.guiguilechat.jcelechat.libs.everaw.meta;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * an index of resources based on a remote file
 */
@RequiredArgsConstructor
public class ResourceIndex {

	/** path of the remote resource loaded to build that index */
	@Getter
	private final String remotePath;

	@Getter(lazy = true, value = AccessLevel.PRIVATE)
	private final List<ResourceMetaData> lines = ServerInfo.loadIndex(remotePath);

	@Getter(lazy = true)
	private final Map<String, ResourceMetaData> map = getLines().stream()
			.collect(Collectors.toMap(l -> l.resName(), l -> l));

	@Getter(lazy = true)
	private final List<String> indexNames = getMap().keySet().stream()
			.filter(name -> name.matches("^resfileindex\\.txt$"))
			.toList();
}
