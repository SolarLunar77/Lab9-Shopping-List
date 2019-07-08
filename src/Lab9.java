import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Lab9 {

	public static void main(String[] args) {
		String userInput = "";
		String userContinue = "";
		Scanner scan = new Scanner(System.in);
		Map<String, Double>menu = new TreeMap<>();

		menu.put("apple", 0.99);
		menu.put("banana", 0.59);
		menu.put("cauliflower", 1.59);
		menu.put("dragonfruit", 2.19);
		menu.put("elderberry", 1.79);
		menu.put("figs", 2.09);
		menu.put("grapefruit", 1.99);
		menu.put("honeydew", 3.49);

		ArrayList<String> orderItems = new ArrayList<String>();
		ArrayList<Double> orderPrices = new ArrayList<Double>();

		
		do {
			boolean validateInput = false;
			while (validateInput == false) {
				getMenu(menu);
				System.out.print("\n" + "Which item would you like to order? ");
				userInput = scan.next();
				
				try {
					if (menu.containsKey(userInput)) {
						validateInput = true;
					} else {
						validateInput = false;
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, we don't have this item. Please try again." + "\n");
				}
			}
			
			orderItems.add(userInput);
			double findPrice = menu.get(userInput);
			orderPrices.add(findPrice);
			
			System.out.println("Adding " + userInput + " to cart at $" + findPrice + "\n");
			System.out.println("Would you like to order anything else (y/n)?");
			
			userContinue = scan.next();

		} while (userContinue.contains("y"));

		System.out.println("Thank you for shopping!" + "\n" + "Here is your total: ");

		getCart(orderItems, orderPrices);
		getAverage(orderPrices);
		getHighest(menu, orderPrices);
		getLowest(menu, orderPrices);
		
	}
	
	public static void getLowest (Map map, ArrayList<Double> d) {
		Double min = Collections.min(d);
		for (Object o : map.keySet()) {
			if (map.get(o).equals(min)) {
				System.out.println("The least expensive item is " + o + " at " + "$" + min);
			}
		}
	}
	
	public static void getHighest(Map map,ArrayList<Double> d) {
		Double max = Collections.max(d);
		for (Object o:map.keySet()) {
			if (map.get(o).equals(max)) {
				System.out.println("The most expensive item is " + o + " at " + "$" + max);
			}
		}
	}
	
	public static void getAverage(ArrayList<Double> price) {
		DecimalFormat Money = new DecimalFormat("$0.00");
		double sum = 0.0;
		double avg = 0.0;
		for (int i = 0; i < price.size(); i++) {
			sum += price.get(i);
			avg = sum / price.size();
		}
		System.out.println("\n" + "Average price per item in order is: " + Money.format(avg));
	}

	public static void getCart(ArrayList<String> s, ArrayList<Double> d) {
		DecimalFormat Money = new DecimalFormat("$0.00");
		String f = "%-17.17s %-17.17s %n";
		for (int i = 0; i < s.size(); i++)
			System.out.printf(f, s.get(i), Money.format(d.get(i)));
	}

	public static void getMenu(Map menu) {
		System.out.println("Welcome to Guenther's Market!" + "\n");
		System.out.printf("%-20s %s", "Item", "Price");
		System.out.println("\n" + "=============================");
		menu.forEach((item, price) -> System.out.printf("%-20s %s\n", item, price));
	}
	
}
