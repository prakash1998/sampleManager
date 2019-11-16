package com.pra.utils.view;

import javax.swing.JOptionPane;

public class CommonOptionPanes {
	public static boolean showExitPrompt() {
		return JOptionPane.showConfirmDialog(null, "Are you want to Exit?","Exit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == 0;
	}
	
	public static boolean showDeletePrompt() {
		return JOptionPane.showConfirmDialog(null, "Are you want to Delete?","Delete", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == 0;
	}
}
