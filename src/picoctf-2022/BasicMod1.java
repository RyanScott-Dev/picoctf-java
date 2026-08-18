void main() throws IOException{
    String input = new String(System.in.readAllBytes(), StandardCharsets.UTF_8);
    IO.println(solve(input.strip()));
}

String solve(String input) {
    String lut = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    String[] numStrings = input.split(" ");
    String solution = "picoCTF{";
    for (String numString: numStrings){
        solution += lut.charAt(Integer.parseInt(numString) % 37);
    }
    solution += '}';

    return solution;
}