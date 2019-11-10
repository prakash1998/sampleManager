package com.pra.utils.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.pra.utils.commons.Searchable;

public class AutoCompleteFileHandler {

	private static FileWriter writer;

	public static void main(String[] args) {
		List<String> keywords = new ArrayList<>();
		
		keywords.add("Hello world");
		keywords.add("bye world");
		keywords.add("Hello java");
		keywords.add("bye world");
		
		System.out.println(Searchable.convert(keywords));
	}

	public static List<String> getKeywordsFromFile(File file) {
		List<String> result = new ArrayList<String>();

		try {
			result = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Suggestions not Found at Path : " + file.getAbsolutePath());
		}

		return Searchable.convert(result);
	}

	public static boolean saveKeywordsIntoFile(File file, List<String> keywords) {
		keywords = Searchable.convert(keywords);
		try {
			file.createNewFile();
			writer = new FileWriter(file);
			for (String keyword : keywords) {
				writer.write(keyword + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
