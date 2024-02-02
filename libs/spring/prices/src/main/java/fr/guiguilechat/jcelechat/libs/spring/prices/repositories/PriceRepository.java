package fr.guiguilechat.jcelechat.libs.spring.prices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.prices.model.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {

}
