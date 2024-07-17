package fr.hb.hasan.parcours_boost.controller.rest;

import fr.hb.hasan.parcours_boost.business.Parcours;
import fr.hb.hasan.parcours_boost.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParcoursRestController {
	@Autowired
	private ParcoursRepository _parcoursRepository;

	@GetMapping({"/parcours", "/parcours/"})
	public ResponseEntity<List<Parcours>> getAllParcours() {
		List<Parcours> parcours = _parcoursRepository.findAll();

		if (parcours.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(parcours, HttpStatus.OK);
	}

	@GetMapping({"/parcours/{parcoursId}", "/parcours/{parcoursId}/"})
	public ResponseEntity<Parcours> getParcoursById(@PathVariable(value = "parcoursId") Long parcoursId) {
		Parcours parcours = _parcoursRepository.findById(parcoursId).orElse(null);

		if (parcours == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(parcours, HttpStatus.OK);
	}

	@PostMapping({"/parcours", "/parcours/"})
	public ResponseEntity<Parcours> addParcours(@RequestBody Parcours newParcours) {
		Parcours parcours = _parcoursRepository.save(newParcours);

		return new ResponseEntity<>(parcours, HttpStatus.CREATED);
	}

	@PutMapping({"/parcours/{parcoursId}", "/parcours/{parcoursId}/"})
	public ResponseEntity<Parcours> updateParcours(@PathVariable(value = "parcoursId") Long parcoursId, @RequestBody Parcours updatedParcours) {
		Parcours parcoursToUpdate = _parcoursRepository.findById(parcoursId).orElse(null);

		if (parcoursToUpdate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		parcoursToUpdate.setNom(updatedParcours.getNom());
		parcoursToUpdate.setPositions(updatedParcours.getPositions());
		Parcours parcours = _parcoursRepository.save(parcoursToUpdate);

		return new ResponseEntity<>(parcours, HttpStatus.OK);
	}

	@DeleteMapping({"/parcours/{parcoursId}", "/parcours/{parcoursId}/"})
	public ResponseEntity<Object> deleteParcours(@PathVariable(value = "parcoursId") Long parcoursId) {
		Parcours parcoursToDelete = _parcoursRepository.findById(parcoursId).orElse(null);

		if (parcoursToDelete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		_parcoursRepository.deleteById(parcoursId);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
