import org.junit.jupiter.api.Test;

import ua.ucu.edu.apps.task2.Group;
import ua.ucu.edu.apps.task2.Signature;
import ua.ucu.edu.apps.task2.Task;

import static org.junit.jupiter.api.Assertions.*;


class GroupTest {

    @Test
    void testAddTask() {
        Group<Integer> group = new Group<>();
        Signature<Integer> signature = new Signature<>(x -> System.out.println(x));
        group.addTask(signature);
        assertEquals(1, group.getTasks().size());
        assertTrue(group.getTasks().contains(signature));
    }

    @Test
    void testFreezeGroupId() {
        Group<Integer> group = new Group<>();
        group.addTask(new Signature<>(x -> System.out.println(x)));
        group.freeze();
        String groupId = group.getHeader("groupId");
        assertNotNull(groupId);
        for (Task<Integer> task : group.getTasks()) {
            assertEquals(groupId, task.getHeader("groupId"));
        }
    }

    @Test
    void testApplyExecutionOrder() {
        StringBuilder result = new StringBuilder();
        Group<Integer> group = new Group<>();
        group.addTask(new Signature<>(x -> result.append("Task1:").append(x).append(" ")));
        group.addTask(new Signature<>(x -> result.append("Task2:").append(x * x).append(" ")));
        group.apply(5);
        assertEquals("Task1:5 Task2:25 ", result.toString());
    }

    @Test
    void testNestedGroups() {
        StringBuilder result = new StringBuilder();
        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(x -> result.append("NestedTask1:").append(x).append(" ")));
        nestedGroup.addTask(new Signature<>(x -> result.append("NestedTask2:").append(x * x).append(" ")));

        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup);
        group.addTask(new Signature<>(x -> result.append("OuterTask:").append(x * x * x).append(" ")));

        group.apply(3);
        assertEquals("NestedTask1:3 NestedTask2:9 OuterTask:27 ", result.toString());
    }

}
