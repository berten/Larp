package org.deschutter.eternica.character;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<Character> getCharactersForUserID(Long userId) {
        return characterRepository.findByUserId(userId);
    }
}
