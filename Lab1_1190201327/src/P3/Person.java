package P3;

import java.util.*;
public class Person {
	private String name;
	private List<Person> FriendshipList;
	public Person(String name)//��ʼ���������������Ѽ���
	{
		this.name = name;
		this.FriendshipList = new ArrayList<>();
	}
	public String whatName()//���ش�������
	{
		return this.name;
	}
	public List<Person> whatFriend()//���ش������Ѽ���
	{
		return this.FriendshipList;
	}
	public void addFriend(Person people)
	{
		this.FriendshipList.add(people);
	}
}
