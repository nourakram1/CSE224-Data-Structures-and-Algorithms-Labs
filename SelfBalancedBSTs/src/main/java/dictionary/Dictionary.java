package dictionary;

import tree.TreePrinter;
import tree.set.BSTSet;

import java.util.List;

public class Dictionary {

    private final BSTSet<String> bstSet;

    public Dictionary(BSTSet<String> bstSet) {
        this.bstSet = bstSet;
    }

    public void insert(String word) {
        boolean inserted = bstSet.insert(word);
        if (!inserted) {
            System.err.println("Insertion failed, entry already exists");
            return;
        }
        System.out.println("Inserted: " + word);
    }

    public void search(String word) {
        boolean found = bstSet.search(word);
        if (!found) {
            System.err.println("Entry not found");
            return;
        }
        System.out.println("Found: " + word);
    }

    public void delete(String word) {
        boolean removed = bstSet.delete(word);
        if (!removed) {
            System.err.println("Deletion failed, entry does not exist");
            return;
        }
        System.out.println("Deleted: " + word);
    }

    public void batchInsert(List<String> words) {
        List<String> inserted = bstSet.insertAll(words);
        System.out.println("Inserted " + inserted.size() + " words.");
        System.out.println("Failed to insert " + (words.size() - inserted.size()) + " words.");
    }

    public void batchDelete(List<String> words) {
        List<String> removed = bstSet.removeAll(words);
        System.out.println("Deleted " + removed.size() + " words.");
        System.out.println("Failed to delete " + (words.size() - removed.size()) + " words.");
    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(bstSet.getRoot());
    }
}