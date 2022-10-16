package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.accessories.LegacyCurrency;
import fr.guiguilechat.jcelechat.model.sde.types.accessories.OutpostImprovements;
import fr.guiguilechat.jcelechat.model.sde.types.accessories.OutpostUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.accessories.PLEX;
import fr.guiguilechat.jcelechat.model.sde.types.accessories.Services;
import fr.guiguilechat.jcelechat.model.sde.types.accessories.SkillInjectors;

public abstract class Accessories
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Accessories.MetaCat METACAT = new Accessories.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Accessories> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Accessories>
    {

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
            return Arrays.asList(OutpostImprovements.METAGROUP, OutpostUpgrades.METAGROUP, LegacyCurrency.METAGROUP, Services.METAGROUP, SkillInjectors.METAGROUP, PLEX.METAGROUP);
        }
    }
}
