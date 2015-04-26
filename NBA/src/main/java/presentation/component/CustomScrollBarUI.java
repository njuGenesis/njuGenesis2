package presentation.component;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBarUI extends BasicScrollBarUI {
	protected void configureScrollBarColors() {
		LookAndFeel.installColors(scrollbar, "ScrollBar.background",
				"ScrollBar.foreground");
		thumbHighlightColor = new Color(235, 236, 231); // 在这里改成你想要的颜色
		thumbLightShadowColor = new Color(235, 236, 231); // 在这里改成你想要的颜色
		thumbDarkShadowColor = new Color(235, 236, 231); // 在这里改成你想要的颜色
		thumbColor = new Color(183, 170, 175); // 在这里改成你想要的颜色
		trackColor = new Color(235, 236, 231); // 在这里改成你想要的颜色
		trackHighlightColor = new Color(183, 170, 175); // 在这里改成你想要的颜色
	}

	protected JButton createDecreaseButton(int orientation)  {
		return createIncreaseButton(orientation);
	}

	protected JButton createIncreaseButton(int orientation) {
		return new BasicArrowButton(orientation, thumbColor,
				thumbLightShadowColor, thumbDarkShadowColor,
				thumbHighlightColor);
//		JButton button = new JButton();
//		button.setIcon(new ImageIcon("img/1.png"));
//		button.setSize(13, 13);
//		return button;
	}
}

