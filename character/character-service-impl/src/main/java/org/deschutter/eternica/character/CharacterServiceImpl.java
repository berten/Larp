package org.deschutter.eternica.character;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
	@Autowired
	private CharacterRepository characterRepository;

	@Override
	public List<CharacterDTO> getCharactersForUserID(Long userId) {
		List<Character> characters = characterRepository.findByUserId(userId);
		ArrayList<CharacterDTO> characterDTOs = new ArrayList<>();
		for (Character character : characters) {
			characterDTOs.add(new DozerBeanMapper().map(character,CharacterDTO.class));
		}
		return characterDTOs;
	}
}
