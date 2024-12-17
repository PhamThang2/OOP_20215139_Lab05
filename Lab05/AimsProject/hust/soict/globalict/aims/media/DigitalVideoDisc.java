package hust.soict.globalict.aims.media;

import java.util.Scanner;

import javax.swing.JOptionPane;

import hust.soict.globalict.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

	public DigitalVideoDisc() {
		super();
	}
	
	public DigitalVideoDisc(String title) {
		super(title);
	}

	public DigitalVideoDisc(String title, String category, float cost) {
		super(title, category, cost);
	}

	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super(title, category, director, cost);
	}

	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title, category, director, length, cost);
	}

	public String getDetails() {
		return String.format("---DVD---\nTitle: %s\nCategory: %s\nDirector: %s\nLength: %dm.\nCost: %.2f $\n", title,
				category, director, length, cost).replaceAll(" null | 0 ", " Unknown ");
	}

	@Override
	public void play() throws PlayerException {

		if (getLength() <= 0) {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Playing DVD: " + this.getTitle() + "\n");
			sb.append("DVD length: " + this.getLength() + "\n");
			JOptionPane.showMessageDialog(null, sb.toString(), "Play DVD", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	@Override
	public String toString() {
		return String.format("DVD - %s - %s - %s - %dm. : %.2f $", title, category, director, length, cost)
				.replaceAll(" null | 0 ", " Unknown ");
	}

	public static DigitalVideoDisc createDVD() {
		System.out.println("---New DVD---");
		String title, category, director;
		int length;
		float cost;

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter title: ");
		title = sc.nextLine();

		System.out.print("Enter category: ");
		category = sc.nextLine();

		System.out.print("Enter director: ");
		director = sc.nextLine();

		System.out.print("Enter length: ");
		length = sc.nextInt();

		System.out.print("Enter cost: ");
		cost = sc.nextFloat();

		return new DigitalVideoDisc(title, category, director, length, cost);
	}
}
