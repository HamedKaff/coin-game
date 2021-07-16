package view.enumfaces;

import java.awt.Image;
import javax.swing.ImageIcon;
/*
 * Enum:           Faces
 * Description:     this Enum returns the faces of the coins in Images
 * Author:          Hamed Alkaff - s3708483
 */
public enum Faces {

	HEADS {
		@Override
		public Image getImage() {
			return new ImageIcon("img/heads.png").getImage();
		}
	},

	TAILS {
		@Override
		public Image getImage() {
			return new ImageIcon("img/tails.png").getImage();
		}
	};

	public abstract Image getImage();
}
