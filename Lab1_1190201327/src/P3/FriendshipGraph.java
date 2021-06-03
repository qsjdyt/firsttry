package P3;

import java.util.*;

public class FriendshipGraph {
	public List<Person> allPerson = new ArrayList<Person>();//�����˵��б�
	public Set<String> set = new HashSet<String>();//���������ֵļ��ϣ��ж��Ƿ�����
	
	public void addVertex(Person newp)//�Ӷ��㣬��������
	{
		if(set.contains(newp.whatName())) {
			System.out.printf("��������");
			System.exit(1);
		}
		allPerson.add(newp);
		set.add(newp.whatName());
	}
	
	public void addEdge(Person per1, Person per2)
	{
		per1.addFriend(per2);
	}
	
	public int getDistance(Person per1, Person per2)
	{
		if(per1 == per2) return 0;
		Map<Person, Integer> message = new HashMap<>();
		Queue<Person> BFS = new LinkedList<>();
		BFS.add(per1);
		message.put(per1, 0);
		while(!BFS.isEmpty())
		{
			Person head = BFS.poll();
			List<Person> Friend = head.whatFriend();
			for(Person p : Friend)
			{
				if(!message.containsKey(p))//��δ���뼯���У�flag = 0;
				{
					BFS.add(p);
					message.put(p, message.get(head) + 1);
					if(p == per2) return message.get(per2);
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[])
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben));
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		//should print -1

	}
}
