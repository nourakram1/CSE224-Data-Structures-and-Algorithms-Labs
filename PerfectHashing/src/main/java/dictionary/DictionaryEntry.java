package dictionary;

import org.apache.commons.lang3.StringUtils;
import representation.BinaryRepresentable;

public class DictionaryEntry implements BinaryRepresentable {

    private final String word;
    private final String binaryRepresentation;
    private static final int BITS_PER_CHAR = 8;
    private static final int MAX_WORD_LENGTH = 45;

    public DictionaryEntry(String word) {
        this.word = word;
        this.binaryRepresentation = getBinaryRepresentation(word);
    }

    @Override
    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DictionaryEntry
                && ((DictionaryEntry) obj).getBinaryRepresentation().equals(this.binaryRepresentation);
    }

    public String getWord() {
        return word;
    }

    private String getBinaryRepresentation(String word) {
        if (word.length() > MAX_WORD_LENGTH)
            throw new IllegalArgumentException(String.format("Max word length exceeded: expected ≤ %d, got %d",
                    MAX_WORD_LENGTH,
                    word.length()
            ));

        word = word.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(StringUtils.leftPad(Integer.toBinaryString(c), BITS_PER_CHAR, '0'));
        }
        return StringUtils.leftPad(sb.toString(), MAX_WORD_LENGTH * BITS_PER_CHAR, '0');
    }
}