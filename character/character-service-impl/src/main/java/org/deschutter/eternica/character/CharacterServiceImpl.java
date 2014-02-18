package org.deschutter.eternica.character;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDTO> getCharactersForUserID(Long userId) {
        List<Character> characters = characterRepository.findByUserId(userId);
        ArrayList<CharacterDTO> characterDTOs = new ArrayList<>();
        for (Character character : characters) {
            characterDTOs.add(new ModelMapper().map(character, CharacterDTO.class));
        }
        return characterDTOs;
    }

    @Override
    public CharacterDTO getCharacter(Long characterId) {
        return new ModelMapper().map(characterRepository.findById(characterId), CharacterDTO.class);
    }
}
