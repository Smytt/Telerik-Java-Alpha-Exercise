public class ReverseString {
    public static void main(String[] args) {
        System.out.println(reverse("Telerik"));
    }

    static String reverse(String str) {
        if(str.length() == 0) {
            return str;
        }
        String rest = str.substring(1);
        return reverse(rest) + str.charAt(0);
    }
}
