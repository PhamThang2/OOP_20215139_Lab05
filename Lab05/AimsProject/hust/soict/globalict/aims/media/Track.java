package hust.soict.globalict.aims.media;

import java.util.Objects;
import java.util.Scanner;

import javax.swing.JOptionPane;

import hust.soict.globalict.aims.exception.PlayerException;

public class Track implements Playable {
	
	private String title;
	private int length;
	
	public Track() {
		super();
	}
	
	public Track(String title) {
		this.title = title;
	}
	
	public Track(String title, int length) {
		this(title);
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}
	
	public String getDetails() {
		return String.format("Title: %s\nLength: %dm.\n", title, length).replaceAll(" null | 0 ", " Unknown ");
	}
	
	@Override
	public void play() throws PlayerException {
		
		if (length<=0) {
			throw new PlayerException("ERROR: Track length is non-positive!");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("Playing track: " + this.getTitle() + "\n");
		sb.append("Track length: " + this.getLength() + "\n");
		JOptionPane.showMessageDialog(null, sb.toString(), "Play track", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		return length == other.length && Objects.equals(title, other.title);
	}
	
	public static Track createTrack() {
		String title;
		int length;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter track title: ");
		title = sc.nextLine();
		
		System.out.print("Enter track length: ");
		length = sc.nextInt();
		
		return new Track(title, length);
	}
	
}
