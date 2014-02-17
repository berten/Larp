package org.deschutter.eternica.character;


public class Character {
    private String characterName;

    public Character(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterName() {
        return characterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (characterName != null ? !characterName.equals(character.characterName) : character.characterName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return characterName != null ? characterName.hashCode() : 0;
    }
}
