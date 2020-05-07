package examples.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ExampleApplication {
    public static void main(String[] args) {
        List<Artist> artists = new ArrayList<Artist>() {
            {
                add(new Artist("Aleksandr"));
                add(new Artist("Roman"));
                add(new Artist("Genka"));
                add(new Artist("Max"));
            }
        };

        List<String> names = artists.stream().map(Artist::getName).collect(Collectors.toList());
        System.out.println(names);

        List<Track> tracks = new ArrayList<Track>() {
            {
                add(new Track("Track1", 400));
                add(new Track("Track2", 20));
                add(new Track("Track3", 300));
                add(new Track("Track4", 150));
            }
        };

        Optional<Track> max = tracks.stream().max(Comparator.comparing(Track::getDuration));
        System.out.println(max);

        List<String> words = new ArrayList<String>() {
            {
                add("Hello");
                add("Hello");
                add("Hi");
            }
        };

        Optional<String> maxLength = words.stream().max(Comparator.comparing(String::length));
        System.out.println(maxLength);

        Map<String, Long> wCount = words.stream().collect(groupingBy(Function.identity(), counting()));
        System.out.println(wCount);

        Stream<String> artistNames = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        Optional<String> max1 = artistNames.max(Comparator.comparing(o -> o.split(" ")[0]));

        System.out.println(max1);
    }
}
