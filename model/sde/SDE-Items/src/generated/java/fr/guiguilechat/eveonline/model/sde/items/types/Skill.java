package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Armor;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.CorporationManagement;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Drones;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.ElectronicSystems;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Engineering;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.FakeSkills;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.FleetSupport;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Gunnery;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Missiles;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Navigation;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.NeuralEnhancement;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.PlanetManagement;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Production;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.ResourceProcessing;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Rigging;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Scanning;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Science;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Shields;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Social;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.SpaceshipCommand;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.StructureManagement;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Subsystems;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Targeting;
import fr.guiguilechat.eveonline.model.sde.items.types.skill.Trade;

public abstract class Skill
    extends Item
{
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SkillLevel;

    @Override
    public int getCategoryId() {
        return  16;
    }

    @Override
    public Class<?> getCategory() {
        return Skill.class;
    }

    public static Map<String, ? extends Skill> loadCategory() {
        return Stream.of(Missiles.load(), SpaceshipCommand.load(), StructureManagement.load(), NeuralEnhancement.load(), Subsystems.load(), FakeSkills.load(), ElectronicSystems.load(), Engineering.load(), Trade.load(), Targeting.load(), Drones.load(), ResourceProcessing.load(), PlanetManagement.load(), Production.load(), Rigging.load(), Social.load(), Navigation.load(), Gunnery.load(), Science.load(), Scanning.load(), CorporationManagement.load(), FleetSupport.load(), Armor.load(), Shields.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
