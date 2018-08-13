package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.LegacyCurrency;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.OutpostImprovements;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.OutpostUpgrades;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.PLEX;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.Services;
import fr.guiguilechat.jcelechat.model.sde.items.types.accessories.SkillInjectors;

public abstract class Accessories
    extends Item
{
    public final static Accessories.MetaCat METACAT = new Accessories.MetaCat();

    @Override
    public int getCategoryId() {
        return  5;
    }

    @Override
    public MetaCategory<Accessories> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Accessories> loadCategory() {
        return Stream.of(LegacyCurrency.load(), OutpostImprovements.load(), OutpostUpgrades.load(), PLEX.load(), Services.load(), SkillInjectors.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Accessories>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Accessories> [] groups = new MetaGroup[] {OutpostImprovements.METAGROUP, OutpostUpgrades.METAGROUP, LegacyCurrency.METAGROUP, Services.METAGROUP, SkillInjectors.METAGROUP, PLEX.METAGROUP };

        @Override
        public String getName() {
            return "Accessories";
        }

        public Collection<MetaGroup<? extends Accessories>> groups() {
            return Arrays.asList(groups);
        }
    }
}
