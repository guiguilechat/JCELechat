package fr.guiguilechat.jcelechat.libs.everaw.meta;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * an index of resources based on a remote file
 */
@RequiredArgsConstructor
public class EveIndex {

	@Getter
	private final String remotePath;

	@Getter(lazy = true, value = AccessLevel.PRIVATE)
	private final List<EveIndexLine> lines = ServerInfo.loadIndex(remotePath);

	@Getter(lazy = true, value = AccessLevel.PRIVATE)
	private final Map<String, EveIndexLine> resourceIndex = getLines().stream()
			.collect(Collectors.toMap(l -> removePrefix(l.resName()), l -> l));

	String removePrefix(String resName) {
		return resName.replaceAll("^app:/", "");
	}

	public Set<String> streamResourceNames() {
		return getResourceIndex().keySet();
	}

	public EveIndexLine getResourceLine(String resourceName) {
		return getResourceIndex().get(removePrefix(resourceName));
	}
}
