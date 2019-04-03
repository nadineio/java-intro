public class Entry {
    public static Entry[] entryList = new Entry[200];
    public static int numberOfEntries;

    public String name;
    public String quantity;
    public String notes;
    
    public Entry() {
        this.name = " ";
        this.quantity = " ";
        this.notes = " ";
    }

    public Entry(String name, String quantity, String notes) {
        this.name = name;
        this.quantity = quantity;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public static void sortArray() {
        Entry exchange;
        boolean flag;
        
        flag = true;
        
        while (flag) {
            flag = false;
            for (int i = 0; i < numberOfEntries - 1; i++) {
                if ((entryList[i] != null) && (entryList[i].name.compareToIgnoreCase(entryList[i+1].name)) > 0) {
                    exchange = entryList[i];
                    entryList[i] = entryList[i+1];
                    entryList[i + 1] = exchange;
                    flag = true;
                }
            }
        }  
    }
}