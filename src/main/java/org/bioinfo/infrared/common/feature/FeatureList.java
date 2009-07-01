package org.bioinfo.infrared.common.feature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bioinfo.infrared.common.dbsql.DBConnector;


@SuppressWarnings("serial")
public class FeatureList<T extends Feature> extends ArrayList<T>{
	
//	private List<T> elements;
	private int numberNullElements;
	
	public FeatureList(int numFeatures) {
		super(numFeatures);
		setNumberNullElements(0);
	}
	
	public FeatureList(Collection<T> c) {
		setNumberNullElements(0);
		addAll(c);
	}
	
	public void setRosettaDBConnector(DBConnector dBConnector) {
		for(Feature feat: this) {
			if(feat != null) {
				feat.setRosettaDBConnector(dBConnector);
			}
		}
	}
	
	public T first() {
		if(this != null && this.size() > 0) {
			return get(0);
		}
		return null;
	}
	
	public T last() {
		return get(this.size()-1);
	}

	public T get(String itemName) {
		T feature = null;
		for(T feat: this) {
			if(feat.getId().equalsIgnoreCase(itemName)) {
				feature = feat;
			}
		}
		return feature;
	}
	
	/* (non-Javadoc)
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, T element) {
		if(element == null) {
			setNumberNullElements(getNumberNullElements() + 1);
		}
		super.add(index, element);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		if(e == null) {
			setNumberNullElements(getNumberNullElements() + 1);
		}
		return super.add(e);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		if(c != null) {
			for(T t: c) {
				if(t == null) {
					setNumberNullElements(getNumberNullElements() + 1);
				}
			}
			return super.addAll(c);
		}else {
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if(c != null) {
			for(T t: c) {
				if(t == null) {
					setNumberNullElements(getNumberNullElements() + 1);
				}
			}
			return super.addAll(index, c);
		}else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#clear()
	 */
	@Override
	public void clear() {
		setNumberNullElements(0);
		super.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#remove(int)
	 */
	@Override
	public T remove(int index) {
		if(this.get(index) == null) {
			numberNullElements--;
		}
		return super.remove(index);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		if(o == null) {
			setNumberNullElements(getNumberNullElements() - 1);
		}
		return super.remove(o);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	@Override
	public T set(int index, T element) {
		if(this.get(index) != null && element == null) {
			setNumberNullElements(getNumberNullElements() + 1);
		}
		if(this.get(index) == null && element != null) {
			setNumberNullElements(getNumberNullElements() - 1);
		}
		return super.set(index, element);
	}

	public void removeNullElements() {
		for(int i=this.size()-1; i >= 0; i--) {
			if(this.get(i) == null) {
				this.remove(i);
			}
		}
	}
	public List<String> getFeaturesIds() {
		List<String> ids = new ArrayList<String>(this.size());
		for(Feature feat: this) {
			if(feat != null) {
				ids.add(feat.getId());
			}
		}
		return ids;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < this.size()-1; i++) {
			if(this.get(i) != null) {
				sb.append(this.get(i).toString()).append("\n");
			}
		}
		if(this.size() > 0 && this.get(this.size()-1) != null) {
			sb.append(this.get(this.size()-1).toString());
		}
		return sb.toString();
	}

	/**
	 * @param numberNullElements the numberNullElements to set
	 */
	public void setNumberNullElements(int numberNullElements) {
		this.numberNullElements = numberNullElements;
	}

	/**
	 * @return the numberNullElements
	 */
	public int getNumberNullElements() {
		return numberNullElements;
	}

	
}
