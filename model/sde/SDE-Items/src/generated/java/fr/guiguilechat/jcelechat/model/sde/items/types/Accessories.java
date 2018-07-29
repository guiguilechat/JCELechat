package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.Clone;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.LegacyCurrency;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.OutpostImprovements;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.OutpostUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.PLEX;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.Services;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.SkillInjectors;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.Voucher;

public abstract class Accessories
    extends Item
{

    @Override
    public int getCategoryId() {
        return  5;
    }

    @Override
    public Class<?> getCategory() {
        return Accessories.class;
    }

    public static Map<String, ? extends Accessories> loadCategory() {
        return Stream.of(Clone.load(), LegacyCurrency.load(), OutpostImprovements.load(), OutpostUpgrades.load(), PLEX.load(), Services.load(), SkillInjectors.load(), Voucher.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
