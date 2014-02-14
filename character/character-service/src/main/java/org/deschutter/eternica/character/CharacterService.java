package org.deschutter.eternica.character;


import java.util.List;

public interface CharacterService {
    List<Character> getCharactersForUserID(Long userId);
}
