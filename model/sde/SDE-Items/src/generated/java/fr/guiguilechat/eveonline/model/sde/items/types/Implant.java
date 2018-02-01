package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.Booster;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberArmor;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberBiology;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberElectronicSystems;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberEngineering;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberGunnery;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberLeadership;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberLearning;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberNavigation;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberProduction;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberResourceProcessing;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberScanning;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberScience;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberShields;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberSocial;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberTargeting;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.CyberXSpecials;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.Cyberimplant;
import fr.guiguilechat.eveonline.model.sde.items.types.implant.SpecialEditionImplant;

public abstract class Implant
    extends Item
{
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;

    @Override
    public int getCategoryId() {
        return  20;
    }

    @Override
    public Class<?> getCategory() {
        return Implant.class;
    }

    public static Map<String, ? extends Implant> loadCategory() {
        return Stream.of(CyberMissile.load(), CyberResourceProcessing.load(), CyberTargeting.load(), CyberElectronicSystems.load(), CyberScanning.load(), CyberArmor.load(), CyberProduction.load(), CyberShields.load(), SpecialEditionImplant.load(), CyberEngineering.load(), CyberSocial.load(), CyberScience.load(), CyberNavigation.load(), CyberXSpecials.load(), Cyberimplant.load(), CyberLearning.load(), CyberGunnery.load(), CyberBiology.load(), Booster.load(), CyberLeadership.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
