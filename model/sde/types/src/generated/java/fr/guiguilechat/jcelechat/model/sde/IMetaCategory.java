package fr.guiguilechat.jcelechat.model.sde;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.types.Abstrct;
import fr.guiguilechat.jcelechat.model.sde.types.Accessories;
import fr.guiguilechat.jcelechat.model.sde.types.AncientRelics;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;
import fr.guiguilechat.jcelechat.model.sde.types.Cells;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import fr.guiguilechat.jcelechat.model.sde.types.ColonyResources;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import fr.guiguilechat.jcelechat.model.sde.types.Deployable;
import fr.guiguilechat.jcelechat.model.sde.types.Drone;
import fr.guiguilechat.jcelechat.model.sde.types.Effects;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import fr.guiguilechat.jcelechat.model.sde.types.ExpertSystems;
import fr.guiguilechat.jcelechat.model.sde.types.Fighter;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import fr.guiguilechat.jcelechat.model.sde.types.InfrastructureUpgrades;
import fr.guiguilechat.jcelechat.model.sde.types.Lights;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import fr.guiguilechat.jcelechat.model.sde.types.Mining;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import fr.guiguilechat.jcelechat.model.sde.types.Orbitals;
import fr.guiguilechat.jcelechat.model.sde.types.Owner;
import fr.guiguilechat.jcelechat.model.sde.types.Personalization;
import fr.guiguilechat.jcelechat.model.sde.types.Placeables;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryIndustry;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryResources;
import fr.guiguilechat.jcelechat.model.sde.types.Reaction;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.guiguilechat.jcelechat.model.sde.types.SovereigntyStructures;
import fr.guiguilechat.jcelechat.model.sde.types.SpecialEditionAssets;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;
import fr.guiguilechat.jcelechat.model.sde.types.Station;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import fr.guiguilechat.jcelechat.model.sde.types.Subsystem;
import fr.guiguilechat.jcelechat.model.sde.types.System;
import fr.guiguilechat.jcelechat.model.sde.types.Trading;
import fr.guiguilechat.jcelechat.model.sde.types.WorldSpace;

public interface IMetaCategory<T extends EveType> {
    @SuppressWarnings("rawtypes")
    public static final IMetaCategory[] INSTANCES = new IMetaCategory[] {System.METACAT, Structure.METACAT, Owner.METACAT, StructureModule.METACAT, Celestial.METACAT, Station.METACAT, Material.METACAT, Accessories.METACAT, Personalization.METACAT, Ship.METACAT, Module.METACAT, Charge.METACAT, Blueprint.METACAT, Trading.METACAT, Entity.METACAT, Bonus.METACAT, Skill.METACAT, Commodity.METACAT, Drone.METACAT, Implant.METACAT, Deployable.METACAT, Starbase.METACAT, Fighter.METACAT, Reaction.METACAT, Asteroid.METACAT, WorldSpace.METACAT, SKINs.METACAT, Abstrct.METACAT, Apparel.METACAT, ColonyResources.METACAT, Subsystem.METACAT, AncientRelics.METACAT, Decryptors.METACAT, InfrastructureUpgrades.METACAT, SovereigntyStructures.METACAT, PlanetaryIndustry.METACAT, PlanetaryResources.METACAT, PlanetaryCommodities.METACAT, Orbitals.METACAT, Placeables.METACAT, ExpertSystems.METACAT, Infantry.METACAT, Effects.METACAT, Lights.METACAT, Mining.METACAT, Cells.METACAT, SpecialEditionAssets.METACAT };

    public int getCategoryId();

    public Collection<IMetaGroup<? extends T>> groups();

    public String getName();

    public default Map<Integer, T> load() {
        HashMap<Integer, T> ret = new HashMap<>();
        groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
        return ret;
    }
}
