package P3;

import org.junit.Test;
import static org.junit.Assert.*;

public class FriendshipGraphTest {
	@Test
	public void testaddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person per1 = new Person("per1");
		graph.addVertex(per1);
		assertEquals(per1, graph.allPerson.get(graph.allPerson.size() - 1));
	}
	
	@Test
	public void testaddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person per1 = new Person("per1");
		Person per2 = new Person("per2");
		graph.addVertex(per1);
		graph.addVertex(per2);
		graph.addEdge(per1, per2);
		assertEquals(per2, per1.whatFriend().get(per1.whatFriend().size() - 1));
	}
	@Test
	public void testgetDistance()
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		Person d = new Person("d");
		Person e = new Person("e");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addEdge(a, b);
		graph.addEdge(b, a);
		graph.addEdge(b, c);
		graph.addEdge(c, b);
		graph.addEdge(c, d);
		graph.addEdge(d, c);
		graph.addEdge(b, d);
		graph.addEdge(d, b);
		assertEquals(0, graph.getDistance(a, a));
		assertEquals(1, graph.getDistance(a, b));
		assertEquals(2, graph.getDistance(a, c));
		assertEquals(2, graph.getDistance(a, d));
		assertEquals(-1, graph.getDistance(a, e));

	}
}
