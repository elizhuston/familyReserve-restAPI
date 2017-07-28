package com.family.familyReserve.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.family.familyReserve.domain.Family;
import com.family.familyReserve.domain.FamilyRepository;
import com.family.familyReserve.domain.Person;
import com.family.familyReserve.domain.Post;
import com.family.familyReserve.domain.Recipe;
import com.family.familyReserve.domain.RecipeRepository;
import com.family.familyReserve.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@Api(value = "Family Recipe ")
public class RecipeController {
	
	
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private FamilyRepository familyRepository;
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Add family recipe", notes = "Add recipe for family")
	@RequestMapping(path = "/api/recipe", method = RequestMethod.POST)
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
		System.out.println("/api/recipe POST ");
		//if (relationTypeRepository.findRelationTypeByDesc(t.getDescription()) != null) {
		Family recipeFamily = recipe.getFamily();
		System.out.println("Recipe family id is " + recipeFamily.getId());
		recipeFamily = familyRepository.findFamilyById(recipeFamily.getId());
		if (recipeFamily == null ){
			return new ResponseEntity<Recipe>(recipe,HttpStatus.BAD_REQUEST);
		}
		
		recipeRepository.save(recipe);
		return new ResponseEntity<Recipe>(recipe,HttpStatus.CREATED);
	}
	
	@JsonView(View.Summary.class)
	@ApiOperation(value = "Get recipe by id", notes = "Returns recipe object for given id")
	@RequestMapping(path = "/api/recipe/{id}", method = RequestMethod.GET)
	public ResponseEntity<Recipe> findOneId(@PathVariable(name = "id", required = true) Integer id) {
		System.out.println("/api/recipe/{id} GET " + id);
	
		Recipe recipe = recipeRepository.findOne(id);
				
		return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	}
}
	
