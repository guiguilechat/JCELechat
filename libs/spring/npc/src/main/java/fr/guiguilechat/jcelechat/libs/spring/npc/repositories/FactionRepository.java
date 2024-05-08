package fr.guiguilechat.jcelechat.libs.spring.npc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.Faction;

public interface FactionRepository extends JpaRepository<Faction, Integer> {

}
