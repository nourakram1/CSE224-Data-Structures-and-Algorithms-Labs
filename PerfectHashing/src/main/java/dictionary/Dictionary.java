package dictionary;

import java.util.List;

import table.Table;

public class Dictionary {

    private final Table<DictionaryEntry> table;

    public Dictionary(Table<DictionaryEntry> table) {
        this.table = table;
    }

    public void insert(String word) {
        DictionaryEntry entry = new DictionaryEntry(word);
        boolean inserted = table.insert(entry);
        if (!inserted) {
            System.out.println("Insertion failed, entry already exists");
            return;
        }
        System.out.println("Inserted: " + word);
    }

    public void search(String word) {
        DictionaryEntry entry = new DictionaryEntry(word);
        boolean found = table.contains(entry);
        if (!found) {
            System.out.println("Entry not found");
            return;
        }
        System.out.println("Found: " + word);
    }

    public void delete(String word) {
        DictionaryEntry entry = new DictionaryEntry(word);
        boolean removed = table.remove(entry);
        if (!removed) {
            System.out.println("Deletion failed, entry does not exist");
            return;
        }
        System.out.println("Deleted: " + word);
    }

    public void batchInsert(List<String> words) {
        List<DictionaryEntry> inserted = table.insertAll(words.stream().map(DictionaryEntry::new).toList());
        System.out.println("Inserted " + inserted.size() + " words.");
        System.out.println("Failed to insert " + (words.size() - inserted.size()) + " words.");
    }

    public void batchDelete(List<String> words) {
        List<DictionaryEntry> removed = table.removeAll(words.stream().map(DictionaryEntry::new).toList());
        System.out.println("Deleted " + removed.size() + " words.");
        System.out.println("Failed to delete " + (words.size() - removed.size()) + " words.");
    }
}