package com.pra.utils.view;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

public class AutoComplete implements DocumentListener {
	
	// demo implementation
	
	private static final String COMMIT_ACTION = "commit";
	JTextArea mainTextField = new JTextArea();

	public AutoComplete() {
		this.keywords = null;

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Without this, cursor always leaves text field
		mainTextField.setFocusTraversalKeysEnabled(false);
		mainTextField.setRows(5);
		mainTextField.setColumns(20);

		// Our words to complete
		List<String> keywords = new ArrayList<String>(5);
		keywords.add("price given");
		keywords.add("autocomplete");
		keywords.add("stackabuse");
		keywords.add("java");
		AutoComplete autoComplete = new AutoComplete(mainTextField, keywords);
		mainTextField.getDocument().addDocumentListener(autoComplete);

		// Maps the tab key to the commit action, which finishes the autocomplete
		// when given a suggestion
		mainTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		mainTextField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());

		JPanel p = new JPanel();

		p.add(mainTextField);

		frame.add(p);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AutoComplete();
			}
		});
	}
	
	
	

	private static enum Mode {
		INSERT, COMPLETION
	};

	private JTextComponent textComponent;
	private final List<String> keywords;
	private Mode mode = Mode.INSERT;

	public AutoComplete(JTextComponent textComponent, List<String> keywords, String commitKey) {
		this.textComponent = textComponent;
		this.textComponent.getInputMap().put(KeyStroke.getKeyStroke(commitKey), COMMIT_ACTION);
		this.textComponent.getActionMap().put(COMMIT_ACTION, this.new CommitAction());
		this.keywords = keywords;
		Collections.sort(keywords);
	}

	public AutoComplete(JTextComponent textComponent, List<String> keywords) {
		this(textComponent,keywords,"TAB");
	}
	
	public AutoComplete(JTextComponent textComponent, File keywordFile , String commitKey) {
		this(textComponent,AutoCompleteFileHandler.getKeywordsFromFile(keywordFile),commitKey);
	}
	
	public AutoComplete(JTextComponent textComponent, File keywordFile ) {
		this(textComponent,AutoCompleteFileHandler.getKeywordsFromFile(keywordFile));
	}

	@Override
	public void changedUpdate(DocumentEvent ev) {
	}

	@Override
	public void removeUpdate(DocumentEvent ev) {
	}

	@Override
	public void insertUpdate(DocumentEvent ev) {
		if (ev.getLength() != 1)
			return;

		int pos = ev.getOffset();
		String content = null;
		try {
			content = textComponent.getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		// Find where the word starts
		int w;
		for (w = pos; w >= 0; w--) {
			if (!Character.isLetter(content.charAt(w))) {
				break;
			}
		}

		// Too few chars
		if (pos - w < 1)
			return;

		String prefix = content.substring(w + 1).toLowerCase();
		int n = Collections.binarySearch(keywords, prefix);
		if (n < 0 && -n <= keywords.size()) {
			String match = keywords.get(-n - 1);
			if (match.startsWith(prefix)) {
				// A completion is found
				String completion = match.substring(pos - w);
				// We cannot modify Document from within notification,
				// so we submit a task that does the change later
				SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
			}
		} else {
			// Nothing found
			mode = Mode.INSERT;
		}
	}

	public class CommitAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5794543109646743416L;

		@Override
		public void actionPerformed(ActionEvent ev) {
			if (mode == Mode.COMPLETION) {
				int pos = textComponent.getSelectionEnd();
				StringBuffer sb = new StringBuffer(textComponent.getText());
				sb.insert(pos, " ");
				textComponent.setText(sb.toString());
				textComponent.setCaretPosition(pos + 1);
				mode = Mode.INSERT;
			} else {
				textComponent.replaceSelection("\t");
			}
		}
	}

	private class CompletionTask implements Runnable {
		private String completion;
		private int position;

		CompletionTask(String completion, int position) {
			this.completion = completion;
			this.position = position;
		}

		public void run() {
			StringBuffer sb = new StringBuffer(textComponent.getText());
			sb.insert(position, completion);
			textComponent.setText(sb.toString());
			textComponent.setCaretPosition(position + completion.length());
			textComponent.moveCaretPosition(position);
			mode = Mode.COMPLETION;
		}
	}

}