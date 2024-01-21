package fr.guiguilechat.jcelechat.libs.spring.mer.model;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A MER loaded in the DB
 */
@Entity(name = "MerLoaded")
@Table(name = "mer_loaded")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LoadedMer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private LocalDate periodMonth;

	private Instant startLoad;

	private Instant endLoad;

	private String url;

}
