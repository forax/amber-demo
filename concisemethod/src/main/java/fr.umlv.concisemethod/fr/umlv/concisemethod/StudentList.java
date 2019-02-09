package fr.umlv.concisemethod;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class StudentList {
  private final ArrayList<String> list = new ArrayList<>();
  
  public void add(String student) -> list.add(requireNonNull(student));
  
  public List<String> asList() -> new AbstractList<>() {
    public int size() = list::size;
    public String get(int index) = list::get;
  };
  
  public static void main(String[] args) {
    var students = new StudentList();
    students.add("John");
    students.add("Jane");
    System.out.println(students.asList());
  }
}
