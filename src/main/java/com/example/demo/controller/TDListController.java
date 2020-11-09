package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.TDListItemNotFoundException;
import com.example.demo.model.TDListItem;
import com.example.demo.repository.TDListItemRepository;

// Rest Controller, this portion takes in HTTP Requests made with a given URL, and then returns items depending on what request was made
@RestController
public class TDListController {
	@Autowired
	TDListItemRepository repository;
	
	// e.g. http://localhost:8080/allitems
	@GetMapping("/allitems")
	public List<TDListItem> getAllItems () {
		return repository.findAll();
	}
	
	// e.g. http://localhost:8080/createitem
	@PostMapping("/createitem")
	public TDListItem createItem (@Valid @RequestBody TDListItem item) {
		return repository.save(item);
	}
	
	// e.g. http://localhost:8080/getitem/1
	@GetMapping("getitem/{id}")
	public TDListItem getItemById(@PathVariable(value = "id") Long itemId) throws TDListItemNotFoundException {
		return repository.findById(itemId).orElseThrow(() -> new TDListItemNotFoundException(itemId));
	}
	
	// e.g. http://localhost:8080/adduser
	@PostMapping("adduser")
	public TDListItem addUser(@Valid @RequestBody TDListItem User){
		return repository.save(user);
	}
	
	// e.g. http://localhost:8080/updateitem/1
	@PostMapping("updateitem/{id}")
	public TDListItem updateItem(@PathVariable(value = "id") Long itemId) throws TDListItemNotFoundException{
		return repository.update(item);
	}
	
	// e.g. http://localhost:8080/removeitem/1
	@DeleteMapping("removeitem/{id}")
	public TDListItem removeItem(@PathVariable(value = "id") Long itemId) throws TDListItemNotFoundException{
		return repository.delete(item);
	}
	
	// Still need to implement post requests (Update items), and delete requests (Delete a given item)
}
