package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.lights.PointLights;

public abstract class Lights
    extends Item
{
    public final static Lights.MetaCat METACAT = new Lights.MetaCat();

    @Override
    public IMetaCategory<Lights> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Lights>
    {

        @Override
        public int getCategoryId() {
            return  54;
        }

        @Override
        public String getName() {
            return "Lights";
        }

        @Override
        public Collection<IMetaGroup<? extends Lights>> groups() {
            return Arrays.asList(PointLights.METAGROUP);
        }
    }
}
