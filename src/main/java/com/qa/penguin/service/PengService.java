package com.qa.penguin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.penguin.exceptions.PenguinNotFoundException;
import com.qa.penguin.model.Penguin;
import com.qa.penguin.repo.PengRepo;

@Service
public class PengService {

	private PengRepo repo;

	public PengService(PengRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Penguin createPenguin(Penguin penguin) {
		return this.repo.save(penguin);
	}
	
	public List<Penguin> getAllPenguins() {
		return this.repo.findAll();
	}
	
	public Penguin updatePenguin(Penguin newPenguin, Long id) {
		Optional<Penguin> optPenguin = this.repo.findById(id);
		Penguin oldPenguin = optPenguin.orElseThrow(() -> new PenguinNotFoundException());
		
		oldPenguin.setName(newPenguin.getName());
		oldPenguin.setColour(newPenguin.getColour());
		oldPenguin.setHasHappyFeet(newPenguin.isHasHappyFeet());
		oldPenguin.setFishEaten(newPenguin.getFishEaten());
		
		Penguin savedPenguin = this.repo.save(oldPenguin);
		return savedPenguin;
		
	}
	
	public boolean deletePenguin(Long id) {
		this.repo.deleteById(id);
		
		return !this.repo.existsById(id);
	}
	
	
}
