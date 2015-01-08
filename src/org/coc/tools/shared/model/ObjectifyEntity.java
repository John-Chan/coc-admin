package org.coc.tools.shared.model;
/*
@Entity
public class ObjectifyEntity {

	@Id
	private Long	id;

	public Long getRowId() {
		return this.id;
	}
	public void setRowId(Long id) {
		this.id = id;
	}
}
*/
public interface ObjectifyEntity {

	public Long getRowId() ;
	public void setRowId(Long id) ;
}