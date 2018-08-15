package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Armor;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.CorporationManagement;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Drones;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.ElectronicSystems;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Engineering;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.FleetSupport;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Gunnery;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Missiles;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Navigation;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.NeuralEnhancement;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.PlanetManagement;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Production;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.ResourceProcessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Rigging;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Scanning;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Science;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Shields;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Social;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.SpaceshipCommand;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.StructureManagement;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Subsystems;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Targeting;
import fr.guiguilechat.jcelechat.model.sde.items.types.skill.Trade;

public abstract class Skill
    extends Item
{
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PrimaryAttribute;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SecondaryAttribute;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SkillLevel;
    /**
     * Time constant for skill training
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SkillTimeConstant;
    public final static Skill.MetaCat METACAT = new Skill.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  180 :
            {
                return PrimaryAttribute;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  181 :
            {
                return SecondaryAttribute;
            }
            case  280 :
            {
                return SkillLevel;
            }
            case  275 :
            {
                return SkillTimeConstant;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<Skill> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Skill>
    {

        @Override
        public int getCategoryId() {
            return  16;
        }

        @Override
        public String getName() {
            return "Skill";
        }

        @Override
        public Collection<IMetaGroup<? extends Skill>> groups() {
            return Arrays.asList(Gunnery.METAGROUP, Missiles.METAGROUP, SpaceshipCommand.METAGROUP, FleetSupport.METAGROUP, CorporationManagement.METAGROUP, Production.METAGROUP, Rigging.METAGROUP, Science.METAGROUP, ElectronicSystems.METAGROUP, Drones.METAGROUP, Trade.METAGROUP, Navigation.METAGROUP, Social.METAGROUP, Shields.METAGROUP, Armor.METAGROUP, Targeting.METAGROUP, Engineering.METAGROUP, Scanning.METAGROUP, ResourceProcessing.METAGROUP, NeuralEnhancement.METAGROUP, Subsystems.METAGROUP, PlanetManagement.METAGROUP, StructureManagement.METAGROUP);
        }
    }
}