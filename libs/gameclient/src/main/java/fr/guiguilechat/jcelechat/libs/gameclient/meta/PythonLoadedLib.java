package fr.guiguilechat.jcelechat.libs.gameclient.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class PythonLoadedLib {

	private final String resourceName;
	private final ResourceMetaData loader;
	private final ResourceMetaData fsdbinary;

}
