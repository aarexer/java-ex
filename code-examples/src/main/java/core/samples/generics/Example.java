package core.samples.generics;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {

        List<Cat> listOfCats = new ArrayList<>();
        listOfCats.add(new Cat("First"));
        listOfCats.add(new Cat("Second"));

        List<? extends Animal> listOfAnimals = listOfCats;
        callPets(listOfAnimals);

        //callPetsWrong(listOfAnimals);

        callPetsAnotherVersion(listOfAnimals);
    }

    //it's wrong
    //cause we can use only list with Animal
    //but not list of Cats
    static void callPetsWrong(List<Animal> list) {
        list.stream().forEach(Animal::call);
    }

    //It's nice
    static void callPets(List<? extends Animal> list) {
        list.stream().forEach(Animal::call);
    }

    //It's good
    static <T extends Animal> void callPetsAnotherVersion(List<T> list) {
        list.stream().forEach(Animal::call);
    }

    //consumer
    static void putAnimalToCollection(List<? super Animal> list) {
        list.add(new Cat("Kitty"));
        list.add(new Dog("Doggerman"));
    }

    //wildcard
    static void printCollection(List<?> list) {
        list.stream().forEach(System.out::println);
    }
}
