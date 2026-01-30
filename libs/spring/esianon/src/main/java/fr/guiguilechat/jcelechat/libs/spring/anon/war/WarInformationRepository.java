package fr.guiguilechat.jcelechat.libs.spring.anon.war;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityRepository;

public interface WarInformationRepository extends RemoteNumberEntityRepository<WarInformation, Integer> {

	public WarInformation findTop1ByOrderByIdDesc();

	public WarInformation findTop1ByOrderByIdAsc();

}
