package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.AmarrEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.BloodlineBonus;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.CaldariEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.CareerBonus;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.GallenteEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.MinmatarEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.PhobiaHandicap;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.PhysicalBenefit;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.PhysicalHandicap;

public abstract class Bonus
    extends Item
{

    @Override
    public int getCategoryId() {
        return  14;
    }

    @Override
    public Class<?> getCategory() {
        return Bonus.class;
    }

    public static Map<String, ? extends Bonus> loadCategory() {
        return Stream.of(AmarrEducation.load(), BloodlineBonus.load(), CaldariEducation.load(), CareerBonus.load(), GallenteEducation.load(), MinmatarEducation.load(), PhobiaHandicap.load(), PhysicalBenefit.load(), PhysicalHandicap.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
