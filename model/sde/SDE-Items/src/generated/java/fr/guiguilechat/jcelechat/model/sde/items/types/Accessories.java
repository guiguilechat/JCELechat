package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
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
    public IMetaCategory<Accessories> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Accessories>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Accessories> [] groups = new IMetaGroup[] {OutpostImprovements.METAGROUP, OutpostUpgrades.METAGROUP, LegacyCurrency.METAGROUP, Services.METAGROUP, SkillInjectors.METAGROUP, PLEX.METAGROUP };

        @Override
        public int getCategoryId() {
            return  5;
        }

        @Override
        public String getName() {
            return "Accessories";
        }

        @Override
        public Collection<IMetaGroup<? extends Accessories>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Accessories> load() {
            HashMap<String, Accessories> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
