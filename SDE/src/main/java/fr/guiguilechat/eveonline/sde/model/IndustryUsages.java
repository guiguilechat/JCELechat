package fr.guiguilechat.eveonline.sde.model;

import java.util.HashSet;

import fr.guiguilechat.eveonline.sde.fsd.Eblueprints;

/**
 * usages of an item in industry
 *
 */
public class IndustryUsages {

	public HashSet<Eblueprints> asMaterial = new HashSet<>();
	public HashSet<Eblueprints> asProduct = new HashSet<>();

}
