package hust.soict.globalict.aims.screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.globalict.aims.Aims;
import hust.soict.globalict.aims.exception.DuplicatedItemException;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.Track;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfArtist;
	private JTextField tfDirector;
	private JTextField tfCost;
	private JTextField tfNumberTracks;
	private int numberTracks;

	public AddCompactDiscToStoreScreen() {
		super("CD");

		JPanel center = createCenter();
		tfTitle = addInputField("Title: *", center);
		tfCategory = addInputField("Category:", center);
		tfArtist = addInputField("Artist", center);
		tfDirector = addInputField("Director:", center);
		tfCost = addInputField("Cost: *", center);
		tfNumberTracks = addInputField("Tracks: *", center);
		center.add(Box.createRigidArea(new Dimension(10, 20)));

		add(center, BorderLayout.CENTER);
		add(createSouth(new ButtonListener()), BorderLayout.SOUTH);

		setVisible(true);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "OK": {
				if (tfTitle.getText().equals("") || tfCost.getText().equals("")
						|| tfNumberTracks.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Some required fields are empty!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					CompactDisc cd = new CompactDisc(tfTitle.getText(), tfCategory.getText(), tfArtist.getText(),
							tfDirector.getText(), Float.parseFloat(tfCost.getText()));

					numberTracks = Integer.parseInt(tfNumberTracks.getText());

					AddTracksToCompactDiscScreen childScreen = new AddTracksToCompactDiscScreen(cd);

				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Cost and number of tracks must be numeric!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
				break;
			case "Cancel":
				setVisible(false);
				break;
			}
		}

	}

	private class AddTracksToCompactDiscScreen extends AddItemToStoreScreen {

		private CompactDisc cd;

		private JLabel lblTrackNo;
		private JTextField tfTitle;
		private JTextField tfLength;

		private int screenIndex;

		public AddTracksToCompactDiscScreen(CompactDisc cd) {
			super("Track");

			this.cd = cd;
			this.screenIndex = 1;

			JPanel center = createCenter();

			lblTrackNo = new JLabel("Track no. " + screenIndex);
			lblTrackNo.setAlignmentX(CENTER_ALIGNMENT);
			center.add(lblTrackNo);
			center.add(Box.createRigidArea(new Dimension(10, 20)));

			tfTitle = addInputField("Title: *", center);
			tfLength = addInputField("Length:", center);
			center.add(Box.createRigidArea(new Dimension(10, 120)));

			add(center, BorderLayout.CENTER);
			add(createSouth(new ButtonListener()), BorderLayout.SOUTH);

			setVisible(true);
		}

		private void reset() {
			tfTitle.setText("");
			tfLength.setText("");
			screenIndex++;
			lblTrackNo.setText("Track no. " + screenIndex);
			setVisible(true);
		}

		private class ButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "OK": {

					if (tfTitle.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Required fields are empty!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					try {
						Track track = new Track(tfTitle.getText(),
								tfLength.getText().equals("") ? 0 : Integer.parseInt(tfLength.getText()));
						cd.addTrack(track);

						setVisible(false);
						if (screenIndex == numberTracks) {
							Aims.getStore().addMedia(cd);
							Aims.getStoreScreen().loadItemsToStore();
							AddCompactDiscToStoreScreen.this.setVisible(false);
							JOptionPane.showMessageDialog(null, "New CD added to store!");
							return;
						}
						
						reset();

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Length must be an integer value!", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (DuplicatedItemException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
					break;
				case "Cancel":
					setVisible(false);
					break;
				}
			}

		}
	}
}
