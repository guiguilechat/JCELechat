package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Armor;
import fr.guiguilechat.jcelechat.model.sde.types.skill.CorporationManagement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Drones;
import fr.guiguilechat.jcelechat.model.sde.types.skill.ElectronicSystems;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Engineering;
import fr.guiguilechat.jcelechat.model.sde.types.skill.FleetSupport;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Gunnery;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Missiles;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Navigation;
import fr.guiguilechat.jcelechat.model.sde.types.skill.NeuralEnhancement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.PlanetManagement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Production;
import fr.guiguilechat.jcelechat.model.sde.types.skill.ResourceProcessing;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Rigging;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Scanning;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Science;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Shields;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Social;
import fr.guiguilechat.jcelechat.model.sde.types.skill.SpaceshipCommand;
import fr.guiguilechat.jcelechat.model.sde.types.skill.StructureManagement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Subsystems;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Targeting;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Trade;

public abstract class Skill
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacity;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double mass;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int primaryattribute;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double radius;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int secondaryattribute;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int skilllevel;
    /**
     * This attribute is a multiplier to the number of skill points required to train. Skill points required to train a skill = 250 * skillTimeConstant * sqrt(32)^(skillLevel - 1)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double skilltimeconstant;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, SkillTimeConstant.INSTANCE, PrimaryAttribute.INSTANCE, RequiredSkill1Level.INSTANCE, SecondaryAttribute.INSTANCE, RequiredSkill1 .INSTANCE, SkillLevel.INSTANCE })));
    public static final Skill.MetaCat METACAT = new Skill.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  4 :
            {
                return mass;
            }
            case  180 :
            {
                return primaryattribute;
            }
            case  162 :
            {
                return radius;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  181 :
            {
                return secondaryattribute;
            }
            case  280 :
            {
                return skilllevel;
            }
            case  275 :
            {
                return skilltimeconstant;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
