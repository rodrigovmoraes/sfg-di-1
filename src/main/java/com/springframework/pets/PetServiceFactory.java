package com.springframework.pets;

public class PetServiceFactory {
    public PetService getPetService(String petServiceType) {
        switch(petServiceType) {
            case "dog":
                return new DogPetService();
            case "cat":
                return new CatPetService();
            default:
                return new DogPetService();
        }
    }
}
