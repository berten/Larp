package org.deschutter.omen.character;


public interface CharacterFactory {

    Character create(Long id, String characterName, String lineageName, String className, String religionName, String wealthName);
}
