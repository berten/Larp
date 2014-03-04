package org.deschutter.omen.character;


public class CharacterImpl implements Character {
    private long id;
    private String characterName;
    private String lineageName;
    private String className;
    private String religionName;
    private String wealthName;


    public CharacterImpl(Long id, String characterName, String lineageName, String className, String religionName, String wealthName) {
        this.id = id;
        this.characterName = characterName;
        this.lineageName = lineageName;
        this.className = className;
        this.religionName = religionName;
        this.wealthName = wealthName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCharacterName() {
        return characterName;
    }

    @Override
    public String getLineageName() {
        return lineageName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getReligionName() {
        return religionName;
    }

    @Override
    public String getWealthName() {
        return wealthName;
    }
}
