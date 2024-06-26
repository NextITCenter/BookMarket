package cart;

import book.BookVO;

public class CartVO {
	private long no;
	private BookVO book;
	private int quantity;
	public CartVO() {
	}
	public CartVO(long no, BookVO book, int quantity) {
		this.no = no;
		this.book = book;
		this.quantity = quantity;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public BookVO getBook() {
		return book;
	}
	public void setBook(BookVO book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartVO [no=" + no + ", book=" + book + ", quantity=" + quantity + "]";
	}
	
}

