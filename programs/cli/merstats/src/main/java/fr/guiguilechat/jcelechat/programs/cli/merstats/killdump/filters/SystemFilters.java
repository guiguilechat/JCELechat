package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.KDEntry;

public class SystemFilters {

	public static boolean typed(KDEntry kde) {
		return kde.getType() != null;
	}

	public static final OffsetDateTime POCHVEN_CREATTION = LocalDateTime.of(2020, 10, 13, 12, 00)
			.atOffset(ZoneOffset.UTC);


	public static final Predicate<KDEntry> AS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isAbyssal;
		}

		@Override
		public String toString() {
			return "AS";
		}
	};

	public static final Predicate<KDEntry> AS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return AS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "AST";
		}
	};

	public static final Predicate<KDEntry> HS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isHS();
		}

		@Override
		public String toString() {
			return "HS";
		}
	};

	public static final Predicate<KDEntry> HS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return HS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "HST";
		}
	};

	public static final Predicate<KDEntry> JS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isJovian;
		}

		@Override
		public String toString() {
			return "JS";
		}
	};

	public static final Predicate<KDEntry> JS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return JS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "JST";
		}
	};

	public static final Predicate<KDEntry> LS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isLS();
		}

		@Override
		public String toString() {
			return "LS";
		}
	};
	public static final Predicate<KDEntry> LS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return LS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "LST";
		}
	};

	public static final Predicate<KDEntry> NS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isNS();
		}

		@Override
		public String toString() {
			return "NS";
		}
	};

	public static final Predicate<KDEntry> NS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return NS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "NST";
		}
	};

	public static final Predicate<KDEntry> PS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isPochven && kde.getDate().isAfter(POCHVEN_CREATTION);
		}

		@Override
		public String toString() {
			return "PS";
		}
	};

	public static final Predicate<KDEntry> PS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return PS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "PST";
		}
	};

	public static final Predicate<KDEntry> WS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isWormhole;
		}

		@Override
		public String toString() {
			return "WS";
		}
	};

	public static final Predicate<KDEntry> WS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return WS.test(kde) && typed(kde);
		}

		@Override
		public String toString() {
			return "WST";
		}
	};
}
