package com.PSMS.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListHelper {
	/* @brief distinct list */
	public static List DistinctList(List list) {
		Set set = new HashSet();
		List resultList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element)) {
				resultList.add(element);
			}
		}
		return resultList;
	}
}
