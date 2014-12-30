package org.coc.tools.shared.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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
