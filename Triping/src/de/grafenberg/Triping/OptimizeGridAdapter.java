package de.grafenberg.Triping;

import java.util.List;

/**
 * Created by b.yuan on 06.08.2015.
 */
public interface OptimizeGridAdapter<T> {
		List<T> getItems();
		/**
		 * Should notify the listView data is changed
		 *
		 * @param items
		 */
		void setItems(List<T> items);
		T getNullItem();
		boolean isNullItem(T item);
		}
