package com.pra.utils.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Searchable {

	private static final String TREE_ROOT = "zejrajfaktkhereklto";

	public static void main(String[] args) {

		List<String> temp = new ArrayList<String>();
		temp.add("price given");
		temp.add("price taken");
		temp.add("Hello given");
		temp.add("Hello taken");
		temp.add("hello world");
		temp.add("No price given");
		temp.add("Some price taken");
		temp.add("Hello price given");
		temp.add("price price taken");
		
		System.out.println(convert(temp));

	}

	public static List<String> convert(List<String> strings) {
		Set<String> result = new TreeSet<String>();

		TreeNode root = new TreeNode(TREE_ROOT);
		strings.stream().forEach(s -> {
			pushIntoTree(root, root,  words(s));
		});

//		System.out.println("children of root is " + root.getChildren());

//		System.out.println(root.getBranches());

		for (TreeNode node : root.getChildren()) {
			List<List<TreeNode>> branches = node.getBranches();
			branches.stream().forEach(branch -> {
				Optional<TreeNode> str = branch.stream().reduce((i1,i2)-> new TreeNode(i1.getValue()+" "+i2.getValue()));
				str.ifPresent(s -> result.add(s.getValue()));
			});
		}

		return new ArrayList<String>(result);
	}
	

	private static void pushIntoTree(TreeNode root,TreeNode node, List<String> strings) {
//		System.out.println(node.getValue() + " push --> " + strings);
		if (!strings.isEmpty()) {
			String str = strings.remove(0);
			TreeNode newRoot;
			
			if(node.getChildren().size() <= 1) {
				Optional<TreeNode> temp = node.getIf(str);
				if (temp.isPresent()) {
					newRoot = temp.get();
				} else {
					newRoot = new TreeNode(str);
					node.addChild(newRoot);
				}
			}else {
				Optional<TreeNode> temp = root.getIf(str);
				if (temp.isPresent()) {
					newRoot = temp.get();
				} else {
					newRoot = new TreeNode(str);
					root.addChild(newRoot);
				}
			}
			pushIntoTree(root,newRoot, strings);
		}
	}

	private static List<String> words(String string) {
		List<String> result = new ArrayList<>();
		String[] subStrings = string.split(" ");

		for (String s : subStrings) {
			s = s.trim();
			if (!s.isEmpty())
				result.add(s);
		}
//		System.out.println(string + " ---> " + result);
		return result;
	}

}
