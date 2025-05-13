import java.util.*;

public class Card_Collection {
    private static final Map<Character, String> SUIT_MAP = Map.of(
            'S', "Spades", 'H', "Hearts", 'D', "Diamonds", 'C', "Clubs"
    );
    private static final List<String> CARD_ORDER = List.of("K", "Q", "J", "A", "10", "9", "8", "7", "6", "5", "4", "3", "2");

    public static void main(String[] args) {
        TreeMap<String, TreeSet<String>> cards = new TreeMap<>();
        for (String suit : SUIT_MAP.values()) {
            cards.put(suit, new TreeSet<>(Comparator.comparingInt(CARD_ORDER::indexOf)));
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Card Collection System =====");
            System.out.println("1. Add a Card");
            System.out.println("2. Search for a Card");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCard(cards, scanner);
                case 2 -> searchCard(cards, scanner);
                case 3 -> displayCards(cards);
                case 4 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1-4.");
            }
        }
    }

    private static void addCard(TreeMap<String, TreeSet<String>> cards, Scanner scanner) {
        System.out.print("Enter card suit (S for Spades, H for Hearts, D for Diamonds, C for Clubs): ");
        char suitChar = Character.toUpperCase(scanner.next().charAt(0));

        if (!SUIT_MAP.containsKey(suitChar)) {
            System.out.println("Error: Invalid suit. Choose from S, H, D, C.");
            return;
        }
        String suit = SUIT_MAP.get(suitChar);

        System.out.print("Enter card value (K, Q, J, A, 10, 9, ..., 2): ");
        String value = scanner.next().trim().toUpperCase();

        if (!CARD_ORDER.contains(value)) {
            System.out.println("Error: Invalid card value.");
            return;
        }

        if (cards.get(suit).contains(value)) {
            System.out.println("Error: Card already exists in the collection.");
        } else {
            cards.get(suit).add(value);
            System.out.println("Card added successfully.");
        }
    }

    private static void searchCard(TreeMap<String, TreeSet<String>> cards, Scanner scanner) {
        System.out.print("Enter card suit to search (S, H, D, C): ");
        char suitChar = Character.toUpperCase(scanner.next().charAt(0));

        if (!SUIT_MAP.containsKey(suitChar)) {
            System.out.println("Error: Invalid suit.");
            return;
        }
        String suit = SUIT_MAP.get(suitChar);

        System.out.print("Enter card value to search: ");
        String value = scanner.next().trim().toUpperCase();

        if (cards.get(suit).contains(value)) {
            System.out.println(value + " of " + suit + " is in the collection.");
        } else {
            System.out.println(value + " of " + suit + " is not in the collection.");
        }
    }

    private static void displayCards(TreeMap<String, TreeSet<String>> cards) {
        System.out.println("\n===== Card Collection =====");

        for (String suit : SUIT_MAP.values()) {
            System.out.print(suit + ": ");
            if (cards.get(suit).isEmpty()) {
                System.out.println("No cards.");
            } else {
                System.out.println(String.join(", ", cards.get(suit)));
            }
        }
    }
}
