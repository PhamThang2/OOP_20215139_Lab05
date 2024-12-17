package hust.soict.globalict.aims.store;

import java.util.ArrayList;

import hust.soict.globalict.aims.exception.DuplicatedItemException;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;

public class Store {

	private ArrayList<Media> itemsInStore = new ArrayList<Media>();

	public void addMedia(Media media) throws DuplicatedItemException {
		if (itemsInStore.contains(media)) {
			throw new DuplicatedItemException("ERROR: Item already exists.");
		} else {
			itemsInStore.add(0, media);
			System.out.println("Added " + media.toString() + " to store.");
		}
	}

	public void removeMedia(Media media) {
		if (itemsInStore.remove(media)) {
			System.out.println("Removed " + media.toString() + " from store.");
		} else {
			System.out.println("Couldn't find this item.");
		}
	}
	
	public void displayItems() {
		System.out.println("\n***********************STORE***********************");
		for (Media m: itemsInStore) {
			System.out.println(m.toString());
		}
		System.out.println("***************************************************");
	}
	
	
	public Media fetchMedia(String title) {
		for (Media m : itemsInStore) {
			if (m.isMatch(title))
				return m;
		}
		return null;
	}

	public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}

}
