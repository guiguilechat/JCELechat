package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.skins.Max1YearSKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.skins.Max30DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.skins.Max7DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.skins.Max90DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.skins.PermanentSKIN;
import fr.guiguilechat.jcelechat.model.sde.items.types.skins.VolatileSKIN;

public abstract class SKINs
    extends Item
{
    public static final SKINs.MetaCat METACAT = new SKINs.MetaCat();

    @Override
    public IMetaCategory<SKINs> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<SKINs>
    {

        @Override
        public int getCategoryId() {
            return  91;
        }

        @Override
        public String getName() {
            return "SKINs";
        }

        @Override
        public Collection<IMetaGroup<? extends SKINs>> groups() {
            return Arrays.asList(PermanentSKIN.METAGROUP, VolatileSKIN.METAGROUP, Max7DaySKIN.METAGROUP, Max30DaySKIN.METAGROUP, Max90DaySKIN.METAGROUP, Max1YearSKIN.METAGROUP);
        }
    }
}
