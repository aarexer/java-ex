package good.collections.empty;

public class CollectionExample {
    public static void main(String[] args) {
        showEmptyNameCollection();
        System.out.println("--------------------------");
        collectionWithPersonNames();
    }

    private static void showEmptyNameCollection() {
        PersonNames personNames = new PersonNames();
        System.out.println(personNames);
    }

    private static void collectionWithPersonNames() {
        PersonNames personNames = new PersonNames("Aleksandr", "Kira", "Katya", "Masha", "Roman");
        System.out.println(personNames);
    }
}
