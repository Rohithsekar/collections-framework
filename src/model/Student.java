package model;

import java.util.Comparator;

public class Student implements Comparator<Student> {

    private int id;
    private String name;
    private double cgpa;

    public Student() {
    }

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }

    @Override
    public int compare(Student o1, Student o2) {
        int cgpaComparison = Double.compare(o2.getCgpa(), o1.getCgpa()); // Compare CGPA in descending order

        if (cgpaComparison != 0) {
            System.out.println("CGPA comparison has done for " +  "student object 1 " + o1.getName() + " and student object 2 " + o2.getName());
            return cgpaComparison;
        }

        System.out.println("Name comparison is done for " + o1.getName() + " and " + o2.getName());
        int nameComparison = o1.getName().compareTo(o2.getName()); // Compare names in ascending order
        if (nameComparison != 0) {
            return nameComparison;
        }

        System.out.println("id comparison is done for " + o1.getName() + " and " + o2.getName());
        return Integer.compare(o1.getId(), o2.getId()); // Compare IDs in ascending order
    }
}

