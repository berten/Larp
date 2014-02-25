package org.deschutter.omen.character;


public class CharacterDTO {
    private Long id;
    private String characterName;
    private String lineageName;
    private String className;
    private String religionName;
    private String wealthName;

    public CharacterDTO(Long id, String characterName, String lineageName, String className, String religionName, String wealthName) {
        this.id = id;
        this.characterName = characterName;
        this.lineageName = lineageName;
        this.className = className;
        this.religionName = religionName;
        this.wealthName = wealthName;
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

    public String getLineageName() {
        return lineageName;
    }

    public void setLineageName(String lineageName) {
        this.lineageName = lineageName;
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

    public String getWealthName() {return wealthName;
    }

    public void setWealthName(String wealthName) {
        this.wealthName = wealthName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterDTO that = (CharacterDTO) o;

        if (characterName != null ? !characterName.equals(that.characterName) : that.characterName != null)
            return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lineageName != null ? !lineageName.equals(that.lineageName) : that.lineageName != null) return false;
        if (religionName != null ? !religionName.equals(that.religionName) : that.religionName != null) return false;
        if (wealthName != null ? !wealthName.equals(that.wealthName) : that.wealthName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (characterName != null ? characterName.hashCode() : 0);
        result = 31 * result + (lineageName != null ? lineageName.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (religionName != null ? religionName.hashCode() : 0);
        result = 31 * result + (wealthName != null ? wealthName.hashCode() : 0);
        return result;
    }
}
