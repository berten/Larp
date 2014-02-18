package org.deschutter.eternica.character;

import java.util.List;

public interface CharacterRepository {
    List<Character> findByUserId(long userId);

    Character findById(long id);
}
