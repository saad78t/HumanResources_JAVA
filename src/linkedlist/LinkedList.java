package linkedlist;

public class LinkedList {
private ListNode head=null;

public void print() {
   ListNode i = head;//refers to the listnode
	
	while(i!=null) {
		System.out.println(i.value);
		i = i.next;
	}
}

//Adding values to the linked list
public void add(String speciality, int index) {
	ListNode newnode = new ListNode(speciality);
	if(index==0) {
		newnode.next=head;
		head=newnode;
	}
	else {
		ListNode i = head;
		for(int count=0; count<index-1; count++) {
			i=i.next;
		}
		newnode.next=i.next;
		i.next=newnode;
	}
}


public void remove (int index) {
	if(index==0) {
		head=head.next;
	}
	else {
		ListNode i = head;
		for(int count=0; count<index-1; count++) {
			i=i.next;
		}
		ListNode j = i.next;
		i.next=j.next;
	}
}
}
