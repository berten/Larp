package org.deschutter.omen.character;


public class Character {
    private long id;
    private String characterName;
    private String lineageName;
    private String className;
    private String religionName;
    private String wealthName;

    public Character(long id, String characterName, String lineageName, String className, String religionName, String wealthName) {
        this.id = id;
        this.characterName = characterName;
        this.lineageName = lineageName;
        this.className = className;
        this.religionName = religionName;
        this.wealthName = wealthName;
    }


    public Character() {
    }

    public String getLineageName() {
        return lineageName;
    }

    public void setLineageName(String lineageName) {
        this.lineageName = lineageName;
    }

    public long getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getWealthName() {
        return wealthName;
    }

    public void setWealthName(String wealthName) {
        this.wealthName = wealthName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (id != character.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
