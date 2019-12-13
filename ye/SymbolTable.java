import java.util.*;
public class SymbolTable {

	Hashtable<String, LinkedList<String>> symbolTable;
	Hashtable<String, String> typeHT;
	Hashtable<String, String> descriptionHT;

	SymbolTable(){
		this.symbolTable = new Hashtable<>();
		this.typeHT = new Hashtable<>();
		this.descriptionHT = new Hashtable<>();
		
		symbolTable.put("program", new LinkedList<>());
	}
	public void put(String id, String type, String information, String scope) {
        LinkedList<String> list = symbolTable.get(scope);
        if(list == null) {
            list = new LinkedList<>();
            list.add(id);
            symbolTable.put(scope, list);
        }
        else {
            list.addFirst(id);
        }
        typeHT.put(id+scope, type);
        descriptionHT.put(id+scope, information);
    }
    	

	public void get(String id, String type, String scope) 
	{
		LinkedList<String> list = symbolTable.get(scope);
		if(list == null) 
		{
			System.out.println("Variable " + id + " not declared in " + scope);
		}     
	}

	public String getType(String id, String scope) 
	{
		String type = typeHT.get(id+scope);
		if(type != null) {
			return type;
		}
		else {
			type = typeHT.get(id+"program");
			if(type != null) 
			{
				return type;
			}
		}
		return null;
	}

	public String getDescription(String id, String scope) 
	{
        String description = descriptionHT.get(id+scope);
        if(description != null) {
            return description;
        }
        else {
            description = descriptionHT.get(id+"global");
            if(description != null) {
                return description;
            }
        }
        return null;
    }

    public LinkedList<String> getScopeTable(String scope) 
    {
        return symbolTable.get(scope);
    }

    public int getParamater(String id) 
    {
    	LinkedList<String> scopelist = symbolTable.get(id);
		int count = 0;
        for(int i = 0; i < scopelist.size(); i++) {
            String description = descriptionHT.get(scopelist.get(i)+id);
            if(description.equals("param")) {
                count++;
            }
        }
        return count;
    }



    public String getParamaterType(int index, String scope) 
	{
		int count = 0;
		LinkedList<String> Listids = symbolTable.get(scope);
		for(String id : Listids) 
		{
			String type = typeHT.get(id+scope);
			String description = descriptionHT.get(id+scope);
			if(description.equals("param")){
				count++;
				if(count == index){
					return type;
				}
			}
		}
		return null;
	}

	public boolean getDuplicates(String id, String scope) 
	{
        LinkedList<String> list = symbolTable.get(scope);
        LinkedList<String> global_list = symbolTable.get("program");
        if(scope.equals("program")) {
            return global_list.indexOf(id) == global_list.lastIndexOf(id);
        }
        return ((list.indexOf(id) == list.lastIndexOf(id)) && (global_list.indexOf(id) == -1));
        
    }

    public ArrayList<String> getFunctions() 
    {
    	LinkedList<String> list = symbolTable.get("global");
    	ArrayList<String> functions = new ArrayList<String>();
    	for(int i = 0; i < list.size(); i++) {
    		String description = descriptionHT.get(list.get(i)+"global");
    		if(description.equals("function")){
    			functions.add(list.get(i));
    		}
        }
        return functions;
    }
    public boolean isFunction(String id) 
    {
    	LinkedList<String> list = symbolTable.get("global");
		ArrayList<String> functions = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			String description = descriptionHT.get(list.get(i)+"global");
			if(description.equals("function") && list.get(i).equals(id)) {
				return true;
			}
        }
        return false;
    }
	public void printSymbolTable() {
		Enumeration e = symbolTable.keys();
		while(e.hasMoreElements()) {
			String scope = (String) e.nextElement();
			System.out.println("Scope: " + scope);
			LinkedList<String> Listids = symbolTable.get(scope);
			for(String id : Listids) {
				String type = typeHT.get(id+scope);
				String description = descriptionHT.get(id+scope);
				System.out.println("[" + id + ", " + type + ", " + description + "]");
            }
            System.out.println();
        }
    }


	  

}

	


	