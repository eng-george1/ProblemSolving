package Array;

public class arrayList {
  public static void main(String[] args) {
    ImplementArray array = new ImplementArray(5);
    array.insert(0, 1);
    array.assign(0, "A");
    array.assign(1, "B");
    array.assign(2, "C");
    array.assign(3, "D");
    array.assign(4, "E");
    array.insert(0, 1);
    array.insert(7, 1);
    System.out.println(array.toString());
    array.delete(6);
    array.delete(0);
    array.delete(5);
    array.Shrink();
    System.out.println(array.toString());

    // Reverse
    System.out.println(Reverse.reverse("s"));
    System.out.println(Reverse.reverse(null));
    System.out.println(Reverse.reverse("Hello world"));

  }

}
