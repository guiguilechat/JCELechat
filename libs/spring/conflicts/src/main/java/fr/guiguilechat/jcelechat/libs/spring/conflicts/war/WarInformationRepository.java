package fr.guiguilechat.jcelechat.libs.spring.conflicts.war;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityRepository;

public interface WarInformationRepository extends RemoteEntityRepository<WarInformation, Integer> {

	public WarInformation findTop1ByOrderByIdDesc();

	public WarInformation findTop1ByOrderByIdAsc();

}
