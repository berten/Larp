package org.deschutter.omen.character;

import java.util.List;

public interface CharacterRepository {
    List<org.deschutter.omen.character.Character> findByUserId(long userId);

    Character findById(long id);
}
