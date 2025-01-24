package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history;

import java.math.BigDecimal;

public record PriceVolume(BigDecimal price, BigDecimal volumeAbove, BigDecimal volumeBelow) {
}