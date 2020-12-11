package aoc7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bag {
    private final String color;
    private final Map<Bag, Integer> contents;
    private final Set<Bag> containedBy;

    public Bag(String color) {
        this.color = color;
        contents = new HashMap<>();
        containedBy = new HashSet<>();
    }

    public void addContents(int amount, Bag bag) {
        if (contents.containsKey(bag)) {
            throw new IllegalArgumentException("Unexpected duplicate bag: " + bag.color);
        }

        bag.addContainedBy(this);
        contents.put(bag, amount);
    }

    public int countWithContents() {
        return contents.entrySet().stream()
                .mapToInt(entry -> entry.getValue() * entry.getKey().countWithContents())
                .sum() + 1; // count the current bag as well
    }

    public Set<Bag> getWithAllContainedBy() {
        return withContainedBy().collect(Collectors.toSet());
    }

    private Stream<Bag> withContainedBy() {
        return Stream.concat(
                Stream.of(this),
                containedBy.stream().flatMap(Bag::withContainedBy)
        );
    }

    private void addContainedBy(Bag bag) {
        containedBy.add(bag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(color, bag.color) && Objects.equals(contents, bag.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, contents);
    }

    @Override
    public String toString() {
        return "Bag{" +
                "color='" + color + '\'' +
                ", contents=" + contents +
                '}';
    }
}
