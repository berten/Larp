package org.deschutter.omen.race;

import org.deschutter.omen.lineage.LineageEntity;
import org.springframework.data.repository.CrudRepository;


public interface RaceDao extends CrudRepository<LineageEntity, Long> {
}
