package com.excilys.model.java;

import java.util.ArrayList;
import java.util.List;
//Class Pagination 
public class Page<E> {
	private List<List<E>> pages;


	public Page(List<E> list){
		pages = new ArrayList<List<E>>();
		int i=0;
		int j=0;
		for(i=0; i<list.size()/10;i++){
			pages.add(new ArrayList<E>());
			for(j=0+10*i;j<10+10*i;j++){
				pages.get(i).add(list.get(j));
			}

		}
		pages.add(new ArrayList<E>());
		for(int k=j;k<list.size();k++){
			pages.get(i).add(list.get(k));
		}
	}

	public void UpdatePage(List<E> list){

		int i=0;
		int j=0;
		for(i=0; i<list.size()/10;i++){
			for(j=0+10*i;j<10+10*i;j++){
				pages.get(i).add(list.get(j));
			}

		}
		pages.add(new ArrayList<E>());
		if(list.size()%10!=0){
			for(int k=j;k<list.size();k++){
				pages.get(i).add(list.get(k));
			}
		}

	}

	public List<List<E>> getPages() {
		return pages;
	}


	public void setPages(List<List<E>> pages) {
		this.pages = pages;
	}


	@Override
	public String toString() {
		return "Page [pages=" + pages + "]";
	}




}
