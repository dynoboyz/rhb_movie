package my.com.rhb.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
public class Movie {
	private Long id;
	private String title;
	private String category;

	@DecimalMax(value = "5.0", message = "Maximum rating is 5.0")
	@DecimalMin(value = "0.5", message = "Minimum rating is 0.5")
	private float rating;

	protected Movie() {
	}

	public Movie(Long id, String title, String category, float rating) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.rating = rating;
	}

	public Movie(String title, String category, float rating) {
		this.title = title;
		this.category = category;
		this.rating = rating;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
