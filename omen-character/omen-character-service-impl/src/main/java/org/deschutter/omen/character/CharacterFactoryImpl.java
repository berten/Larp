package org.deschutter.omen.character;

import org.springframework.stereotype.Component;

@Component
public class CharacterFactoryImpl implements CharacterFactory {
    @Override
    public Character create(Long id, String characterName, String lineageName, String className, String religionName, String wealthName) {
        return new CharacterImpl(id,characterName,lineageName,className,religionName,wealthName);
    }
}
