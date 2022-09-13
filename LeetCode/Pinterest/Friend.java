package LeetCode.Pinterest;

import java.util.Collection;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;

public class Friend {
    private Collection<Friend> friends;
    private String email;

    public Friend(String email) {
        this.email = email;
        this.friends = new ArrayList<Friend>();
    }

    public String getEmail() {
        return email;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public void addFriendship(Friend friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    // List<Integer> ans = new ArrayList<>();
    // if (root == null)
    // return ans;
    // Queue<TreeNode> queue = new LinkedList<TreeNode>();
    // queue.add(root);
    // while (!queue.isEmpty()) {
    // TreeNode t = queue.poll();
    // ans.add(t.val);
    // if (t.left != null)
    // queue.add(t.left);
    // if (t.right != null)
    // queue.add(t.right);
    // }
    // return ans;
    public boolean canBeConnected(Friend friend) {
        Queue<Friend> queue = new LinkedList<>();
        queue.add(friend);
        Set<Friend> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Friend cur = queue.poll();
            visited.add(cur);
            Collection<Friend> temp = cur.getFriends();
            for (Friend m : temp) {
                if (visited.contains(m))
                    continue;
                if (this.friends.contains(cur))
                    return true;
                visited.add(m);
                queue.add(m);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Friend a = new Friend("A");
        Friend b = new Friend("B");
        Friend c = new Friend("C");

        a.addFriendship(b);
        b.addFriendship(c);

        System.out.println(a.canBeConnected(c));
    }
}