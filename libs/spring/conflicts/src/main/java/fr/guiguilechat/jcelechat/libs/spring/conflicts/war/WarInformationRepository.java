package fr.guiguilechat.jcelechat.libs.spring.conflicts.war;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface WarInformationRepository extends IRemoteEntityRepository<WarInformation, Integer> {

	public WarInformation findTop1ByOrderByIdDesc();

	public WarInformation findTop1ByOrderByIdAsc();

}
