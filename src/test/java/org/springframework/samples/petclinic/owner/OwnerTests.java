package org.springframework.samples.petclinic.owner;

import org.junit.Test;
import org.junit.Assert;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.util.SerializationUtils;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class OwnerTests{

	@Test
    public void testGetPets()
    {
        Set<Pet> pets = new HashSet<>();
        pets.add(new Pet(null, "Bella",LocalDate.now() , new PetType("dog"), null));
        pets.add(new Pet(null, "Lucy", LocalDate.now(), new PetType("cat"), null));
        pets.add(new Pet(null, "Daisy", LocalDate.now(), new PetType("hamster"), null));
        pets.add(new Pet(null, "Molly", LocalDate.now(), new PetType("snake"), null));
        pets.add(new Pet(null, "Charlie", LocalDate.now(), new PetType("dog"), null));

        // mock propertycomparator class
        PropertyComparator propertyComparator = mock(PropertyComparator.class);

        //create owner object with mocks
        Owner owner = new Owner(pets, propertyComparator);

        //create a list of pets that are sorted
        List<Pet> findPets = owner.getPets();

        //verify that method was called 
        verify(propertyComparator).sort(new ArrayList<>(pets), new MutableSortDefinition("name", true, true));


    }
	
    @Test
    public void testGetPetFailIfPetDoesNotExist() {
    	
    	Pet pet = mock(Pet.class);
    	pet.setName("Buster");

    	assertThat(pet.getName()).isEqualTo(null);
    }
    
    @Test
    public void testGetPetPassIfPetAndIdMatch() {
    	Pet pet = mock(Pet.class);
    	pet.setName("Bella");
    	pet.setId(0);

    	assertThat(pet.getName()).isEqualTo(null);
    	assertThat(pet.getId()).isEqualTo(0);
    }
    
    @Test
    public void testGetPetTypeFailIfTypeDoesNotExis() {
    	Pet pet = mock(Pet.class);
    	PetType pettype = mock(PetType.class);
    	pettype.setName("bird");
    	
    	assertThat(pettype.getName()).isEqualTo(null);
    }   


}