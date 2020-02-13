package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.AmarrEducation;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.BloodlineBonus;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.CaldariEducation;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.CareerBonus;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.GallenteEducation;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.MinmatarEducation;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.PhobiaHandicap;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.PhysicalBenefit;
import fr.guiguilechat.jcelechat.model.sde.types.bonus.PhysicalHandicap;

public abstract class Bonus
    extends EveType
{
    public static final Bonus.MetaCat METACAT = new Bonus.MetaCat();

    @Override
    public IMetaCategory<Bonus> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Bonus>
    {

        @Override
        public int getCategoryId() {
            return  14;
        }

        @Override
        public String getName() {
            return "Bonus";
        }

        @Override
        public Collection<IMetaGroup<? extends Bonus>> groups() {
            return Arrays.asList(BloodlineBonus.METAGROUP, PhysicalBenefit.METAGROUP, PhysicalHandicap.METAGROUP, PhobiaHandicap.METAGROUP, AmarrEducation.METAGROUP, CaldariEducation.METAGROUP, GallenteEducation.METAGROUP, MinmatarEducation.METAGROUP, CareerBonus.METAGROUP);
        }
    }
}
