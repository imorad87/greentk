/**
 * 
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.persistence.MappedSuperclass;

/**
 * @author Islam Morad
 *
 */
@MappedSuperclass
public abstract class AbstractModel {

	protected PropertyChangeSupport pcs;
	
	public AbstractModel(){
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
       pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

	
}
