package org.deschutter.omen.character;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {
	@Autowired
	private CharacterDao characterDao;
	@Autowired
	private CharacterFactory characterFactory;

	public CharacterRepositoryImpl() {
	}

	@Override
	public List<org.deschutter.omen.character.Character> findByUserId(long userId) {
		ArrayList<Character> characters = new ArrayList<>();
		List<CharacterEntity> entities = characterDao.findByUserEntityId(userId);
		for (CharacterEntity entity : entities) {
			characters.add(createDomainObject(entity));
		}
		return characters;
	}

	@Override
	public Character findById(long id) {
        return createDomainObject(characterDao.findOne(id));
	}

	private Character createDomainObject(CharacterEntity entity) {
		return characterFactory.create(entity.getId(), entity.getName(), entity.getLineage().getName(), entity
				.getClassEntity().getName(), entity.getReligion().getName(), entity.getWealth().getName());
	}
}
