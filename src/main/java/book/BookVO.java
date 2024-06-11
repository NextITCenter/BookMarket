package book;

import java.time.LocalDate;

// 데이터를 저장하는 객체
// VO, DTO, Item, Bean 등 여러 이름으로 사용
// VO(Value Object) => 우리나라 현업에서 많이 사용
// DTO(Data Transfer Object) => 정확한 의미
public class BookVO {
	private String id;
	private String title;
	private int price;
	private String author;
	private String description;
	private String publisher;		// 출판사
	private String category;		// 분류
	private long quantity;			// 재고 개수
	private LocalDate releaseDate;	// 출판일(년-월-일)
	private String condition;		// 신제품 or 구제품 or 리퍼비시 제품
	public BookVO() {
	}
	public BookVO(String id, String title, int price, String author, String description, String publisher,
			String category, long quantity, LocalDate releaseDate, String condition) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.author = author;
		this.description = description;
		this.publisher = publisher;
		this.category = category;
		this.quantity = quantity;
		this.releaseDate = releaseDate;
		this.condition = condition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
