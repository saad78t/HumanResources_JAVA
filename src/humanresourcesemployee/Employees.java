package humanresourcesemployee;

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


/**
 * add a new employee
 *
 * @author Saad Turky
 * @version 1.0, 9.05.2019
 * @version 23.04.2019 - files in the directory
 */
public class Employees implements Iterable<Employee>{
	
	private static final int MAX_EMPLOYEES   = 5;
    private int              lkm           = 0;
    //private String           fileName = "";
    private Employee []         objects = new Employee[MAX_EMPLOYEES]; 
    //private String kokoNimi = ""; 
    private String tiedostonPerusNimi = "nimet"; 
    private boolean muutettu = false; 
    
    
    public Employees() {
    	//
    }
    
   
    /**
     * Add a new employee to the data structure. Take over the employee.
     * @param share the employee's reference. Note the data structure becomes the owner
     * @throws SailoException if the data structure is already full
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Employees employees = new Employees();
     * Employee aku1 = new Employee(), aku2 = new Employee();
     * employees.getLkm() === 0;
     * employees.lisaa(aku1); employees.getLkm() === 1;
     * employees.lisaa(aku2); employees.getLkm() === 2;
     * employees.lisaa(aku1); employees.getLkm() === 3;
     * Iterator<Employee> it = employees.iterator(); 
     * it.next() === aku1;
     * it.next() === aku2; 
     * it.next() === aku1;  
     * employees.lisaa(aku1); employees.getLkm() === 4;
     * employees.lisaa(aku1); employees.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Employee employee)   {
        if (lkm >= objects.length)  {
          objects = Arrays.copyOf(objects, objects.length+10);
        }      
        objects[lkm++] = employee;
        muutettu = true;
//        this.objects[lkm] = employee;
//lkm++;
    }
    
   
    /**
     * Returns the reference to the i employee.
     * @param i an employee reference
     * @return a reference to an employee whose index is i
     * @throws IndexOutOfBoundsException if i is not within the allowed range
     */
    public Employee anna(int i) throws IndexOutOfBoundsException {
       if (i < 0 || lkm <= i)
         throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return objects[i];
    }
    
    
    public int getLkm() {
        return this.lkm;
    }
    

	public Employee newEmployee() {
	    Employee neww = new Employee();
	    neww.rekisteroi();
	    neww.vastaaAkuAnkka();
	   // try {
	        lisaa(neww);
	          return neww;
	    //} catch (SailoException e) {
	       // Dialogs.showMessageDialog("Problems creating a new one " + e.getMessage());
	      
	   // }
	    //hae(neww.getEmployeeNumber());
	    //return null;
}
	
		
	/**
     * Replaces an employee in the data structure. Take over the employee.
     * Searching for an employee with the same Employee Number. If not found
     * so be added as a new employee.
     * @param share the employee's reference. Note the data structure becomes the owner
     * @throws SailoException if the data structure is already full
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Employees employees = new Employees();
     * Employee aku1 = new Employee(), aku2 = new Employee();
     * aku1.rekisteroi(); aku2.rekisteroi();
     * employees.getLkm() === 0;
     * employees.korvaaTaiLisaa(aku1); employees.getLkm() === 1;
     * employees.korvaaTaiLisaa(aku2); employees.getLkm() === 2;
     * Employee aku3 = aku1.clone();
     * aku3.aseta(3,"kkk");
     * Iterator<Employee> it = employees.iterator();
     * it.next() == aku1 === true;
     * employees.korvaaTaiLisaa(aku3); employees.getLkm() === 2;
     * it = employees.iterator();
     * Employee j0 = it.next();
     * j0 === aku3;
     * j0 == aku3 === true;
     * j0 == aku1 === false;
     * </pre>
     */
	public void korvaaTaiLisaa(Employee employee) throws SailoException {
		 int id = employee.getEmployeeNumber();
		 for(int i =0; i<lkm; i++) {
			 if(objects[i].getEmployeeNumber()==id) {
				 objects[i]= employee;
				 muutettu=true;
				 return;
			 }
		 }
		 lisaa(employee);
	  }
	
	
	/**
      * Class for iteration of employees.
      * @example
      * <pre name="test">
      * #THROWS SailoException 
      * #PACKAGEIMPORT
      * #import java.util.*;
      * 
      * Employees employees = new Employees();
      * Employee aku1 = new Employee(), aku2 = new Employee();
      * aku1.rekisteroi(); aku2.rekisteroi();
      *
      * employees.lisaa(aku1); 
      * employees.lisaa(aku2); 
      * employees.lisaa(aku1); 
      * 
      * StringBuffer ids = new StringBuffer(30);
      * for (Employee employee:employees)   // Kokeillaan for-silmukan toimintaa
      *   ids.append(" "+employee.getEmployeeNumber());           
      * 
      * String tulos = " " + aku1.getEmployeeNumber() + " " + aku2.getEmployeeNumber() + " " + aku1.getEmployeeNumber();
      * 
      * ids.toString() === tulos; 
      * 
      * ids = new StringBuffer(30);
      * for (Iterator<Employee>  i=employees.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
      *   Employee employee = i.next();
      *   ids.append(" "+employee.getEmployeeNumber());           
      * }
      * 
      * ids.toString() === tulos;
      * 
      * Iterator<Employee>  i=employees.iterator();
      * i.next() == aku1  === true;
      * i.next() == aku2  === true;
      * i.next() == aku1  === true;
      * 
      * i.next();  #THROWS NoSuchElementException
      *  
      * </pre>
      */
     public class EmployeesIterator implements Iterator<Employee> {
    	 private int kohdalla = 0;


         /**
          * Is there another employee yet
          * @see java.util.Iterator#hasNext()
          * @return true if there are still employees
          */
         @Override
         public boolean hasNext() {
             return kohdalla < getLkm();
         }


         /**
          * Give the next employee
          * @return next employee
          * @throws NoSuchElementException if the next item no longer exists
          * @see java.util.Iterator#next()
          */
         @Override
         public Employee next() throws NoSuchElementException {
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
	    * Returning the iterator from its employees.
	    * @return employee iterator
	    */
	   @Override
	   public Iterator<Employee> iterator() {
	       return new EmployeesIterator();
	   }
	
	
	/** 
      * Returns references in the "table" for employees matching the search criteria
      * @param hakuehto hakuehto 
      * @param k index of the field to be searched
      * @return found employees of the data structure
      * @example 
      * <pre name="test"> 
      * #THROWS SailoException  
      *   Employees employees = new Employees();
      *   Employee employee1 = new Employee(); employee1.parse("1|Ankka Aku|0302012|Paratiisitie 13|"); 
      *   Employee employee2 = new Employee(); employee2.parse("2|Ankka Tupu||0305522|"); 
      *   Employee employee3 = new Employee(); employee3.parse("3|Susi Sepe|121237||131313|Per‰mets‰"); 
      *   Employee employee4 = new Employee(); employee4.parse("4|Ankka Iines|030245|Ankkakuja 9"); 
      *   Employee employee5 = new Employee(); employee5.parse("5|Ankka Roope|091007|Ankkakuja 12"); 
      *   employees.lisaa(employee1); employees.lisaa(employee2); employees.lisaa(employee3); employees.lisaa(employee4); employees.lisaa(employee5);
      *   List<Employee> loytyneet;  
      *   loytyneet = (List<Employee>)employees.etsi("*s*",1);  
      *   loytyneet.size() === 2;  
      *   loytyneet.get(0) == employee3 === true;  
      *   loytyneet.get(1) == employee4 === true;  
      *     
      *   loytyneet = (List<Employee>)employees.etsi("*7-*",2);  
      *   loytyneet.size() === 2;  
      *   loytyneet.get(0) == employee3 === true;  
      *   loytyneet.get(1) == employee5 === true; 
      *     
      *   loytyneet = (List<Employee>)employees.etsi(null,-1);  
      *   loytyneet.size() === 5;  
      * </pre> 
      */ 
     public Collection<Employee> etsi(String hakuehto,final int k) {      
		String ehto = "*"; 
         if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
         int hk = k; 
         if ( hk < 0 ) hk = 1;
         List<Employee> loytyneet = new ArrayList<Employee>(); 
         for (Employee employee : this) { 
            if (WildChars.onkoSamat(employee.anna(hk), ehto)) 
            	 loytyneet.add(employee);   
         } 
         //  TODO: lajittelua varten vertailija 
         var vertailija =new Employee.Vertailija(hk);
         Collections.sort(loytyneet,vertailija);
         return loytyneet; 
     }
     	
	
	public static void main(String[] args)  {
		Employees employees = new Employees();
		
		Employee aku= new Employee();
		Employee aku2= new Employee();
		
		aku.rekisteroi();
		aku.vastaaAkuAnkka();
		aku2.rekisteroi();
		aku2.vastaaAkuAnkka();
		
		 
		employees.lisaa(aku);
		employees.lisaa(aku2);
		
        
		  System.out.println("============= Employees test =================");
		  for (int i = 0; i < employees.getLkm(); i++) {
              Employee employee = employees.anna(i);
              System.out.println("Employee nro: " + i);
              employee.tulosta(System.out);
          }
	}
	
	
	/** 
	  * Returns the name of the backup file 
	  * @return name of the backup file	
	  */   
	    public String getBakNimi() { 
	        return tiedostonPerusNimi + ".bak"; 
	    } 
	 
	
	   public void tallenna() throws SailoException { 
	        if ( !muutettu ) return; 
	        File fbak = new File(getBakNimi()); 
	        File ftied = new File(getTiedostonNimi()); 
	        fbak.delete(); // if .. System.err.println("Ei voi tuhota"); 
	        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimet‰"); 
	        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) { 
	           // fo.println(getKokoNimi()); 
	            fo.println(objects.length); 
	            for (Employee employee : objects) { 
	            	if (employee != null)
	                fo.println(employee.toString()); 
	            } 
	            //} catch ( IOException e ) { // ei heit‰ poikkeusta 
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

    
    /** 
       * id number of the employee to be deleted 
       * @param id number of the employee to be deleted 
       * @return1 if deleted, 0 if not found
       * @example 
       * <pre name="test"> 
       * #THROWS SailoException  
       * Employees employees = new Employees(); 
       * Employee aku1 = new Employee(), aku2 = new Employee(), aku3 = new Employee(); 
       * aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi(); 
       * int id1 = aku1.getEmployeeNumber(); 
       * employees.lisaa(aku1); employees.lisaa(aku2); employees.lisaa(aku3); 
       * employees.poista(id1+1) === 1; 
       * employees.annaId(id1+1) === null; employees.getLkm() === 2; 
       * employees.poista(id1) === 1; employees.getLkm() === 1; 
       * employees.poista(id1+3) === 0; employees.getLkm() === 1; 
       * </pre> 
       *  
       */ 
      public int poista(int id) { 
          int ind = etsiId(id); 
          if (ind < 0) return 0; 
          lkm--; 
          for (int i = ind; i < lkm; i++) 
              objects[i] = objects[i + 1]; 
          objects[lkm] = null; 
          muutettu = true; 
          return 1; 
      } 

      
      /** 
        * Searches by employee id
        * @param id employee number to search
        * @return found employee index or -1 if not found
        * <pre name="test"> 
        * #THROWS SailoException  
        * Employees employees = new Employees(); 
        * Employee aku1 = new Employee(), aku2 = new Employee(), aku3 = new Employee(); 
        * aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi(); 
        * int id1 = aku1.getEmployeeNumber(); 
        * employees.lisaa(aku1); employees.lisaa(aku2); employees.lisaa(aku3); 
        * employees.etsiId(id1+1) === 1; 
        * employees.etsiId(id1+2) === 2; 
        * </pre> 
        */ 
       public int etsiId(int id) { 
           for (int i = 0; i < lkm; i++) 
               if (id == objects[i].getEmployeeNumber()) return i; 
           return -1; 
       } 

    
      /**
      * Reads the employee's file.
      * @param the basic name of the file
      * @throws SailoException if reading fails
      * 
      * @example
      * <pre name="test">
      * #THROWS SailoException 
      * #import java.io.File;
      * 
      *  Employees employees = new Employees(); 
      *  Employee aku1 = new Employee(), aku2 = new Employee();
      *  aku1.vastaaAkuAnkka();
      *  aku2.vastaaAkuAnkka();
      *  String hakemisto = "testikelmit";
      *  String tiedNimi = hakemisto+"/nimet";
      *  File ftied = new File(tiedNimi+".dat");
      *  File dir = new File(hakemisto);
      *  dir.mkdir();
      *  ftied.delete();
      *  Employees.lueTiedostosta(tiedNimi); #THROWS SailoException
      *  Employees.lisaa(aku1);
      *  Employees.lisaa(aku2);
      *  Employees.tallenna();
      *  Employees = new Employees();            // Poistetaan vanhat luomalla uusi
      *  Employees.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
      *  Iterator<Employee> i = employees.iterator();
      *  i.next() === aku1;
      *  i.next() === aku2;
      *  i.hasNext() === false;
      *  employees.lisaa(aku2);
      *  employees.tallenna();
      *  ftied.delete() === true;
      *  File fbak = new File(tiedNimi+".bak");
      *  fbak.delete() === true;
      *  dir.delete() === true;
      * </pre>
      */
	public void lueTiedostosta(String tied) throws SailoException { 
		 
        setTiedostonPerusNimi(tied); 
 
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) { 
 
            //kokoNimi = fi.readLine(); 
 
            //if ( kokoNimi == null ) throw new SailoException("Kerhon nimi puuttuu"); 
 
            String rivi = fi.readLine(); 
 
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu"); 
 
            // int maxKoko = Mjonot.erotaInt(rivi,10); // tehd‰‰n jotakin 
 
            while ( (rivi = fi.readLine()) != null ) { 
                rivi = rivi.trim(); 
 
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;  
                Employee employee = new Employee(); 
                employee.parse(rivi); // voisi olla virhek‰sittely 
                lisaa(employee);
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

    
//       /** 
//         * Palauttaa Kerhon koko nimen 
//         * @return Kerhon koko nimi merkkijononna 
//         */ 
//        public String getKokoNimi() { 
//       return kokoNimi; 
//     } 
    
}
