package org.deschutter.eternica.character;


public class CharacterDTO {
    private Long id;
    private String characterName;

    public CharacterDTO(Long id, String characterName) {

        this.id = id;
        this.characterName = characterName;
    }

    public CharacterDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Long getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterDTO that = (CharacterDTO) o;

        if (characterName != null ? !characterName.equals(that.characterName) : that.characterName != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (characterName != null ? characterName.hashCode() : 0);
        return result;
    }
}
