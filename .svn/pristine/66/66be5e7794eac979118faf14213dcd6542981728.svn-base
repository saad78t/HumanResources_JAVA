package speciality;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Collection;
import java.util.*;

import fi.jyu.mit.fxgui.Dialogs;
import humanhesourcescontract.Contract;
import humanresourcesemployee.Employee;
import humanresourcesemployee.SailoException;
import humanresourcessalaries.Salary;


/**
 * HumanResources specialities that know how to Add a new speciality
 *
 * @author Saad Turky
 * @version 1.0, 12.05.2019
 */
public class Specialities  implements Iterable<Speciality> {

	 private String                      tiedostonNimi = "";
	 private boolean muutettu = false;   
	  
	  private String tiedostonPerusNimi = "speciality"; 
	  
	  
   /** Taulukko specialities */
   private final Collection<Speciality> objects        = new ArrayList<Speciality>();
	   

	 /**
	  * Initializing specialities
	  */
     public Specialities() {
         // toistaiseksi ei tarvitse tehdä mitään
     }
     
     
     /**
      * Add a new speciality to the data structure. Take a speciality to own.
      * @param spe add a speciality. Note the data structure becomes the owner
      */
     public void lisaa( Speciality spe) {
         objects.add(spe);
     }
 
     
     public String getTiedostonNimi() { 
	        return getTiedostonPerusNimi() + ".dat"; 
	    } 

     /**
       * Returns the name of the backup file
       * @return name of the backup file
       */
      public String getBakNimi() {
          return tiedostonPerusNimi + ".bak";
      }


		public String getTiedostonPerusNimi() {
		 return tiedostonPerusNimi;
		}


		public void setTiedostonPerusNimi(String nimi) { 
		 tiedostonPerusNimi = nimi; 
		}

     
		/**
	      * Replaces the speciality in the data structure. Take a speciality to own.
	      * Finding a speciality with the same ID number. If not found
	      * so will be added as a new speciality.
	      * @param speciality is a reference to a speciality. Note the data structure becomes the owner
	      * @throws SailoException if the data structure is already full
	      * @example
	      * <pre name="test">
	      * #THROWS SailoException,CloneNotSupportedException
	      * #PACKAGEIMPORT
	      * Specialities specialities = new Specialities();
	      * Speciality spe1 = new Speciality(), spe2 = new Speciality();
	      * spe1.rekisteroi(); spe2.rekisteroi();
	      * specialities.getLkm() === 0;
	      * specialities.korvaaTaiLisaa(spe1); specialities.getLkm() === 1;
	      * specialities.korvaaTaiLisaa(spe2); specialities.getLkm() === 2;
	      * Speciality spe3 = spe1.clone();
	      * spe3.aseta(2,"kkk");
	      * Iterator<Speciality> i2=specialities.iterator();
	      * i2.next() === har1;
	      * harrastukset.korvaaTaiLisaa(spe3); specialities.getLkm() === 2;
	      * i2=specialities.iterator();
	      * Speciality h = i2.next();
	      * h === spe3;
	      * h ==  spe3 === true;
	      * h ==  spe1 === false;
	      * </pre>
	      */ 
	     public void korvaaTaiLisaa(Speciality speciality) throws SailoException {
	         int id = speciality.getTunnusNro();
	         for (int i = 0; i < getLkm(); i++) {
	        	 Speciality r =((ArrayList<Speciality>) objects).get(i);
	             if (r.getTunnusNro() == id) {
	            	 ((ArrayList<Speciality>) objects).set(i, speciality);
	                 muutettu = true;
	                 return;
	             }
	         }
	         lisaa(speciality);
	     }
	     
	     
	     /**
	       * Removes the selected speciality
	       * @param speciality to remove speciality
	       * @return if the record to be deleted was found
	       * @example
	       * <pre name="test">
	       * #THROWS SailoException 
	       * #import java.io.File;
	       *  Specialities specialities = new Specialities();
	       *  Speciality pitsi21 = new SPeciality(); pitsi21.vastaaPitsinNyplays(2);
	       *  Speciality pitsi11 = new SPeciality(); pitsi11.vastaaPitsinNyplays(1);
	       *  Speciality pitsi22 = new SPeciality(); pitsi22.vastaaPitsinNyplays(2); 
	       *  Speciality pitsi12 = new SPeciality(); pitsi12.vastaaPitsinNyplays(1); 
	       *  Speciality pitsi23 = new SPeciality(); pitsi23.vastaaPitsinNyplays(2); 
	       *  specialities.lisaa(pitsi21);
	       *  specialities.lisaa(pitsi11);
	       *  specialities.lisaa(pitsi22);
	       *  specialities.lisaa(pitsi12);
	       *  specialities.poista(pitsi23) === false ; specialities.getLkm() === 4;
	       *  specialities.poista(pitsi11) === true;   specialities.getLkm() === 3;
	       *  List<Speciality> h = specialities.annaSpecialities(1);
	       *  h.size() === 1; 
	       *  h.get(0) === pitsi12;
	       * </pre>
	       */
	      public boolean poista(Speciality speciality) {
	          boolean ret = objects.remove(speciality);
	          if (ret) muutettu = true;
	          return ret;
	      }

	     
	     /**
	      * Removes all the specialities of a particular employee
	      * @param tunnusNro reference to where records are deleted
	      * @return how many were removed
	      * @example
	      * <pre name="test">
	      *  Specialities specialities = new Specialities();
	      *  Speciality pitsi21 = new Speciality(); pitsi21.vastaaPitsinNyplays(2);
	      *  Speciality pitsi11 = new Speciality(); pitsi11.vastaaPitsinNyplays(1);
	      *  Speciality pitsi22 = new Speciality(); pitsi22.vastaaPitsinNyplays(2); 
	      *  Speciality pitsi12 = new Speciality(); pitsi12.vastaaPitsinNyplays(1); 
	      *  Speciality pitsi23 = new Speciality(); pitsi23.vastaaPitsinNyplays(2); 
	      *  specialities.lisaa(pitsi21);
	      *  specialities.lisaa(pitsi11);
	      *  specialities.lisaa(pitsi22);
	      *  specialities.lisaa(pitsi12);
	      *  specialities.lisaa(pitsi23);
	      *  specialities.poistaEmployeeSpecialities(2) === 3;  specialities.getLkm() === 2;
	      *  specialities.poistaEmployeeSpecialities(3) === 0;  specialities.getLkm() === 2;
	      *  List<Harrastus> h = specialities.annaSpecialities(2);
	      *  h.size() === 0; 
	      *  h = specialities.annaSpecialities(1);
	      *  h.get(0) === pitsi11;
	      *  h.get(1) === pitsi12;
	      * </pre>
	      */
	     public int poistaEmployeeSpecialities(int employeeNro) {
	         int n = 0;
	         for (Iterator<Speciality> it = objects.iterator(); it.hasNext();) {
	             Speciality spe = it.next();
	             if ( spe.getEmployeeNro() == employeeNro ) {
	                 it.remove();
	                 n++;
	             }
	         }
	         if (n > 0) muutettu = true;
	         return n;
	     }

	     
	     
   /**
     * Reads specialities from the file.
     * @param tied the beginning of the file name
     * @throws SailoException if reading fails
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Specialities specialities = new Specialities();
     *  Speciality pitsi21 = new Speciality(); pitsi21.vastaaPitsinNyplays(2);
     *  Speciality pitsi11 = new Speciality(); pitsi11.vastaaPitsinNyplays(1);
     *  Speciality pitsi22 = new Speciality(); pitsi22.vastaaPitsinNyplays(2); 
     *  Speciality pitsi12 = new Speciality(); pitsi12.vastaaPitsinNyplays(1); 
     *  Speciality pitsi23 = new Speciality(); pitsi23.vastaaPitsinNyplays(2); 
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  specialities.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  specialities.lisaa(pitsi21);
     *  specialities.lisaa(pitsi11);
     *  specialities.lisaa(pitsi22);
     *  specialities.lisaa(pitsi12);
     *  specialities.lisaa(pitsi23);
     *  specialities.tallenna();
     *  Specialities = new Specialities();
     *  specialities.lueTiedostosta(tiedNimi);
     *  Iterator<Speciality> i = specialities.iterator();
     *  i.next().toString() === pitsi21.toString();
     *  i.next().toString() === pitsi11.toString();
     *  i.next().toString() === pitsi22.toString();
     *  i.next().toString() === pitsi12.toString();
     *  i.next().toString() === pitsi23.toString();
     *  i.hasNext() === false;
     *  specialities.lisaa(pitsi23);
     *  specialities.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Speciality spe = new Speciality();
                spe.parse(rivi); // voisi olla virhekäsittely
                lisaa(spe);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }

 	
    /**
       * Saves your specialities to a file.
       * @throws SailoException if the saving fails
       */
      public void tallenna() throws SailoException {
          if ( !muutettu ) return;
  
          File fbak = new File(getBakNimi());
          File ftied = new File(getTiedostonNimi());
          fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
          ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");
  
          try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
              for (Speciality spe : objects) {
                  fo.println(spe.toString());
              }
          } catch ( FileNotFoundException ex ) {
              throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
          } catch ( IOException ex ) {
              throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
          }
  
          muutettu = false;
      }

    
//      /**
//       * Lukee jäsenistön tiedostosta.  
//       * TODO Kesken.
//       * @param hakemisto tiedoston hakemisto
//       * @throws SailoException jos lukeminen epäonnistuu
//        */
//       public void lueTiedostosta(String hakemisto) throws SailoException {
//           tiedostonNimi = hakemisto + ".spe";
//           throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
//       }
//   
   
     public void lueTiedostosta() throws SailoException { 
         lueTiedostosta(getTiedostonPerusNimi()); 
      }
     
       /**
        * Saves employees to file. 
        * TODO Kesken.
        * @throws SailoException if the saving fails
        */
       public void talleta() throws SailoException {
           throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
       }
   
   
       /**
        * Returns the number of humanresources specialities
        * @return Number of specialities
        */
       public int getLkm() {
           return objects.size();
       }
       
       
    /**
      * Iterator to go through all the hobbies
      * @return speciality iterator
      * 
      * @example
      * <pre name="test">
      * #PACKAGEIMPORT
      * #import java.util.*;
      * 
      *  Specialities specialities = new Specialities();
      *  Speciality pitsi21 = new Speciality(2); specialities.lisaa(pitsi21);
      *  Speciality pitsi11 = new Speciality(1); specialities.lisaa(pitsi11);
      *  Speciality pitsi22 = new Speciality(2); specialities.lisaa(pitsi22);
      *  Speciality pitsi12 = new Speciality(1); specialities.lisaa(pitsi12);
      *  Speciality pitsi23 = new Speciality(2); specialities.lisaa(pitsi23);
      * 
      *  Iterator<Speciality> i2=specialities.iterator();
      *  i2.next() === pitsi21;
      *  i2.next() === pitsi11;
      *  i2.next() === pitsi22;
      *  i2.next() === pitsi12;
      *  i2.next() === pitsi23;
      *  i2.next() === pitsi12;  #THROWS NoSuchElementException  
      *  
      *  int n = 0;
      *  int jnrot[] = {2,1,2,1,2};
      *  
      *  for ( Speciality  spe:specialities ) { 
      *    spe.getEmployeeNro() === jnrot[n]; n++;  
      *  }
      *  
      *  n === 5;
      *  
      * </pre>
      */
    @Override
    public Iterator<Speciality> iterator() {
        return objects.iterator();
    }


    /**
      * Searching for all employee specialities
      * @param tunnusnro employee tunnusnumero for which specialities are sought
      * @return data structure with references to discovered specialities
      * @example
      * <pre name="test">
      * #import java.util.*;
      * 
      *  Specialities specialities = new Specialities();
      *  Speciality pitsi21 = new Speciality(2); specialities.lisaa(pitsi21);
      *  Speciality pitsi11 = new Speciality(1); specialities.lisaa(pitsi11);
      *  Speciality pitsi22 = new Speciality(2); specialities.lisaa(pitsi22);
      *  Speciality pitsi12 = new Speciality(1); specialities.lisaa(pitsi12);
      *  Speciality pitsi23 = new Speciality(2); specialities.lisaa(pitsi23);
      *  Speciality pitsi51 = new Speciality(5); specialities.lisaa(pitsi51);
      *  
      *  List<Speciality> loytyneet;
      *  loytyneet = specialities.annaSpecialities(3);
      *  loytyneet.size() === 0; 
      *  loytyneet = specialities.annaSpecialities(1);
      *  loytyneet.size() === 2; 
      *  loytyneet.get(0) == pitsi11 === true;
      *  loytyneet.get(1) == pitsi12 === true;
      *  loytyneet = specialities.annaSpecialities(5);
      *  loytyneet.size() === 1; 
      *  loytyneet.get(0) == pitsi51 === true;
      * </pre> 
      */
     public List<Speciality> annaSpecialities(int employeenumber) {
         var loydetyt = new ArrayList<Speciality>();
         for (Speciality spe : objects)
         if (spe.getEmployeeNro() == employeenumber) loydetyt.add(spe);
         return loydetyt;
     }
           

 /**
   * Test program for specialities
   * @param args ei käytössä
   */
      public static void main(String[] args) {
          Specialities specialities = new Specialities();
          Speciality pitsi1 = new Speciality();
          pitsi1.vastaaPitsinNyplays(2);
          Speciality pitsi2 = new Speciality();
          pitsi2.vastaaPitsinNyplays(1);
          Speciality pitsi3 = new Speciality();
          pitsi3.vastaaPitsinNyplays(2);
          Speciality pitsi4 = new Speciality();
          pitsi4.vastaaPitsinNyplays(2);
  
          specialities.lisaa(pitsi1);
          specialities.lisaa(pitsi2);
          specialities.lisaa(pitsi3);
          specialities.lisaa(pitsi2);
          specialities.lisaa(pitsi4);
  
          System.out.println("============= Specialities testi =================");
  
          List<Speciality> specialities2 = specialities.annaSpecialities(2);
  
          for (Speciality spe : specialities2 ) {
              System.out.print(spe.getEmployeeNro() + " ");
              spe.tulosta(System.out);
          }  
      }     
}
