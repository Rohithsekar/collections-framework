package queue;

import impl.StudentWeightingQueue;
import model.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class StudentPriorityQueue {

    /**
     * Problem definition:
     *
     * There are a number of students in a school who wait to be served. Two types of events, ENTER and SERVED,
     * can take place which are described below.
     *
     * ENTER: A student with some priority enters the queue to be served.
     * SERVED: The student with the highest priority is served (removed) from the queue.
     * A unique id is assigned to each student entering the queue. The queue serves the students based on the
     * following criteria (priority criteria):
     *
     * The student having the highest Cumulative Grade Point Average (CGPA) is served first.
     * Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
     * Any students having the same CGPA and name will be served in ascending order of the id.
     * Create the following two classes:
     *
     * The Student class should implement:
     * The constructor Student(int id, String name, double cgpa).
     * The method int getID() to return the id of the student.
     * The method String getName() to return the name of the student.
     * The method double getCGPA() to return the CGPA of the student.
     * The Priorities class should implement the method List<Student> getStudents(List<String> events)
     * to process all the given events and return all the students yet to be served in the priority order.
     * Input Format
     *
     * The first line contains an integer,n, describing the total number of events. Each of the
     * subsequent lines will be of the following two forms:
     *
     * ENTER name CGPA id: The student to be inserted into the priority queue.
     * SERVED: The highest priority student in the queue was served.
     *
     * Sample Input:
     *
     * 12
     * ENTER John 3.75 50
     * ENTER Mark 3.8 24
     * ENTER Shafaet 3.7 35
     * SERVED
     * SERVED
     * ENTER Samiha 3.85 36
     * SERVED
     * ENTER Ashley 3.9 42
     * ENTER Maria 3.6 46
     * ENTER Anik 3.95 49
     * ENTER Dan 3.95 50
     * SERVED
     *
     *
     * Sample Output:
     *
     * Dan
     * Ashley
     * Shafaet
     * Maria
     *
     */

    static boolean continueTask = true;

    public static void main(String[] args) {


        if (continueTask) {
            System.out.println("Source is file or console?(F for file, C for Console:");
            Scanner s = new Scanner(System.in);
            String x = s.nextLine();
            if (x.equals("F") || x.equals("f")) {
                Path path = Paths.get("src/resources/input.txt");
                try {
                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    Long start = Instant.now().getEpochSecond();
                    System.out.println("start timestamp: " + Instant.now().getEpochSecond());
                    getStudents(lines);
                    Long end = Instant.now().getEpochSecond();
                    System.out.println("end timestamp: " + Instant.now().getEpochSecond());
                    System.out.println("time taken: " + (end - start));

                } catch (IOException e) {
                    System.out.println("IO exception occured." + e.getMessage());
                }
            } else {
                System.out.println("Enter number of students:");
                int n = Integer.parseInt(s.nextLine());
                List<String> inputLines = new ArrayList<>();

                System.out.println("Start:");
                // Read input lines and store them in a list
                for (int i = 0; i < n; i++) {
                    inputLines.add(s.nextLine());
                }

                getStudents(inputLines);
                System.out.println("Do you wish to continue?(Y/N): ");
                String y = s.nextLine();
                if (y.equals("Y") || y.equals("y")) {
                    main(new String[]{});
                }
            }

        }

    }

    public static List<Student> getStudents(List<String> events) {

        //priority queue to add elements according to their weightage
        Queue<Student> studentCompareQueue = new StudentWeightingQueue(new Student());

        // Process input lines and create Student objects
        for (String line : events) {
            String[] tokens = line.split(" ");
            if (tokens[0].equals("ENTER")) {
                String name = tokens[1];
                double CGPA = Double.parseDouble(tokens[2]);
                int id = Integer.parseInt(tokens[3]);
                Student student = new Student(id, name, CGPA);
                studentCompareQueue.offer(student);
                System.out.println(Arrays.toString(studentCompareQueue.toArray()));
            } else if (tokens[0].equals("SERVED")) {
                // Handle SERVED event if needed
                System.out.println("Just before polling the queue looked like this:");
                System.out.println(Arrays.toString(studentCompareQueue.toArray()));
                if (!studentCompareQueue.isEmpty()) {
                    System.out.println(studentCompareQueue.poll().getName() + " got served");
                    System.out.println("After polling the served student, the list is:");
                    System.out.println(Arrays.toString(studentCompareQueue.toArray()));
                }
            }
        }

        System.out.println("The end: ");
        for (Student x : studentCompareQueue) {
            System.out.println(x);
        }

        return studentCompareQueue.stream().collect(Collectors.toList());

    }
}
