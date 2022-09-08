package LeetCode.Pinterest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Friends {
    public static List<Member> getFriendsOfDegree(Member member, int degree) {
        List<Member> res = new ArrayList<>();

        Queue<Member> queue = new LinkedList<>();

        queue.offer(member);

        Set<Member> visited = new HashSet<>();

        while (degree > 0) {
            if (queue.isEmpty())
                break;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Member cur = queue.poll();
                visited.add(cur);
                Collection<Member> temp = cur.getFriends();
                for (Member m : temp) {
                    if (visited.contains(m))
                        continue;
                    visited.add(m);
                    queue.offer(m);
                }
            }
            degree--;
        }

        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }

        return res;
    }

    public static void main(String[] args) {
        Member a = new Member("A");
        Member b = new Member("B");
        Member c = new Member("C");
        Member d = new Member("D");
        Member e = new Member("E");
        Member f = new Member("F");


        a.addFriend(b);
        a.addFriend(d);
        a.addFriend(f);
        b.addFriend(c);
        d.addFriend(e);
        f.addFriend(e);

        for (Member friend : getFriendsOfDegree(a, 2))
            System.out.println(friend.getEmail());
    }
}
