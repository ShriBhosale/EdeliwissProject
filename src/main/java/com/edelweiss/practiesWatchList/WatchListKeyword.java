package com.edelweiss.practiesWatchList;

import java.util.ArrayList;
import java.util.List;

public class WatchListKeyword {
	
	public List<String> keyWordProvider() {
		List<String> keyWordList=new ArrayList<String>();
		keyWordList.add("AddWatchList");
		keyWordList.add("Report");
		/*
		 * keyWordList.add("AddScript"); keyWordList.add("DeleteWatchList");
		 */
		return keyWordList;
	}

}
