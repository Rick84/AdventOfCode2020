package aoc7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc7 {
    private static final Pattern CONTENTS_PATTERN = Pattern.compile("(?<amount>\\d+) (?<color>.+) bag(s?)(\\.?)");

    private final Map<String, Bag> bagStorage;

    public Aoc7(List<String> input) {
        this.bagStorage = new HashMap<>();
        input.forEach(this::parse);
    }

    public static void main(String[] args) throws IOException {
        Aoc7 aoc7 = new Aoc7(getInput());
        System.out.println("part 1 " + aoc7.calculatePart1());
        System.out.println("part 2 " + aoc7.calculatePart2());
    }

    public int calculatePart1() {
        return bagStorage.get("shiny gold").getWithAllContainedBy().size() - 1; // subtract the requested bag itself
    }

    public int calculatePart2() {
        return bagStorage.get("shiny gold").countWithContents() - 1; // subtract the requested bag itself
    }

    Bag getBagFromStorage(String color) {
        return bagStorage.get(color);
    }

    private void parse(String input) {
        String[] split = input.split(" contain ");
        String bag = split[0];
        String contents = split[1];

        Bag mainBag = parseMainBag(bag);
        if (!contents.equals("no other bags.")) {
            parseContents(mainBag, contents);
        }
    }

    private Bag parseMainBag(String input) {
        String color = input.substring(0, input.length() - " bags".length());
        return bagStorage.computeIfAbsent(color, Bag::new);
    }

    private void parseContents(Bag mainBag, String input) {
        Arrays.stream(input.split(", "))
                .forEach(bag -> addBag(mainBag, bag));
    }

    private void addBag(Bag mainBag, String input) {
        Matcher matcher = CONTENTS_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unexpected input: " + input);
        }

        String color = matcher.group("color");
        int amount = Integer.parseInt(matcher.group("amount"));

        Bag bag = bagStorage.computeIfAbsent(color, Bag::new);
        mainBag.addContents(amount, bag);
    }

    private static List<String> getInput() throws IOException {
        return Files.readAllLines(Paths.get("src", "main", "resources", "aoc7.txt"));
    }
}
