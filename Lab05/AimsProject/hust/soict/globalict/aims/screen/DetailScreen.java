package hust.soict.globalict.aims.screen;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.LimitExceededException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hust.soict.globalict.aims.Aims;
import hust.soict.globalict.aims.exception.DuplicatedItemException;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Disc;
import hust.soict.globalict.aims.media.Media;

public class DetailScreen extends JFrame {
	private Media media;

	public DetailScreen(Media media) {
		super("Details");
		this.media = media;

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		add(Box.createRigidArea(new Dimension(10, 10)));
		displayInformation();
		add(Box.createVerticalGlue());
		addButtons();
		add(Box.createRigidArea(new Dimension(10, 5)));

		setBounds(450, 200, 300, 350);
		setVisible(true);
	}

	void displayInformation() {
		addInfoLine("Media type:", media instanceof DigitalVideoDisc ? "Digital Video Disc"
				: media instanceof CompactDisc ? "Compact Disc" : "Book");
		addInfoLine("Title:", media.getTitle());
		addInfoLine("Category:", media.getCategory());

		if (media instanceof CompactDisc) {
			addInfoLine("Artist:", ((CompactDisc) media).getArtist());
		}
		if (media instanceof Disc) {
			addInfoLine("Director:", ((Disc) media).getDirector());
			addInfoLine("Length:", ((Disc) media).getLength() + " m.");
		}
		if (media instanceof CompactDisc) {
			addInfoLine("Number of tracks:", ((CompactDisc) media).getTracks().size() + "");
		}
		if (media instanceof Book) {
			addInfoLine("Author(s):", String.join(", ", ((Book) media).getAuthors()));
		}

		addInfoLine("Cost:", String.format("%.2f $", media.getCost()));
	}

	void addInfoLine(String label, String info) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(Box.createRigidArea(new Dimension(10, 10)));

		JLabel lblLabel = new JLabel(label);
		lblLabel.setFont(new Font(lblLabel.getFont().getName(), Font.BOLD, 12));
		panel.add(lblLabel);

		JLabel lblInfo = new JLabel((info == null || info.equals("")) ? "Unknown" : info);
		lblInfo.setFont(new Font(lblInfo.getFont().getName(), Font.PLAIN, 12));
		panel.add(lblInfo);

		add(panel);
	}

	void addButtons() {
		JPanel panel = new JPanel(new FlowLayout());

		JButton btnAddToCart = new JButton("Add to cart");
		btnAddToCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Aims.getCart().addMedia(media);
					JOptionPane.showMessageDialog(null,
							String.format("Added %s to cart.\nCurrent number of items in cart: %d", media.toString(),
									Aims.getCart().getItemsOrdered().size()));
				} catch (LimitExceededException | DuplicatedItemException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnAddToCart);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnCancel);

		add(panel);
	}

}
