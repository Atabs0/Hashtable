public class SimpleHashtable {
    private StoredEmployee[] hashtable;
    public SimpleHashtable(){
        hashtable = new StoredEmployee[10];
    }

    public void put(String key,Employee employee){
        int hashedkey=hashkey(key);
        if(occupied(hashedkey)){  //linear probing starts here.
            int stopIndex = hashedkey;
            if(hashedkey == hashtable.length-1){
                hashedkey=0;
            }
            else{
                hashedkey++;
            }
            while(occupied(hashedkey) && hashedkey!=stopIndex){
                hashedkey=(hashedkey+1)%hashtable.length;
            }
        }
        if(occupied(hashedkey)){
            System.out.println("Sorry, there is already an employee at postion "+hashedkey);
        }
        else{
            hashtable[hashedkey]=new StoredEmployee(key,employee);
        }
    }
   public Employee get(String key){
        int hashedkey =findkey(key);
        if (hashedkey==-1){
            return null;
        }
        return hashtable[hashedkey].employee;
   }
   public Employee remove (String key){
        int hashedkey = findkey(key);
        if (hashedkey==-1){
            return null;
        }
        Employee employee = hashtable[hashedkey].employee;
        hashtable[hashedkey]=null;                             //rehashing is done here to avoid bugs in the code
       StoredEmployee [] oldHashtable = hashtable;
       for (int i=0; i<oldHashtable.length; i++){
           if (oldHashtable[i] != null){
               put(oldHashtable[i].key, oldHashtable[i].employee);
           }
       }
         return employee;
   }
    private int hashkey(String key){
        return key.length()%hashtable.length;
    }
    private int findkey(String key){
        int hashedkey =hashkey(key);  ////linear probing starts here.
        if(hashtable[hashedkey]!=null && hashtable[hashedkey].key.equals(key)){
            return hashedkey;
        }

        int stopIndex = hashedkey;
        if (hashedkey == hashtable.length - 1) {
            hashedkey = 0;
        } else {
            hashedkey++;
        }
        while ( hashedkey != stopIndex && hashtable[hashedkey]!=null && !hashtable[hashedkey].key.equals(key)) {
            hashedkey = (hashedkey + 1) % hashtable.length;
        }
        if (hashtable[hashedkey]!=null && hashtable[hashedkey].key.equals(key)) {
             return hashedkey;
        }
        else {
            return -1;
        }

    }
    private boolean occupied(int index){
        return hashtable[index]!=null;
    }
    public void printHashtable(){
        for(int i=0; i<hashtable.length; i++){
            if (hashtable[i]==null){
                System.out.println("Empty");
            }
            else{
                System.out.println("Position "+i +": "+hashtable[i]);
            }


        }
    }

}
