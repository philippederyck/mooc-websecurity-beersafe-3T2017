package eu.beersafe.mooc.data.objects;

public class Beer {

	private long id;
	private String name;
	private String image;
	private double alcohol;
	private String color;
	private String category;
	private String description;
	
	public Beer(long id, String name, String image, double alcohol, String color, String category, String description) {
		this.setId(id);
		this.setName(name);
		this.setImage(image);
		this.setAlcohol(alcohol);
		this.setColor(color);
		this.setCategory(category);
		this.setDescription(description);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
