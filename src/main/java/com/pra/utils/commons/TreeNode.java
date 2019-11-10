package com.pra.utils.commons;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class TreeNode {

	private String value;
	private List<TreeNode> children;

	public TreeNode(String value) {
		this.value = value;
		this.children = new ArrayList<TreeNode>();
	}
	
	public String getValue() {
		return this.value;
	}
	
	public List<TreeNode> getChildren(){
		return this.children;
	}

	public void addChild(TreeNode child) {
		this.children.add(child);
	}
	
	public boolean hasNoChild() {
		return this.children.isEmpty();
	}
	
	public boolean hasAnyChild() {
		return !this.children.isEmpty();
	}
	
	public boolean hasOneChild() {
		return this.children.size() == 1;
	}

	public boolean includes(TreeNode child) {
		return this.includes(child.getValue());
	}
	
	public Optional<TreeNode> getIf(TreeNode child) {
		return this.getIf(child.getValue());
	}
	
	public boolean includes(String child) {
		return this.children.stream()
				.filter(c -> c.getValue().equals(child))
				.findFirst()
				.isPresent();
	}
	
	public Optional<TreeNode> getIf(String child) {
		return this.children.stream()
				.filter(c -> c.getValue().equals(child))
				.findFirst();
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public List<List<TreeNode>> getBranches(){
		List<List<TreeNode>> result = new ArrayList<>();
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(this);
		
		while(!queue.isEmpty()) {
			TreeNode head = queue.remove();
			List<TreeNode> branch = new ArrayList<>();
			List<TreeNode> childs = this.addToBranch(branch , head);
			result.add(branch);
			if(childs.size() > 1) {
				queue.addAll(childs);
			}
		}
		return result;
	}
	
	private List<TreeNode> addToBranch(List<TreeNode> branch,TreeNode node){
		branch.add(node);
		List<TreeNode> children = node.getChildren();
		if(children.size() == 1) {
			return addToBranch(branch,children.get(0));
		}else {
			return children;
		}
	}
	
}
