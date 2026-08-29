void main(String[] args) throws IOException{
    String cipherText = Files.readString(Path.of(args[0])).strip();
    int railCount = Integer.parseInt(args[1]);
    System.out.println(decode(cipherText, railCount));
}

String decode(String cipherText, int railCount) {

    StringBuilder plainText = new StringBuilder();
    for (int i = 0 ; i < cipherText.length(); i++){
        plainText.append(cipherText.charAt(cipherIndexFor(i, railCount, cipherText.length())));
    }

    return plainText.toString();
}

int cipherIndexFor(int plainTextIndex, int railCount, int textLength){
    int railIndex = railIndexFor(plainTextIndex, railCount);
    int railStartCharIndex = 0;

    for (int i = 0; i < railIndex; i++){
        railStartCharIndex += railTextLength(i, textLength, railCount);
    }

    return railStartCharIndex + offsetWithinRail(plainTextIndex, railCount);
}

int offsetWithinRail(int plainTextIndex, int railCount){
    int railIndex = railIndexFor(plainTextIndex, railCount);
    boolean isEndRail = ((railIndex == 0) || (railIndex == railCount - 1));
    int period = 2 * (railCount - 1);
    int completeCycles = plainTextIndex/period;
    int offset = isEndRail ? completeCycles : completeCycles * 2;

    int positionInRailCycle = plainTextIndex % period;
    for (int i = 0; i < positionInRailCycle; i++){
        if (railIndexFor(i, railCount) == railIndex){
            offset++;
        }
    }

    return offset;
}

int railTextLength(int railIndex, int textLength, int railCount){
    boolean isEndRail = ((railIndex == 0) || (railIndex == railCount - 1));
    int period = 2 * (railCount - 1);
    int numberOfCharacters = isEndRail ? textLength / period : (textLength / period) * 2;

    int remainingCharacters = textLength % period;
    for (int i = 0; i < remainingCharacters; i++){
        if (railIndexFor(i, railCount) == railIndex){
            numberOfCharacters++;
        }
    }

    return numberOfCharacters;
}

int railIndexFor(int plainTextIndex, int railCount){
    int period = 2 * (railCount - 1);
    return (-1 * Math.abs((plainTextIndex % period) - (railCount - 1))) + railCount - 1;
}