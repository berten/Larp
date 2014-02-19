package org.deschutter.eternica.character;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharacterDao extends CrudRepository<CharacterEntity, Long> {

    public List<CharacterEntity> findByUserEntityId(Long userId);

}
