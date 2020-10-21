
/*
 * @author jguedel
 * @version 1.0
 * 
 */
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Composite {

	/** The sku. */
	private Text SKU;

	/** The Title. */
	private Text Title;

	/** The Price. */
	private Text Price;

	/** The Quantity. */
	private Text Quantity;

	/** The output. */
	private Text output;

	/** The ans. */
	private String ans;

	/** The key. */
	private int key;

	/** The title. */
	private String title;

	/** The price. */
	private Double price;

	/** The quan. */
	private int quan;

	/** The h. */
	static HashMap<Integer, Book> h = new HashMap<Integer, Book>();

	/**
	 * Create the composite.
	 *
	 * @param args the arguments
	 */

	public static void main(String[] args) {
		// FILL HASHTABLE WITH PREVIOUS IVENTORY IF THERE IS SOME
		h = function.getInventory(h);
		System.out.println(h.toString());
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		Main Key = new Main(shell, SWT.NONE);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();

		}
		display.dispose();
	}

	/**
	 * Instantiates a new main.
	 *
	 * @param parent the parent
	 * @param style  the style
	 */
	public Main(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Label lblBookstore = new Label(this, SWT.NONE);
		lblBookstore.setBounds(135, 10, 56, 16);
		lblBookstore.setText("BookStore");

		SKU = new Text(this, SWT.BORDER);
		SKU.setBounds(10, 55, 41, 19);

		Title = new Text(this, SWT.BORDER);
		Title.setBounds(57, 55, 177, 19);

		Price = new Text(this, SWT.BORDER);
		Price.setBounds(240, 55, 49, 19);

		Quantity = new Text(this, SWT.BORDER);
		Quantity.setBounds(295, 55, 41, 19);

		Label lblSku = new Label(this, SWT.NONE);
		lblSku.setBounds(10, 33, 41, 16);
		lblSku.setText("SKU:");

		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setBounds(57, 33, 56, 16);
		lblTitle.setText("Title:");

		Label lblPrice = new Label(this, SWT.NONE);
		lblPrice.setText("Price:");
		lblPrice.setBounds(240, 33, 34, 16);

		Label lblQuantity = new Label(this, SWT.NONE);
		lblQuantity.setBounds(288, 33, 56, 16);
		lblQuantity.setText("Quantity:");

		Button btnAddBook = new Button(this, SWT.NONE);
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// CHECK IF FIELDS ARE EMPTY
				if (SKU.getText() != "" && Title.getText() != "" && Price.getText() != "" && Quantity.getText() != "") {
					key = Integer.parseInt(SKU.getText());
					title = Title.getText();
					// MAKE SURE PRICE IS A VALID DOUBLE
					try {
						price = Double.parseDouble(Price.getText());
						// MAKE SURE QUAN IS A VALID INT
						try {
							quan = Integer.parseInt(Quantity.getText());
							h = function.addBook(h, key, title, price, quan);
							function.printToDoc(h);
							ans = function.getAns();
						} catch (NumberFormatException z) { // will be thrown if number is too large
							ans = "Please enter a valid number into Quantity";
						}
					} catch (NumberFormatException z) { // will be thrown if number is too large
						ans = "Please enter a valid number into Price";
					}

				} else {
					ans = "Missing one or more input fields. Please make sure that they are all filled out to add a book.";
					function.printToDoc(h);
				}
				output.setText(ans);

			}
		});
		btnAddBook.setBounds(10, 93, 70, 21);
		btnAddBook.setText("Add Book");

		Button btnRemoveBook = new Button(this, SWT.NONE);
		btnRemoveBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// CHECK IF FIELD IS EMPTY
				if (SKU.getText() != "") {
					key = Integer.parseInt(SKU.getText());
					h = function.removeBook(h, key);
					function.printToDoc(h);
					ans = function.getAns();
				} else {
					ans = "Missing the SKU. Please place a number for the SKU";
					function.printToDoc(h);
				}
				output.setText(ans);
			}
		});
		btnRemoveBook.setBounds(10, 120, 87, 21);
		btnRemoveBook.setText("Remove Book");

		Button btnInventory = new Button(this, SWT.NONE);
		btnInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				function.displayInventory(h);
				ans = function.getAns();
				output.setText(ans);
			}
		});
		btnInventory.setText("Inventory");
		btnInventory.setBounds(10, 147, 70, 21);

		Label lblneedAllInfo = new Label(this, SWT.NONE);
		lblneedAllInfo.setBounds(86, 95, 111, 16);
		lblneedAllInfo.setText("(Need all info filled)");

		Label lblneedSkuOnly = new Label(this, SWT.NONE);
		lblneedSkuOnly.setBounds(103, 122, 94, 16);
		lblneedSkuOnly.setText("(Need SKU only)");

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(86, 149, 105, 16);
		lblNewLabel.setText("(No info needed)");

		Button btnDisplayBook = new Button(this, SWT.NONE);
		btnDisplayBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// CHECK IF FIELD IS EMPTY
				if (SKU.getText() != "") {
					key = Integer.parseInt(SKU.getText());
					function.getBook(h, key);
					ans = function.getAns();
				} else {
					ans = "Missing the SKU. Please place a number for the SKU";
					function.printToDoc(h);
				}
				output.setText(ans);
			}
		});
		btnDisplayBook.setBounds(10, 174, 87, 21);
		btnDisplayBook.setText("Display Book");

		Label lblneedSkuOnly_1 = new Label(this, SWT.NONE);
		lblneedSkuOnly_1.setBounds(103, 179, 88, 16);
		lblneedSkuOnly_1.setText("(Need SKU only)");

		output = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		output.setEditable(false);
		output.setBounds(10, 204, 326, 166);

	}
}
