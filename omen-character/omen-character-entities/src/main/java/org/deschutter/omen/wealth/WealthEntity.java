package org.deschutter.omen.wealth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WealthEntity {
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public WealthEntity(String wealthName) {
		name = wealthName;
	}

    public WealthEntity() {
    }

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		WealthEntity that = (WealthEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
