package com.pra.utils.view;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFormattedTextField;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

public class SwingComponentUtils {

	public static <T extends Container> T findParent(Component comp, Class<T> clazz) {
		if (comp == null)
			return null;
		if (clazz.isInstance(comp))
			return (clazz.cast(comp));
		else
			return findParent(comp.getParent(), clazz);
	}

	public static void clearTextInput(JTextComponent textComponent) {
		textComponent.setText("");
	}
	
	public static void clearAllText(JTextComponent... components) {
		for (JTextComponent component : components)
			component.setText("");
	}

	public static String stringVal(JTextComponent textComponent) {
		return textComponent.getText();
	}

	public static Double doubleVal(JFormattedTextField component) {
		String s = component.getText().replace(",", "");
		// if(s.trim().isEmpty()) {
		// return 0.0;
		// }
		return Double.parseDouble(s);
	}

	public static Integer intVal(JFormattedTextField component) {
		String s = component.getText().replace(",", "");
		// if(s.trim().isEmpty()) {
		// return 0;
		// }
		return Integer.parseInt(s);
	}

	public static boolean isBlank(JTextComponent component) {
		if (component.getText().trim().isEmpty())
			return true;
		return false;
	}

	public static boolean anyBlank(JTextComponent... components) {
		for (JTextComponent component : components)
			if (component.getText().trim().isEmpty())
				return true;
		return false;
	}

	public static boolean invalidDate(JDateChooser datepicker) {
		if (datepicker.getDate() == null)
			return true;
		return false;
	}
}
