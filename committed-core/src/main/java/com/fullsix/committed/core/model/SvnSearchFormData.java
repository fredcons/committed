package com.fullsix.committed.core.model;

import java.util.List;

public class SvnSearchFormData {
	
	List<String> authors;
	List<String> rootPaths;
	List<String> filePaths;
	
	public List<String> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
	public List<String> getRootPaths() {
		return rootPaths;
	}
	
	public void setRootPaths(List<String> rootPaths) {
		this.rootPaths = rootPaths;
	}
	
	public List<String> getFilePaths() {
		return filePaths;
	}
	
	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}
	
	

}
