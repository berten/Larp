package org.deschutter.eternica.character;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {
    @Autowired
    private CharacterDao characterDao;

    @Override
    public List<Character> findByUserId(long userId) {
        ArrayList<Character> characters = new ArrayList<>();
        List<CharacterEntity> entities = characterDao.findByUserEntityId(userId);
        for (CharacterEntity entity : entities) {
            characters.add(new DozerBeanMapper().map(entity,Character.class));
        }
        return characters;
    }
}
