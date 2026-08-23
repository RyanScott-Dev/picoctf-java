void main() throws IOException{
    String input = new String(System.in.readAllBytes(), StandardCharsets.UTF_8);
    System.out.println("picoCTF{" + solve(input) + "}");
}

String solve(String input) {
    String lut = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    String[] numStrings = input.split(" ");
    String solution = "";
    for (String numString: numStrings){
        solution += lut.charAt(modInverse(Integer.parseInt(numString), 41) - 1); //modInverse returns 1-40, subtracted 1 to fit array
    }

    return solution;
}

int modInverse(int a, int mod){
    a = a % mod;
    for (int i = 1; i < mod; i++){
        if ((a*i) % mod == 1){
            return i;
        }
    }
    throw new IllegalArgumentException("ERROR - Modular Inverse Could Not Be Found - a: " + a + ", mod: " + mod);
}