package speciality;
import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;
import humanresourcesemployee.EmployNumberChecking.*;

/**
 * Speciality
 *
 * @author Saad Turky
 * @version 1.0, 12.05.2019
 */
public class Speciality implements Cloneable{
	
  private int tunnusNro;
  private int employeeNro;
  private String ala;
  private int aloitusvuosi;
  private int tuntiaViikossa;
	  
  private static int seuraavaNro = 1;
	 
  
    /**
     * Initiating a speciality. So far, there is no need to do anything
     */
    public Speciality() {
        // Viel‰ ei tarvita mit‰‰n
    }

        
    /**
     * Initiating a particular employee's speciality. 
     * @param employeeNro employee reference number
     */
    public Speciality(int employeeNro) {
        this.employeeNro = employeeNro;
    }


    /**
      * @return Number of speciality fields
      */
     public int getKenttia() {
         return 5;
     }
 
 
     /**
      * @return first user input field index
      */
 
     public int ekaKentta() {
         return 2;
     }

    
     /**
       * @param k which field question is desired
       * @return the question text in the selected field
       */
      public String getKysymys(int k) {
          switch (k) {
              case 0:
                  return "id";
              case 1:
                  return "employeeId";
              case 2:
                  return "ala";
              case 3:
                  return "aloitusvuosi";
              case 4:
                  return "h/vko";
              default:
                  return "???";
          }
      }

     
    /**
        * make an identical clone of an employee
        * @return Object cloned employee
        * @example
        * <pre name="test">
        * #THROWS CloneNotSupportedException 
        *   Speciality spe = new Speciality();
        *   spe.parse("   2   |  10  |   Worker  | 1949 | 22 t ");
        *   Speciality kopio = spe.clone();
        *   kopio.toString() === spe.toString();
        *   spe.parse("   1   |  11  |   Engineer  | 1949 | 22 t ");
        *   kopio.toString().equals(spe.toString()) === false;
        * </pre>
        */
       @Override
       public Speciality clone() throws CloneNotSupportedException { 
           return (Speciality)super.clone();
       }

       
		/**
		   * A method to fill test values for Employee.
		   * The start year is estimated to have no two specialities
		   * the same information.
		   * @param nro reference to the person whose speciality is
		   */
		  public void vastaaPitsinNyplays(int nro) {
		      employeeNro = nro;
		      ala = "Pitsin nypl‰ys";
		      aloitusvuosi = rand(1900, 2000);
		      tuntiaViikossa = rand(0, 60);
		  }

  
		  public static int rand(int ala, int yla) {
				double n =(yla-ala)*Math.random()+ala;
				return (int)Math.round(n);
			}


       /**
         * Prints speciality information
         * @param out data stream to be printed
         */
        public void tulosta(PrintStream out) {
            out.println(ala + " " + aloitusvuosi + " " + tuntiaViikossa);
        }
  
        
     /**
       * Prints personal information
       * @param os the data stream to be printed
       */
          public void tulosta(OutputStream os) {
              tulosta(new PrintStream(os));
          }
          
        
       /**
         * Gives the speciality the following registration number.
         * @return speciality new tunnus_nro
         * @example
         * <pre name="test">
         *   Speciality pitsi1 = new Speciality();
         *   pitsi1.getTunnusNro() === 0;
         *   pitsi1.rekisteroi();
         *   Speciality pitsi2 = new Speciality();
         *   pitsi2.rekisteroi();
         *   int n1 = pitsi1.getTunnusNro();
         *   int n2 = pitsi2.getTunnusNro();
         *   n1 === n2-1;
         * </pre>
         */    
    public int rekisteroi() {
    	tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
      * Returning to which employee of the speciality belongs
      * @return j‰senen id
      */
     public int getEmployeeNro() {
         return employeeNro;
     }
     
     
     public int getTunnusNro() {
         return tunnusNro;
     }
     
     
     /**
      * Sets the ID number while ensuring that
      * the next number is always higher than the highest ever.
      * @param nr the identification number to be set
      */
     private void setTunnusNro(int nr) {
         tunnusNro = nr;
         if ( tunnusNro >= seuraavaNro ) seuraavaNro = tunnusNro + 1;
     }

     
     public String getAla() {
			return ala;
		}


		public String setAla(String Ala) {
			ala = Ala;
			return null;
		}
     
     
     /**
      * Returns employee information as a string that can be saved to a file.
      * @return a speciality in a set of characters
      * @example
      * <pre name="test">
      *   Speciality speciality = new Speciality();
      *   speciality.parse("   2   |  10  |   Worker  | 1949 | 22 t ");
      *   speciality.toString()    === "2|10|Worker|1949|22";
      * </pre>
      */
     @Override
     public String toString() {
         StringBuffer sb = new StringBuffer("");
         String erotin = "";
         for (int k = 0; k < getKenttia(); k++) {
             sb.append(erotin);
             sb.append(anna(k));
             erotin = "|";
         }
         return sb.toString();
      }
     

         /**
           * @param k Which field content is desired
           * @return content of the selected field
           * @example
           * <pre name="test">
           *   Speciality spe = new Speciality();
           *   spe.parse("   2   |  10  |   Engineer  | 1949 | 22 t ");
           *   spe.anna(0) === "2";   
           *   spe.anna(1) === "10";   
           *   spe.anna(2) === "Engineer";   
           *   spe.anna(3) === "1949";   
           *   spe.anna(4) === "22";   
           *   
           * </pre>
           */
          public String anna(int k) {
              switch (k) {
                  case 0:
                      return "" + tunnusNro;
                  case 1:
                      return "" + employeeNro;
                  case 2:
                      return ala;
                  case 3:
                      return "" + aloitusvuosi;
                  case 4:
                      return "" + tuntiaViikossa;
                  default:
                      return "???";
              }
          }

          
          /**
            * Asetting the content of the selected field. If set up,
            * returned null, otherwise error text.
            * @param k which field content is set
            * @param s set content as a string
            * @return null if ok, otherwise error text
            * @example
            * <pre name="test">
            *    Speciality spe = new Speciality();
            *   spe.aseta(3,"kissa") === "aloitusvuosi: Ei kokonaisluku (kissa)";
            *   spe.aseta(3,"1940")  === null;
            *   spe.aseta(4,"kissa") === "h/vko: Ei kokonaisluku (kissa)";
            *   spe.aseta(4,"20")    === null;
            * </pre>
            */
           public String aseta(int k, String s) {
               String st = s.trim();
               StringBuffer sb = new StringBuffer(st);
               switch (k) {
                   case 0:
                       setTunnusNro(Mjonot.erota(sb, '$', getTunnusNro()));
                       return null;
                   case 1:
                       employeeNro = Mjonot.erota(sb, '$', employeeNro);
                       return null;
                   case 2:
                       ala = st;
                       return null;
                   case 3:
                       try {
                           aloitusvuosi = Mjonot.erotaEx(sb, 'ß', aloitusvuosi);
                       } catch (NumberFormatException ex) {
                           return "aloitusvuosi: Ei kokonaisluku ("+st+")";
                       }
                       return null;
       
                   case 4:
                       try {
                           tuntiaViikossa = Mjonot.erotaEx(sb, 'ß', tuntiaViikossa);
                       } catch (NumberFormatException ex) {
                           return "h/vko: Ei kokonaisluku ("+st+")";
                       }
                       return null;
       
                   default:
                       return "V‰‰r‰ kent‰n indeksi";
               }
           }
       

          
     /**
         * Find Out Speciality Information | separated from the string.
         * Makes sure that seuraavaNro is greater than the incoming identification No.
         * @param a line from which speciality information is taken
         * @example
         * <pre name="test">
         *     Speciality speciality = new Speciality();
         *   speciality.parse("   2   |  10  |   Worker  | 1949 | 22 t ");
         *   speciality.getJasenNro() === 10;
         *   speciality.toString()    === "2|10|Worker|1949|22";
         *   
         *   speciality.rekisteroi();
         *   int n = speciality.getTunnusNro();
         *   speciality.parse(""+(n+20));
         *   speciality.rekisteroi();
         *   speciality.getTunnusNro() === n+20+1;
         *   speciality.toString()     === "" + (n+20+1) + "|10||1949|22";
         * </pre>
         */
        public void parse(String rivi) {
            StringBuffer sb = new StringBuffer(rivi);
            for (int k = 0; k < getKenttia(); k++)
                aseta(k, Mjonot.erota(sb, '|'));
        }

        @Override
            public boolean equals(Object obj) {
                if ( obj == null ) return false;
                return this.toString().equals(obj.toString());
            }
            
        
            @Override
            public int hashCode() {
                return employeeNro;
            }
        
    
     /**
           * Test program for Speciality.
           * @param args ei k‰ytˆss‰
           */
          public static void main(String[] args) {
             Speciality spe = new Speciality();
              spe.vastaaPitsinNyplays(2);
              spe.tulosta(System.out);
          }

}
