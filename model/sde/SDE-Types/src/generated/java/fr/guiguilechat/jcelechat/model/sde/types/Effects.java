package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.effects.AnimatedLights;
import fr.guiguilechat.jcelechat.model.sde.types.effects.LensFlares;
import fr.guiguilechat.jcelechat.model.sde.types.effects.ParticleSystems;

public abstract class Effects
    extends EveType
{
    public static final Effects.MetaCat METACAT = new Effects.MetaCat();

    @Override
    public IMetaCategory<Effects> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Effects>
    {

        @Override
        public int getCategoryId() {
            return  53;
        }

        @Override
        public String getName() {
            return "Effects";
        }

        @Override
        public Collection<IMetaGroup<? extends Effects>> groups() {
            return Arrays.asList(LensFlares.METAGROUP, ParticleSystems.METAGROUP, AnimatedLights.METAGROUP);
        }
    }
}
