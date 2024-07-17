package fr.hb.hasan.parcours_boost.business;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcours")
@Getter
@Setter
public class Parcours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String nom;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "parcours_positions", joinColumns = @JoinColumn(name = "parcours_id"), inverseJoinColumns = @JoinColumn(name = "position_id"))
	private List<Position> positions = new ArrayList<>();

	public Parcours() {
	}
}
