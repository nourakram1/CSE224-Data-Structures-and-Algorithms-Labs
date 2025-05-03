package dictionary;

import java.util.List;

import input.Input;
import table.Hashtable;
import table.HierarchicalHashtable;
import table.Table;

public class Dictionary {

    private Table<BitString> table;

    public Dictionary(boolean twolevel) {
        if(twolevel) {
            table = new HierarchicalHashtable<>();
        } else {
            table = new Hashtable<>();
        }
    }
    public String getBinaryRepresentation(String word) {
        StringBuilder binary = new StringBuilder();

        word = word.toLowerCase(); // Convert to lowercase to handle case insensitivity

        for (char c : word.toCharArray()) {
            int value = c - 'a' + 1;  //'a' = 1, 'b' = 2, ..., 'z' = 26
            String bin = String.format("%5s", Integer.toBinaryString(value)).replace(' ', '0');
            binary.append(bin);
        }

        // Pad the binary string to 225 bits
        while (binary.length() < 225) {
            binary.append("0");
        }

        return binary.toString();
    }

    public void insert(String word) {
        String binaryRepresentation = getBinaryRepresentation(word);
        BitString bitString = new BitString(binaryRepresentation);
        boolean inserted = table.insert(bitString);
        if (!inserted) {
            System.out.println("Insertion failed");
            return;
        }
        System.out.println("Inserted: " + word);
    }

    public boolean search(String word) {
        String binaryRepresentation = getBinaryRepresentation(word);
        BitString bitString = new BitString(binaryRepresentation);
        boolean found = table.contains(bitString);
        if (found) {
            System.out.println("Found: " + word);
        } else {
            System.out.println("Not found: " + word);
        }
        return found;
    }

    public void delete(String word) {
        String binaryRepresentation = getBinaryRepresentation(word);
        BitString bitString = new BitString(binaryRepresentation);
        boolean removed = table.remove(bitString);
        if (removed) {
            System.out.println("Deleted: " + word);
        } else {
            System.out.println("Deletion failed: " + word + " not found");
        }
    }

    public void batchInsert(String filePath) {
        Input file= new Input();
        List<String> words = file.readFile(filePath);
        table.insertAll(words.stream().map(this::getBinaryRepresentation).map(BitString::new).toList());
        System.out.println("Inserted " + words.size() );
    }

    public void batchDelete(String filePath) {
        Input file= new Input();
        List<String> words = file.readFile(filePath);
        table.removeAll(words.stream().map(this::getBinaryRepresentation).map(BitString::new).toList());
        System.out.println("Deleted " + words.size() );
    }

}
