package aoc7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Aoc7Test {

    @Test
    void examplePart1() {
        assertEquals(4, new Aoc7(getExampleInputPart1()).calculatePart1());
    }

    @Test
    void examplePart2() {
        assertEquals(126, new Aoc7(getExampleInputPart2()).calculatePart2());
    }

    @Test
    void simpleExample() {
        assertEquals(1, new Aoc7(List.of("bright white bags contain 1 shiny gold bag.")).calculatePart1());
    }

    @Test
    void parseEmptyBag() {
        Bag expected = new Bag("faded blue");
        Aoc7 aoc7 = new Aoc7(List.of("faded blue bags contain no other bags."));
        assertEquals(expected, aoc7.getBagFromStorage("faded blue"));
    }

    @Test
    void parseContainsSingleBag() {
        Bag whiteBag = new Bag("bright white");
        Bag goldBag = new Bag("shiny gold");
        whiteBag.addContents(1, goldBag);

        Aoc7 aoc7 = new Aoc7(List.of("bright white bags contain 1 shiny gold bag."));
        assertEquals(whiteBag, aoc7.getBagFromStorage("bright white"));
    }

    @Test
    void parseContainsMultipleBags() {
        Bag redBag = new Bag("light red");
        Bag whiteBag = new Bag("bright white");
        Bag yellowBag = new Bag("muted yellow");
        redBag.addContents(1, whiteBag);
        redBag.addContents(2, yellowBag);

        Aoc7 aoc7 = new Aoc7(List.of("light red bags contain 1 bright white bag, 2 muted yellow bags."));
        assertEquals(redBag, aoc7.getBagFromStorage("light red"));
    }

    private List<String> getExampleInputPart1() {
        return List.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags."
        );
    }

    private List<String> getExampleInputPart2() {
        return List.of(
                "shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."
        );
    }
}