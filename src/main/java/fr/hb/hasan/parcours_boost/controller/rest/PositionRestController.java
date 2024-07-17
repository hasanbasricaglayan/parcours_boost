package fr.hb.hasan.parcours_boost.controller.rest;

import fr.hb.hasan.parcours_boost.business.Position;
import fr.hb.hasan.parcours_boost.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PositionRestController {
	@Autowired
	private PositionRepository _positionRepository;

	@GetMapping({"/positions", "/positions/"})
	public ResponseEntity<List<Position>> getAllPositions() {
		List<Position> positions = _positionRepository.findAll();

		if (positions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(positions, HttpStatus.OK);
	}

	@GetMapping({"/positions/{positionId}", "/positions/{positionId}/"})
	public ResponseEntity<Position> getPositionById(@PathVariable(value = "positionId") Long positionId) {
		Position position = _positionRepository.findById(positionId).orElse(null);

		if (position == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(position, HttpStatus.OK);
	}

	@PostMapping({"/positions", "/positions/"})
	public ResponseEntity<Position> addPosition(@RequestBody Position newPosition) {
		Position position = _positionRepository.save(newPosition);

		return new ResponseEntity<>(position, HttpStatus.CREATED);
	}

	@PutMapping({"/positions/{positionId}", "/positions/{positionId}/"})
	public ResponseEntity<Position> updatePosition(@PathVariable(value = "positionId") Long positionId, @RequestBody Position updatedPosition) {
		Position positionToUpdate = _positionRepository.findById(positionId).orElse(null);

		if (positionToUpdate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		positionToUpdate.setLatitude(updatedPosition.getLatitude());
		positionToUpdate.setLongitude(updatedPosition.getLongitude());
		Position position = _positionRepository.save(positionToUpdate);

		return new ResponseEntity<>(position, HttpStatus.OK);
	}

	@DeleteMapping({"/positions/{positionId}", "/positions/{positionId}/"})
	public ResponseEntity<Object> deletePosition(@PathVariable(value = "positionId") Long positionId) {
		Position positionToDelete = _positionRepository.findById(positionId).orElse(null);

		if (positionToDelete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		_positionRepository.deleteById(positionId);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
