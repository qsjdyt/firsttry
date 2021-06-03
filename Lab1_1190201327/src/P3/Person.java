package P3;

import java.util.*;
public class Person {
	private String name;
	private List<Person> FriendshipList;
	public Person(String name)//初始化此人姓名和朋友集合
	{
		this.name = name;
		this.FriendshipList = new ArrayList<>();
	}
	public String whatName()//返回此人姓名
	{
		return this.name;
	}
	public List<Person> whatFriend()//返回此人朋友集合
	{
		return this.FriendshipList;
	}
	public void addFriend(Person people)
	{
		this.FriendshipList.add(people);
	}
}
