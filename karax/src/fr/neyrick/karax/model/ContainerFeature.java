package fr.neyrick.karax.model;

import javax.xml.bind.annotation.XmlTransient;

import fr.neyrick.karax.entities.generic.CharacterEdit;


@XmlTransient
public abstract class ContainerFeature extends AbstractFeature {

	private int depth = 0;
	
	public int getDepth() {
		return depth;
	}

	protected String getSubItemKey(CharacterEdit edit) {
		switch (depth) {
			case 0: return edit.getTargetSubKey1(); 
			case 1: return edit.getTargetSubKey2(); 
			case 2: return edit.getTargetSubKey3();
			default: return null;
		}
	}
	
	public ContainerFeature(ContainerFeature parent, String key) {
		super(parent, key);
		this.depth = parent.depth + 1;
	}

	public ContainerFeature(String key) {
		super(null, key);
	}
	
	@Override
	public void setContainer(ContainerFeature parent) {
		super.setContainer(parent);
		this.depth = parent.depth + 1;		
	}

	private ContainerFeature() {
		super(null, null);
		throw new UnsupportedOperationException("Dummy constructor");
	}
	
	public abstract CharacterFeature addFeature(String subItemKey, CharacterEdit edit);
	
	public abstract CharacterFeature getSubFeature(String key);
	
	@Override
	public void recordEdit(CharacterEdit edit) {
		String subitemKey = getSubItemKey(edit);
		CharacterFeature feature = getSubFeature(subitemKey);
		if (feature == null) {
			feature = addFeature(subitemKey, edit);
		}
		feature.recordEdit(edit);
	}
	
}
