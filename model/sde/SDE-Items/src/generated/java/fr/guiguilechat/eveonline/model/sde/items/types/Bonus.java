package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.AmarrEducation;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.BloodlineBonus;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.CaldariEducation;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.CareerBonus;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.GallenteEducation;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.MinmatarEducation;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.PhobiaHandicap;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.PhysicalBenefit;
import fr.guiguilechat.eveonline.model.sde.items.types.bonus.PhysicalHandicap;

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
        return Stream.of(PhysicalBenefit.load(), BloodlineBonus.load(), GallenteEducation.load(), CareerBonus.load(), PhysicalHandicap.load(), CaldariEducation.load(), PhobiaHandicap.load(), AmarrEducation.load(), MinmatarEducation.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
