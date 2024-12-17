package hust.soict.globalict.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {

	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitle()
			.thenComparing(new MediaComparatorByCost());
	public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCost()
			.thenComparing(new MediaComparatorByTitle());

	private static int nbMedia = 0;
	protected int id;
	protected String title;
	protected String category;
	protected float cost;

	public Media() {
		super();
		this.id = ++nbMedia;
	}

	public Media(String title) {
		this();
		this.title = title;
	}

	public Media(String title, String category, float cost) {
		this(title);
		this.category = category;
		this.cost = cost;
	}

	public boolean isMatch(String title) {
		String[] keywords = title.split("\\s+");
		for (String word : keywords) {
			if (this.title.toLowerCase().contains(word.toLowerCase()))
				return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public float getCost() {
		return cost;
	}

	abstract public String getDetails();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		return Objects.equals(title, other.title);
	}

}
