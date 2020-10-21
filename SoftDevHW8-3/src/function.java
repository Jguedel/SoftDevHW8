
/*
 * @author jguedel
 * @version 1.0
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class function.
 */
public class function {

	/** The ans. */
	private static String ans;

	/** The inv. */
	public static Set<Integer> inv;

	/**
	 * Gets the inventory.
	 *
	 * @param h the empty map
	 * @return the populated hashmap
	 */
	public static HashMap<Integer, Book> getInventory(HashMap<Integer, Book> h) {
		File file = new File("C:\\Users\\jgued\\Desktop\\inventory.txt");
		try {
			// CHECK IF FILE IS THERE AND IF ANYTHING IS IN IT
			if (!file.createNewFile() && file.length() != 0) {
				ObjectInputStream toTable = new ObjectInputStream(new FileInputStream(file));
				int size = toTable.readInt();
				// CREATE EACH BOOK AND PLACE IN TABLE
				for (int i = 0; i < size; i++) {
					Book t;
					t = (Book) toTable.readObject();
					h.put(t.getSku(), t);
				}
				toTable.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}

	/**
	 * Prints the to doc.
	 *
	 * @param h the hashmap
	 */
	public static void printToDoc(HashMap<Integer, Book> h) {
		File file = new File("C:\\Users\\jgued\\Desktop\\inventory.txt");

		ObjectOutputStream toPrint;
		try {
			toPrint = new ObjectOutputStream(new FileOutputStream(file));
			toPrint.writeInt(h.size());
			Iterator<Book> itr = h.values().iterator();
			while (itr.hasNext()) {
				toPrint.writeObject(itr.next());
			}
			toPrint.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds the book.
	 *
	 * @param h     the hashmap
	 * @param key   the SKU
	 * @param title the title
	 * @param price the price
	 * @param quan  the quantity
	 * @return the hash map
	 */
	public static HashMap<Integer, Book> addBook(HashMap<Integer, Book> h, int key, String title, Double price,
			int quan) {
		if (h.containsKey(key)) {
			// SEE IF BOOK IS ADDING TO QUANTITY OR A NEW BOOK
			if (h.get(key).getTitle().equals(title)) {
				int newQ = h.get(key).getQuan();
				quan = newQ + quan;
				Book t = new Book(key, title, price, quan);
				h.put(key, t);
				return h;
			} else {
				ans = "SKU already exists in inventory!";
				return h;
			}
			// IF SKU IS VALID
		} else {
			Book t = new Book(key, title, price, quan);
			h.put(key, t);
			ans = "Your book " + t.getTitle() + " was added in spot: " + String.valueOf(key);
			return h;
		}
	}

	/**
	 * Removes the book.
	 *
	 * @param h   the hashmap
	 * @param key the SKU
	 * @return the hash map
	 */
	public static HashMap<Integer, Book> removeBook(HashMap<Integer, Book> h, int key) {
		// MAKE SURE BOOK REMOVING IS IN TABLE
		if (h.get(key) != null) {
			ans = h.get(key).getTitle() + " has been removed";
			h.remove(key);
			return h;
		} else
			ans = "No Book and SKU: " + key;
		return h;
	}

	/**
	 * Display inventory.
	 *
	 * @param h the hashmap
	 */
	public static void displayInventory(HashMap<Integer, Book> h) {
		// MAKE SURE THERE IS INVENTORY
		if (h.size() > 0) {
			ans = "";
			inv = h.keySet();
			for (Integer keys : inv) {
				ans += h.get(keys).toStr();
			}
		} else
			ans = "inventory is empty";
	}

	/**
	 * Gets the book.
	 *
	 * @param h   the hashmap
	 * @param key the SKU
	 */
	public static void getBook(HashMap<Integer, Book> h, int key) {
		// MAKE SURE BOOK IS IN TABLE
		if (h.get(key) != null)
			ans = h.get(key).toStr();
		else
			ans = "No book in that SKU value";
	}

	/**
	 * Gets the ans.
	 *
	 * @return the output text
	 */
	public static String getAns() {
		return ans;
	}

}
