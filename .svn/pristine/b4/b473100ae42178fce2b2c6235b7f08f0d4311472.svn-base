package humanhesourcescontract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.ohj2.WildChars;
import humanresourcesemployee.Employee;
import humanresourcesemployee.SailoException;
import humanresourcesemployee.Employees.EmployeesIterator;

/**
 * add a new contract
 *
 * @author Saad Turky
 * @version 1.0, 9.05.2019
 * @version 23.04.2019 - files in the directory
 */
public class Contracts implements Iterable<Contract> {
	private static final int MAX_Contracts   = 5;
    private int              lkm           = 0;
    private String tiedostonPerusNimi ="contracts";
    private Contract[] objects= new Contract[MAX_Contracts];
    private boolean muutettu = false; 
    
    
    public Contracts() {
    	//
    }
   
    
    public void lisaa(Contract contract)   {
    	 if (lkm >= objects.length)  {
             objects = Arrays.copyOf(objects, objects.length+10);
           }      
           objects[lkm++] = contract;
           muutettu = true;
//        this.objects[lkm] = contract;
//        lkm++;
    }
    
   
    public Contract anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return objects[i];
        
    }
    
    
    public int getLkm() {
        return this.lkm;
    }
    
    
    public Contract newContract() {
	    Contract neww = new Contract();
	    neww.rekisteroi();
	    neww.vastaaAkuAnkka();
//	    try {
	        lisaa(neww);
	          return neww;
//	    } catch (SailoException e) {
//	        Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
	      
//	    }
	    //hae(neww.getEmployeeNumber());
//	    return null;
}
 
    
    public void korvaaTaiLisaa(Contract contract) throws SailoException {
		 int id = contract.getContractNumber();
		 for(int i =0; i<lkm; i++) {
			 //System.out.println(objects[i].getContractNumber());
			 if(objects[i].getContractNumber()==id) {
				 System.out.println("Are we here?");  //just for testing
				 objects[i]= contract;
				 muutettu=true;
				 return;
			 }
		 }
		 lisaa(contract);
	  }
    
    
    /**
     * Class for iteration of contracts.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Contracts < = new Contracts();
     * Contract aku1 = new Contract(), aku2 = new Contract();
     * aku1.rekisteroi(); aku2.rekisteroi();
     *
     * contracts.lisaa(aku1); 
     * contracts.lisaa(aku2); 
     * contracts.lisaa(aku1); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Contract contract:contracts)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+contract.getContractNumber());           
     * 
     * String tulos = " " + aku1.getContractNumber() + " " + aku2.getContractNumber() + " " + aku1.getContractNumber();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Contract>  i=contracts.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Contract contract = i.next();
     *   ids.append(" "+contract.getContractNumber());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Contract>  i=contracts.iterator();
     * i.next() == aku1  === true;
     * i.next() == aku2  === true;
     * i.next() == aku1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class ContractsIterator implements Iterator<Contract> {
   	 private int kohdalla = 0;

   	 
   	 /**
      * Is there another contract yet
      * @see java.util.Iterator#hasNext()
      * @return true if there are still contracts
      */
     @Override
     public boolean hasNext() {
         return kohdalla < getLkm();
     }


     /**
      * Give the next contract
      * @return next contract
      * @throws NoSuchElementException if the next item no longer exists
      * @see java.util.Iterator#next()
      */
     @Override
     public Contract next() throws NoSuchElementException {
         if ( !hasNext() ) throw new NoSuchElementException("Ei oo");
         return anna(kohdalla++);
     }


     /**
      * Destruction has not been carried out
      * @throws UnsupportedOperationException aina
      * @see java.util.Iterator#remove()
      */
     @Override
     public void remove() throws UnsupportedOperationException {
         throw new UnsupportedOperationException("Me ei poisteta");
     }
 }
    
    
    /**
	    * Returning the iterator from its contracts.
	    * @return contract iterator
	    */
	   @Override
	   public Iterator<Contract> iterator() {
	       return new ContractsIterator();
	   }
	
	 
	   /** 
	      * Returns references in the "table" for contracts matching the search criteria
	      * @param hakuehto hakuehto 
	      * @param k index of the field to be searched
	      * @return found contracts of the data structure
	      * @example 
	      * <pre name="test"> 
	      * #THROWS SailoException  
	      *   Contracts contracts = new Contracts();
	      *   Contract contract1 = new Contract(); contract1.parse("1|1199|20|1.7.2019|1.6.2020|8|10|One"); 
	      *   Contract contract2 = new Contract(); contract2.parse("2|7134|20|1.7.2019|1.6.2020|8|10|two"); 
	      *   Contract contract3 = new Contract(); contract3.parse("3|6380|20|1.7.2019|1.6.2020|8|10|three"); 
	      *   Contract contract4 = new Contract(); contract4.parse("4|1106|20|1.7.2019|1.6.2020|8|10|four"); 
	      *   Contract contract5 = new Contract(); contract5.parse("5|7898|20|1.7.2019|1.6.2020|8|10|five"); 
	      *   contracts.lisaa(contract1); contracts.lisaa(contract2); contracts.lisaa(contract3); contracts.lisaa(contract4); contracts.lisaa(contract5);
	      *   List<Employee> loytyneet;  
	      *   loytyneet = (List<Contract>)contracts.etsi("*s*",1);  
	      *   loytyneet.size() === 2;  
	      *   loytyneet.get(0) == contract3 === true;  
	      *   loytyneet.get(1) == contract4 === true;  
	      *     
	      *   loytyneet = (List<Contract>)contracts.etsi("*7-*",2);  
	      *   loytyneet.size() === 2;  
	      *   loytyneet.get(0) == contract3 === true;  
	      *   loytyneet.get(1) == contract5 === true; 
	      *     
	      *   loytyneet = (List<Contract>)contracts.etsi(null,-1);  
	      *   loytyneet.size() === 5;  
	      * </pre> 
	      */ 
	     public Collection<Contract> etsi(String hakuehto,final int k) {      
			String ehto = "*"; 
	         if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
	         int hk = k; 
	         if ( hk < 0 ) hk = 1;
	         List<Contract> loytyneet = new ArrayList<Contract>(); 
	         for (Contract employee : this) { 
	            if (WildChars.onkoSamat(employee.anna(hk), ehto)) 
	            	 loytyneet.add(employee);   
	         } 
	         //  TODO: lajittelua varten vertailija 
	         var vertailija =new Contract.Vertailija(hk);
	         Collections.sort(loytyneet,vertailija);
	         return loytyneet; 
	     }
	     
	     
    public static void main(String[] args)  {
		Contracts contracts = new Contracts();
		
		Contract aku= new Contract();
		Contract aku2= new Contract();
		
		aku.rekisteroi();
		aku.vastaaAkuAnkka();
		aku2.rekisteroi();
		aku2.vastaaAkuAnkka();
		
//		  try {
		contracts.lisaa(aku);
		contracts.lisaa(aku2);
		
//		  } catch (SailoException ex) {
//	            System.out.println(ex.getMessage());
//	        }
		  System.out.println("============= Contracts test =================");
		  for (int i = 0; i < contracts.getLkm(); i++) {
              Contract contract = contracts.anna(i);
              System.out.println("Contract nro: " + i);
              contract.tulosta(System.out);
          }
	}
   
    public int poista1(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            objects[i] = objects[i + 1]; 
        objects[lkm] = null; 
        muutettu = true; 
        return 1; 
    } 
    
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == objects[i].getEmployeeNumber()) return i; 
        return -1; 
    } 

    
    /** 
	  * Palauttaa varakopiotiedoston nimen 
	  * @return varakopiotiedoston nimi 	
	  */   
	    public String getBakNimi() { 
	        return tiedostonPerusNimi + ".bak"; 
 } 
	    
	    
	    public void tallenna() throws SailoException { 
        if ( !muutettu ) return; 
	        File fbak = new File(getBakNimi()); 
	        File ftied = new File(getTiedostonNimi()); 
	        fbak.delete(); // if .. System.err.println("Ei voi tuhota"); 
	        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä"); 
	        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) { 
	           // fo.println(getKokoNimi()); 
	            fo.println(objects.length); 
	            for (Contract contract : objects) { 
	            	if (contract != null)
	                fo.println(contract.toString()); 
	            } 
	            //} catch ( IOException e ) { // ei heitä poikkeusta 
	            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage()); 
	        } catch ( FileNotFoundException ex ) { 
	            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea"); 
	        } catch ( IOException ex ) { 
	            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia"); 
	        } 
	        muutettu = false; 
	    }

	   
	public String getTiedostonNimi() { 
		        return getTiedostonPerusNimi() + ".dat"; 
		    } 
	
	
	public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }
	
	
	 public void setTiedostonPerusNimi(String nimi) { 
	        tiedostonPerusNimi = nimi; 
	    }
	 
	 
	 public void lueTiedostosta(String tied) throws SailoException { 
		 
	        setTiedostonPerusNimi(tied); 
	 
	        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) { 
		            
	            String rivi = fi.readLine(); 
	 
	            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu"); 
		 
	            while ( (rivi = fi.readLine()) != null ) { 
	                rivi = rivi.trim(); 
	 
	                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;  
	                Contract contract = new Contract(); 
	                contract.parse(rivi); // voisi olla virhekäsittely 
	                lisaa(contract);
	            } 
	            muutettu = false; 
	        } catch ( FileNotFoundException e ) { 
	            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea"); 
	 
	        } catch ( IOException e ) { 
	            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage()); 
	        } 
	    }  
		
	 
	 public void lueTiedostosta() throws SailoException { 
	        lueTiedostosta(getTiedostonPerusNimi()); 
	     }
}
