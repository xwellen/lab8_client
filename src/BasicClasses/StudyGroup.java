package BasicClasses;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;


public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private static final long serialVersionUID = 32L;
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private Integer studentsCount;
    private FormOfEducation formOfEducation;
    private Semester semesterEnum;
    private Person groupAdmin;

    public StudyGroup(String name, Coordinates coordinates, Integer studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = 0;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.ZonedDateTime.now();
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toLanguageString(ResourceBundle currentBundle) {
        return String.format(currentBundle.getString("toStringGroup"),  id, name, coordinates.getX(),
            coordinates.getY(), creationDate, studentsCount,
            formOfEducation, semesterEnum, groupAdmin.getName(),
            groupAdmin.getHeight(), groupAdmin.getEyeColor(), groupAdmin.getHairColor(),
            groupAdmin.getNationality());
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        return this.id - studyGroup.getId();
    }
}
