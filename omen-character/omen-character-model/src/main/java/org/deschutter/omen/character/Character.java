package org.deschutter.omen.character;


public class Character {
    private long id;
    private String characterName;
    private String lineageName;

    public Character(long id, String characterName, String lineageName) {
        this.id = id;
        this.characterName = characterName;
        this.lineageName = lineageName;
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
