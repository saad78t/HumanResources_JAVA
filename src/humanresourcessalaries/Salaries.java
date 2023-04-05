package humanresourcessalaries;

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
import humanhesourcescontract.Contract;
import humanhesourcescontract.Contracts.ContractsIterator;
import humanresourcesemployee.Employee;
import humanresourcesemployee.SailoException;

/**
 * add a new salary
 *
 * @author Saad Turky
 * @version 1.0, 9.05.2019
 * @version 23.04.2019 - files in the directory
 */
public class Salaries implements Iterable<Salary>{

	private static final int MAX_Salaries   = 5;
    private int              lkm           = 0;    
    private Salary[] objects= new Salary[MAX_Salaries];
    private String tiedostonPerusNimi = "salary"; 
    private boolean muutettu = false;
    
    public Salaries() {
    	//
    }
    
    
    public void lisaa(Salary salary)  {
        if (lkm >= objects.length) {        	
        	 objects = Arrays.copyOf(objects, objects.length+10);
        }      
        objects[lkm++] = salary;
        muutettu = true;
//        this.objects[lkm] = salary;
//        lkm++;
    }
    
    
    public Salary anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return objects[i];
    }
    
    
    public int getLkm() {
        return this.lkm;
    }
    
    
    public Salary newSalary() {
	    Salary neww = new Salary();
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
    
    public void korvaaTaiLisaa(Salary salary) throws SailoException {
		 int id = salary.getEmployeeNumber();
		 for(int i =0; i<lkm; i++) {
			 if(objects[i].getEmployeeNumber()==id) {
				 objects[i]= salary;
				 muutettu=true;
			 return;
			 }
		 }
		 lisaa(salary);
	  }
    
    
    public class SalariesIterator implements Iterator<Salary> {
      	 private int kohdalla = 0;

      	/**
          * Is there another salary yet
          * @see java.util.Iterator#hasNext()
          * @return true if there are still salaries
          */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Give the next salary
         * @return next salry
         * @throws NoSuchElementException if the next item no longer exists
         * @see java.util.Iterator#next()
         */
        @Override
        public Salary next() throws NoSuchElementException {
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
	    * Returning the iterator from its salaries.
	    * @return salary iterator
	    */
    @Override
	   public Iterator<Salary> iterator() {
	       return new SalariesIterator();
	   }
    
    
    public Collection<Salary> etsi(String hakuehto,final int k) {      
		String ehto = "*"; 
         if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
         int hk = k; 
         if ( hk < 0 ) hk = 1;
         List<Salary> loytyneet = new ArrayList<Salary>(); 
         for (Salary salary : this) { 
            if (WildChars.onkoSamat(salary.anna(hk), ehto)) 
            	 loytyneet.add(salary);   
         } 
         //  TODO: lajittelua varten vertailija 
         var vertailija =new Salary.Vertailija(hk);
         Collections.sort(loytyneet,vertailija);
         return loytyneet; 
     }
    
    
	public static void main(String[] args) {
		Salaries salaries = new Salaries();
		
		Salary aku= new Salary();
		Salary aku2= new Salary();
		
		aku.rekisteroi();
		aku.vastaaAkuAnkka();
		aku2.rekisteroi();
		aku2.vastaaAkuAnkka();
		
//		  try {
		salaries.lisaa(aku);
		salaries.lisaa(aku2);
		
//		  } catch (SailoException ex) {
//	            System.out.println(ex.getMessage());
//	        }
		  System.out.println("============= Salaries test =================");
		  for (int i = 0; i < salaries.getLkm(); i++) {
              Salary salary = salaries.anna(i);
              System.out.println("Employee nro: " + i);
              salary.tulosta(System.out);
          }
	}
	
	
	 public int poista2(int id) { 
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
	        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimet�"); 
	        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) { 
	           // fo.println(getKokoNimi()); 
	            fo.println(objects.length); 
	            for (Salary salary : objects) { 
	            	if (salary != null)
	                fo.println(salary.toString()); 
	            } 
	            //} catch ( IOException e ) { // ei heit� poikkeusta 
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

           // int maxKoko = Mjonot.erotaInt(rivi,10); // tehd��n jotakin 

           while ( (rivi = fi.readLine()) != null ) { 
               rivi = rivi.trim(); 

               if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;  
               Salary salary = new Salary(); 
               salary.parse(rivi); // voisi olla virhek�sittely 
               lisaa(salary);
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

   
   /** 
        * Palauttaa Kerhon koko nimen 
        * @return Kerhon koko nimi merkkijononna 
        */ 
     //  public String getKokoNimi() { 
     // return kokoNimi; 
     //  } 
   
}
