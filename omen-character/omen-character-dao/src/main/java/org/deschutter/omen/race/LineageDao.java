package org.deschutter.omen.race;

import org.deschutter.omen.lineage.LineageEntity;
import org.springframework.data.repository.CrudRepository;


public interface LineageDao extends CrudRepository<LineageEntity, Long> {
}
