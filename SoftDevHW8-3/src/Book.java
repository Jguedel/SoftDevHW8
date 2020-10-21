
/*
 * @author jguedel
 * @version 1.0
 * 
 */
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Book.
 */
public class Book implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 156789L;

	/** The sku. */
	private Integer sku;

	/** The title. */
	private String title;

	/** The price. */
	private Double price;

	/** The quan. */
	private Integer quan;

	/**
	 * Instantiates a new book.
	 *
	 * @param sku   the sku
	 * @param title the title
	 * @param price the price
	 * @param quan  the quan
	 */
	public Book(Integer sku, String title, Double price, Integer quan) {
		this.sku = sku;
		this.title = title;
		this.price = price;
		this.quan = quan;
	}

	/**
	 * Gets the sku.
	 *
	 * @return the sku
	 */
	public Integer getSku() {
		return sku;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the quan.
	 *
	 * @return the quantity
	 */
	public Integer getQuan() {
		return quan;
	}

	/**
	 * To str.
	 *
	 * @return the book info
	 */
	public String toStr() {
		return Integer.toString(this.sku) + " Title: " + this.title + " $" + this.price + " Quantity: " + this.quan
				+ " \n";
	}
}