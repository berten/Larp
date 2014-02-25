package org.deschutter.omen.character;

import org.deschutter.omen.clazz.ClassEntity;
import org.deschutter.omen.lineage.LineageEntity;
import org.deschutter.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "CHARACTER")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private LineageEntity lineage;
    @ManyToOne
    private ClassEntity classEntity;


    public CharacterEntity(UserEntity userEntity, String name, LineageEntity lineage, ClassEntity classEntity) {
        this.userEntity = userEntity;
        this.name = name;
        this.lineage = lineage;
        this.classEntity = classEntity;
    }

    public LineageEntity getLineage() {
        return lineage;
    }

    protected CharacterEntity() {
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterEntity that = (CharacterEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}
