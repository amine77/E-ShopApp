package com.zarzisdev.EShopApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TermRestService {

	@Autowired
	private TermRepository termRepository;

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	List<Term> getAllTerms() {
		return termRepository.findAll();
	}

	@RequestMapping(value = "/100-terms", method = RequestMethod.GET)
	List<Term> getLast100Terms() {
		return termRepository.findRandomTerms();
	}

	// ------------------- Trouver un Term
	// --------------------------------------------------------
	@RequestMapping(value = "/terms/{id}", method = RequestMethod.GET)
	public Term getTerm(@PathVariable("id") Long id) {
		return termRepository.findById(id).orElse(null);
	}

	// ------------------- Créer un Term
	// --------------------------------------------------------
	@RequestMapping(value = "/terms", method = RequestMethod.POST)
	public Term saveTerm(@RequestBody Term t) {
		// @requestBody indique que les données sont envoyées dans le corps de la
		// requête
		return termRepository.save(t);
	}

	// ------------------- Update un Term
	// --------------------------------------------------------
	@RequestMapping(value = "/terms/{id}", method = RequestMethod.PUT)
	public Term updateTerm(@RequestBody Term p, @PathVariable("id") Long id) {

		System.out.println("Updating Term " + id);

		Term currentTerm = termRepository.findById(id).orElse(null);

		if (currentTerm == null) {
			System.out.println("Term with id " + id + " not found");
			return new Term();
		}

		currentTerm.setId(id);

		return termRepository.save(currentTerm);

	}

	// ------------------- Supprimer un Term
	// --------------------------------------------------------

	@RequestMapping(value = "/terms/{id}", method = RequestMethod.DELETE)
	public void deleteTerm(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Term with id " + id);

		Term term = termRepository.findById(id).orElse(null);
		if (term == null) {
			System.out.println("Unable to delete. Term with id " + id + " not found");

		} else {
			termRepository.delete(term);
		}

	}
}
