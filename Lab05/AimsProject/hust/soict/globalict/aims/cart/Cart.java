package hust.soict.globalict.aims.cart;

import java.util.Collections;

import javax.naming.LimitExceededException;
import javax.swing.JOptionPane;

import hust.soict.globalict.aims.exception.DuplicatedItemException;
import hust.soict.globalict.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {

	public static final int MAX_NUMBERS_ORDERED = 20;

	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

	public void addMedia(Media media) throws LimitExceededException, DuplicatedItemException {
		if (itemsOrdered.size() == MAX_NUMBERS_ORDERED) {
			throw new LimitExceededException("ERROR: The number of media has reached its limit.");
		}

		if (itemsOrdered.contains(media)) {
			throw new DuplicatedItemException("ERROR: Item already in cart.");
		}
		
		itemsOrdered.add(media);
	}

	public void removeMedia(Media media) {
		if (itemsOrdered.remove(media)) {
			System.out.println("Removed " + media.toString() + " from cart.");
		} else {
			System.out.println("Couldn't find this item.");
		}
	}

	public float totalCost() {
		float cost = 0;
		for (Media m : itemsOrdered) {
			cost += m.getCost();
		}
		return cost;
	}

	public void print() {
		System.out.println("\n***********************CART***********************");
		System.out.println("Ordered Items:");
		for (int i = 0; i < itemsOrdered.size(); i++) {
			System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
		}
		System.out.println("Total cost: " + totalCost() + " $");
		System.out.println("**************************************************");
	}

	public void searchById(int id) {
		System.out.println("Search results for ID: " + id);
		for (Media m : itemsOrdered) {
			if (m.getId() == id) {
				System.out.println(m.toString());
				return;
			}
		}
		System.out.println("No items found.");
	}

	public void searchByTitle(String title) {
		boolean found = false;
		System.out.println("Search results for keywords: " + title);
		for (Media m : itemsOrdered) {
			if (m.isMatch(title)) {
				System.out.println(m.toString());
				found = true;
			}
		}
		if (!found)
			System.out.println("No items found.");
	}

	public void sortByTitle() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
	}

	public void sortByCost() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
	}

	public Media fetchMedia(String title) {
		for (Media m : itemsOrdered) {
			if (m.isMatch(title))
				return m;
		}
		return null;
	}

	public void placeOrder() {
		itemsOrdered.clear();
	}

	public ObservableList<Media> getItemsOrdered() {
		return itemsOrdered;
	}
}
