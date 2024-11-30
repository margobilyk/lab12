package ua.ucu.edu.apps.task2;

public class Main {
    public static void main(String[] args) {
        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(x -> System.out.println("Nested task: " + x)))
                  .addTask(new Signature<>(x -> System.out.println("Nested squared: " + (x * x))));

        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup)
             .addTask(new Signature<>(x -> System.out.println("Cubed: " + (x * x * x))));

        group.apply(10);

        System.out.println("Group UUID: " + group.getHeader("groupId"));
        for (Task<Integer> task : group.getTasks()) {
            System.out.println("Task UUID: " + task.getId());
            System.out.println("Task Group UUID: " + task.getHeader("groupId"));
        }
    }
}
