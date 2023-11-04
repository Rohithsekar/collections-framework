package impl;

import model.Student;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StudentWeightingQueue extends PriorityQueue<Student> {

    public StudentWeightingQueue(Comparator<Student> comparator){
        super(comparator);
    }

    @Override
    public boolean offer(Student student) {
        // Iterate through the existing elements in the priority queue
        // and insert the new element at the appropriate position
        for (Student existingStudent : this) {
            //This condition becomes true when the current student object has more priority than the
            //Head element of the priority queue.
            if (comparator().compare(student, existingStudent) < 0) {
                // If the new student has higher priority, insert it before the existing student
                super.remove(existingStudent);
                super.offer(student);
                super.offer(existingStudent);
                return true;
            }
        }
        // If the new student has lowest priority, simply add it to the end
        return super.offer(student);
    }
}

