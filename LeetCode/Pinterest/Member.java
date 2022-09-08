package LeetCode.Pinterest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Member {
    private String email;
    private Collection<Member> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return !(email != null ? !email.equals(member.email) : member.email != null);

    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    public Member(String email) {
        this(email, new ArrayList<Member>());
    }

    public Member(String email, Collection<Member> friends) {
        this.email = email;
        this.friends = friends;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Member> getFriends() {
        return friends;
    }

    public void addFriends(Collection<Member> friends) {
        this.friends.addAll(friends);
    }

    public void addFriend(Member friend) {
        friends.add(friend);
    }
}
