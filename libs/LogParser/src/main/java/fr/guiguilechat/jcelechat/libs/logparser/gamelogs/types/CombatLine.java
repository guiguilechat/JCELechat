package fr.guiguilechat.jcelechat.libs.logparser.gamelogs.types;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.guiguilechat.jcelechat.libs.logparser.gamelogs.LogLine;

public class CombatLine extends LogLine {

	public static enum SUBTYPE {
		MISS, HIT, POINT, NOS, NEUT
	}

	public CombatLine(ZonedDateTime time, LOGTYPE type, String details) {
		super(time, type, details);
	}

	private transient SUBTYPE subtype = null;

	public SUBTYPE subtype() {
		if (subtype == null) {
			parseDetails();
		}
		return subtype;
	}

	private transient String source = null;

	public String source() {
		if (subtype == null) {
			parseDetails();
		}
		return source;
	}

	private transient String target = null;

	public String target() {
		if (subtype == null) {
			parseDetails();
		}
		return target;
	}

	private transient String charge = null;

	public String charge() {
		if (subtype == null) {
			parseDetails();
		}
		return charge;
	}

	private transient String quality = null;

	public String quality() {
		if (subtype == null) {
			parseDetails();
		}
		return quality;
	}

	private transient int quantity = 0;

	public int quantity() {
		if (subtype == null) {
			parseDetails();
		}
		return quantity;
	}

	private transient boolean scramble = false;

	public boolean scramble() {
		if (subtype == null) {
			parseDetails();
		}
		return scramble;
	}

	static final Pattern MISSESYOU_PAT = Pattern.compile("(.*) misses you completely");
	static final Pattern HITYOUCHARGE_PAT = Pattern.compile(".*?<b>([0-9]+)</b>.*>from<.*>(.*?)</b>.* - (.*?) - (.*)");
	static final Pattern HITYOU_PAT = Pattern.compile(".*?<b>([0-9]+)</b>.*>from<.*>(.*?)</b>.* - (.*?)");
	static final Pattern YOUMISS_PAT = Pattern.compile("Your .* misses (.*?) completely - (.*)");
	static final Pattern YOUHITCHARGE_PAT = Pattern.compile(".*?<b>([0-9]+)</b>.*>to<.*>(.*?)</b>.* - (.*?) - (.*)");
	static final Pattern YOUHIT_PAT = Pattern.compile(".*?<b>([0-9]+)</b>.*>to<.*>(.*?)</b>.* - (.*?)");
	static final Pattern WARPSCRAM_PAT = Pattern.compile(".*Warp scramble attempt.*from.*<b>(.*?)</b>.*to.*>(.*)");
	static final Pattern WARPDIS_PAT = Pattern.compile(".*Warp disruption attempt.*from.*<b>(.*?)</b>.*to.*>(.*)");
	static final Pattern YOUNOS_PAT = Pattern
			.compile(".*<b>\\+([0-9]+) GJ</b>.*energy drained from.*(.*?)</b>.* - (.*?)<.*");
	static final Pattern NEUTYOU_PAT = Pattern
			.compile("<color=0xffe57f7f><b>([0-9]+) GJ</b>.*energy neutralized.*>(.*?)</b>.* - (.*)");
	static final Pattern YOUNEUT_PAT = Pattern
			.compile("<color=0xff7fffff><b>([0-9]+) GJ</b>.*energy neutralized.*>(.*?)</b>.* - (.*)");

	protected void parseDetails() {
		Matcher m = MISSESYOU_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.MISS;
			source = m.group(1);
			return;
		}
		m = HITYOUCHARGE_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.HIT;
			quantity = Integer.parseInt(m.group(1));
			source = m.group(2);
			charge = m.group(3);
			quality = m.group(4);
			return;
		}
		m = HITYOU_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.HIT;
			quantity = Integer.parseInt(m.group(1));
			source = m.group(2);
			quality = m.group(3);
			return;
		}
		m = YOUMISS_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.MISS;
			target = m.group(1);
			charge = m.group(2);
			return;
		}
		m = YOUHITCHARGE_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.HIT;
			quantity = Integer.parseInt(m.group(1));
			target = m.group(2);
			charge = m.group(3);
			quality = m.group(4);
			return;
		}
		m = YOUHIT_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.HIT;
			quantity = Integer.parseInt(m.group(1));
			target = m.group(2);
			quality = m.group(3);
			return;
		}
		m = WARPDIS_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.POINT;
			scramble = false;
			source = m.group(1);
			target = m.group(2);
			return;
		}
		m = WARPSCRAM_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.POINT;
			scramble = true;
			source = m.group(1);
			target = m.group(2);
			return;
		}
		m = YOUNOS_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.NOS;
			quantity = Integer.parseInt(m.group(1));
			target = m.group(2);
			charge = m.group(3);
			return;
		}
		m = NEUTYOU_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.NEUT;
			quantity = Integer.parseInt(m.group(1));
			source = m.group(2);
			charge = m.group(3);
			return;
		}
		m = YOUNEUT_PAT.matcher(details);
		if (m.matches()) {
			subtype = SUBTYPE.NEUT;
			quantity = Integer.parseInt(m.group(1));
			target = m.group(2);
			charge = m.group(3);
			return;
		}

		System.err.println("can't match details " + details);
	}

}
