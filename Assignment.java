

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// Abstract class Task
abstract class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getDescription() {
        return description;
    }

    public abstract void displayTask();
}

// Concrete class for ToDoTask
class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public void displayTask() {
        System.out.println("To-Do: " + description + (isCompleted ? " (Completed)" : ""));
    }
}

// Concrete class for DeadlineTask
class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public void displayTask() {
        System.out.println("Deadline Task: " + description + " (Deadline: " + deadline + ")" + (isCompleted ? " (Completed)" : ""));
    }
}

// Concrete class for TimedTask
class TimedTask extends Task {
    private String timeline;

    public TimedTask(String description, String timeline) {
        super(description);
        this.timeline = timeline;
    }

    public String getTimeline() {
        return timeline;
    }

    @Override
    public void displayTask() {
        System.out.println("Timed Task: " + description + " (Timeline: " + timeline + ")" + (isCompleted ? " (Completed)" : ""));
    }
}

// Class representing a To-Do List
class ToDoList {
    private List<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        System.out.println("Tasks in To-Do List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". ");
            tasks.get(i).displayTask();
        }
    }

    public void markTaskCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
            System.out.println("Task marked as completed: " + tasks.get(index).getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }
}

// Main class
public class Assignment {
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a task");
            System.out.println("2. Display tasks");
            System.out.println("3. Mark a task as completed");
            System.out.println("4. Exit");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Select task type:");
                    System.out.println("1. ToDo Task");
                    System.out.println("2. Deadline Task");
                    System.out.println("3. Timed Task");

                    int taskType;
                    try {
                        taskType = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // Clear invalid input
                        continue;
                    }

                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();

                    switch (taskType) {
                        case 1:
                            toDoList.addTask(new ToDoTask(description));
                            break;
                        case 2:
                            System.out.print("Enter deadline: ");
                            String deadline = scanner.nextLine();
                            toDoList.addTask(new DeadlineTask(description, deadline));
                            break;
                        case 3:
                            System.out.print("Enter timeline: ");
                            String timeline = scanner.nextLine();
                            toDoList.addTask(new TimedTask(description, timeline));
                            break;
                        default:
                            System.out.println("Invalid task type.");
                            break;
                    }
                    break;

                case 2:
                    toDoList.displayTasks();
                    break;

                case 3:
                    System.out.print("Enter the index of the task to mark as completed: ");
                    int index;
                    try {
                        index = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // Clear invalid input
                        continue;
                    }
                    toDoList.markTaskCompleted(index - 1); // Adjust index to 0-based
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }
}
