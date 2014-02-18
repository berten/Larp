package org.deschutter.eternica.character;


public class Character {
    private long id;
    private String characterName;

    public Character(long id, String characterName) {
        this.id = id;
        this.characterName = characterName;
    }

    public Character() {
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
