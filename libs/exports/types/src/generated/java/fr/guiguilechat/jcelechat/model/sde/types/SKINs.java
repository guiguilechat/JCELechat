package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.skins.Max1YearSKIN;
import fr.guiguilechat.jcelechat.model.sde.types.skins.Max30DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.types.skins.Max7DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.types.skins.Max90DaySKIN;
import fr.guiguilechat.jcelechat.model.sde.types.skins.PermanentSKIN;
import fr.guiguilechat.jcelechat.model.sde.types.skins.VolatileSKIN;

public abstract class SKINs
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final SKINs.MetaCat METACAT = new SKINs.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
