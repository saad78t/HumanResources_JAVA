package humanresourcessalaries;
import static humanresourcesemployee.EmployNumberChecking.arvoHetu;
import static humanresourcesemployee.EmployNumberChecking.rand;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;
import humanhesourcescontract.Contract;
import humanresourcesemployee.Employee;

/**
 * Asking for salary information by creating a new dialogue for it
 * @author Saad Turky
 * @version 7.5.2019
 */
public class Salary  implements Cloneable {
private int seuraavaNro    = 1;
	
    private int        EmployeeNumber;
    private int        ContractNumber;
    private double        Mainsalary ;
    private int        Month;
    private int        Year;
    private double        Bounce;
    private double        Loance;
    private double        TransportaitionAllowance;
    private double     NetSalary ;
    
    

    /** 
     * Comparison of Salaries
     */ 
    public static class Vertailija implements Comparator<Salary> { 
        private int k;  
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
         
        
        @Override 
        public int compare(Salary salary1, Salary salary2) { 
            return salary1.getAvain(k).compareToIgnoreCase(salary2.getAvain(k)); 
//       	 return salary1.ContractNumber.compareToIgnoreCase(salary2.ContractNumber);
        } 
    } 
    
    
    /** 
     * Gives the contents of the k field as a string
     * @param k The contents of the param k field are restored
     * @return field content as a string
     */  
    public String getAvain(int k) { 
        switch ( k ) { 
        case 0: return "" + ContractNumber ;
        case 1: return "" + EmployeeNumber ;
        case 2: return "" + Mainsalary ;             
        case 3: return "" + Month;                   
        case 4: return "" + Year;                       
        case 5: return "" + Bounce;                    
        case 6: return "" + Loance;                    
        case 7: return "" + TransportaitionAllowance;
        case 8: return "" + NetSalary ;
       default: return "���l" ;              
        } 
    } 
    
    /**
     * Returns the number of salary fields
     * @return Number of fields
     */ 
    public int getKenttia() {
        return 9;
    }
    
    
    public int ekaKentta() {
        return 0;
    }
    
    public Salary() {
    	//
    }
    
    
    public static void main(String[] args) {
		Salary aku= new Salary();
		Salary aku2= new Salary();
		
		aku.rekisteroi();
		aku2.rekisteroi();
		
		aku.tulosta(System.out);
		aku2.tulosta(System.out);
		
		aku.vastaaAkuAnkka();
		aku2.vastaaAkuAnkka();
		aku.tulosta(System.out);
		aku2.tulosta(System.out);
	}
    
    
    public int rekisteroi() {
//		 if (EmployeeNumber!=0) return EmployeeNumber;
		 EmployeeNumber = seuraavaNro;
	        seuraavaNro++;
	        return EmployeeNumber;
	}
	 
	 
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa ett�
     * seuraava numero on aina suurempi kuin t�h�n menness� suurin.
     * @param nr asetettava tunnusnumero
     */
 public void setEmployeeNumber(int nr){
	 EmployeeNumber = nr;
     if (EmployeeNumber >= seuraavaNro) seuraavaNro = EmployeeNumber + 1;
 }
    
 
	 public int getEmployeeNumber() {
	    return EmployeeNumber;
 }
	 
	 
	 public int getContractNumber() {
		    return ContractNumber;
	 }
		 
	 
	 public void tulosta(PrintStream out) {
        out.println(String.format("%03d", EmployeeNumber, 3) + "  " + EmployeeNumber + "  "
        + ContractNumber+" "+   Mainsalary+" "+ Month);
        out.println("  " + Year + "  " + Bounce + " " + Loance+" "+TransportaitionAllowance + " "+ NetSalary);
 }
	        
	      
	   	 public void tulosta(OutputStream os) {
	   	        tulosta(new PrintStream(os));
	   	    }
	   	 
	   	 
	   	/**
		    * Gives the contents of the k field as a string
		    * @param k Multiple field content is restored
		    * @return field content as a string
		    */
	   	public String anna(int k) {
	        switch ( k ) {
	        case 0: return "" +    EmployeeNumber;           
	        case 1: return "" +    ContractNumber;           
	        case 2: return "" +    Mainsalary ;              
	        case 3: return "" +    Month;                    
	        case 4: return "" +    Year;                     
	        case 5: return "" +    Bounce;                   
	        case 6: return "" +    Loance;                   
	        case 7: return "" +    TransportaitionAllowance; 
	        case 8: return "" +    NetSalary ;               	      
	        
	        default: return "���li�";
	        }
	    }
	   	
	   	
	   	
	    /**
	     * Sets the value of the k string as the value of the string imported as a parameter
	     * @param k how many fields will be set
	     * @param The queue that is assigned to the field value
	     * @return null if the setup is successful, otherwise a corresponding error message.
	     * @example
	     * <pre name="test">
	     *   Salary salary = new Salary();
	     *   salary.aseta(1,32432) === null;
	     *   salary.aseta(2,2000) === "error"; 
	     *   salary.aseta(4,194.5) === "error";
	     * </pre>
	     */
	   	public String aseta(int k, String jono) {
	        String tjono = jono.trim();
	        StringBuffer sb = new StringBuffer(tjono);
	        switch ( k ) {
	        case 0:
	            setEmployeeNumber(Mjonot.erota(sb, '�', getEmployeeNumber()));
	            return null;
	        case 1:
	        	try {
	        	ContractNumber =Integer.valueOf(tjono);
	            return null;    
	        	}catch (Exception e) {return "Error, you have entred Double number in an Integer field or a text in an integer field";}
	        case 2:  
	        	try {
	        	Mainsalary   =Double.valueOf(tjono );                
	            return null; 
	        	}catch (NumberFormatException e) {return "Error, you have entred a text in an Integer field";}
	        case 3:  
	        	try {
	        	Month          =Integer.valueOf(tjono );  
	        	if (Month>12) return "Error, Month must be les than 13";
	            return null; 
	        	}catch (Exception e) {return "Error, you have entred Double number in an Integer field";}
	        case 4: 
	        	try {
	        	 Year          =Integer.valueOf( tjono );                 	                
	            return null;  
	        	}catch (Exception e) {return "Error, you have entred Double number in an Integer field";}
	        case 5:                                              
	        	 Bounce        =Double.valueOf(tjono);                            
	            return null;                                            
	        case 6:
	        	 Loance        =Double.valueOf(tjono );
	            return null;
	        case 7:
	        	 TransportaitionAllowance=Double.valueOf( tjono );
	            return null;
	        case 8:
	        	  NetSalary=Double.valueOf( tjono );
	              return null;        
	        default:
	            return "��li�";
	        }
	    }

	   	
	    public String getKysymys(int k) {
	        switch ( k ) {        
	        case 0: return   "EmployeeNumber";
	        case 1: return   "ContractNumber ";
	        case 2: return   "Mainsalary ";
	        case 3: return   "Month";
	        case 4: return   "Year";
	        case 5: return   "Bounce";
	        case 6: return   "Loance";
	        case 7: return   "TransportaitionAllowance";
	        case 8: return   "NetSalary";
	        default: return    ""  ;	
	        }                 
	    }
	   	
	    
	   	 public void vastaaAkuAnkka(int EmployeeNumber) {
             EmployeeNumber    = 1 + rand(1000,9999);
             ContractNumber    = 32132131;
             Mainsalary        = 1000;
             Month             = 5;
             Year              = 2018;
             Bounce            = 20;
             Loance            =100; 
             TransportaitionAllowance     = 0;
             NetSalary                    = 920;             
  }

				
	 public String getEmployeeNumbers() {
	    return Integer.toString(EmployeeNumber);
  }
	
	 
	public void vastaaAkuAnkka() {
		 int EmployeeNumber = arvoHetu();
	   vastaaAkuAnkka(EmployeeNumber);
 }


	/**
     * Returns salary information as a string that can be saved to a file.
     * @return salary punctuated as a string
     * @example
     * <pre name="test">
     *   Salary salary = new Salary();
     *   salary.parse("1|32132131|1000.0|5|2018|20.0|2121.0|2222.0|1121.0");
     *   salary.toString().startsWith("1|32132131|1000.0|5|2018|20.0|2121.0|2222.0|1121.0|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
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
         
		//the old code
//	    return "" +
//	            getEmployeeNumber() + "|" +
//	            EmployeeNumber + "|" +
//	            ContractNumber + "|" +
//	            Mainsalary + "|" +
//	            Month + "|" +
//	            Year + "|" +
//	            Bounce + "|"+
//	            Loance + "|"+            
//	            TransportaitionAllowance + "|" +
//	            NetSalary + "|" ;	               	            	                           
	}

	
 /**
  * Find out salary information | separated from the string
  * Makes sure that nextNro is greater than the incoming EmployeeNumber.
  * @param row from which salary information is taken
  * @example
  * <pre name="test">
  *   Salary salary = new Salary();;
  *   salary.parse("1|1199|20|1.7.2019|1.6.2020|8|10|One");
  *   salary.getContractNumber() === 3;
  *   salary.toString().startsWith("1|1199|20|1.7.2019|1.6.2020|8|10|One|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
  *   salary.rekisteroi();
  *   int n = salary.getEmployeeNumber();
  *   salary.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
  *   salary.rekisteroi();           // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
  *   salary.getEmployeeNumber() === n+20+1;    
  * </pre>
  */
    public void parse(String rivi) {
    	 StringBuffer sb = new StringBuffer(rivi);
         for (int k = 0; k < getKenttia(); k++)
         aseta(k, Mjonot.erota(sb, '|'));
         
    	//the old code
//        StringBuffer sb = new StringBuffer(rivi);
//        setEmployeeNumber(Mjonot.erota(sb, '|', getEmployeeNumber()));
//        EmployeeNumber = Mjonot.erota(sb, '|', EmployeeNumber);
//        ContractNumber = Mjonot.erota(sb, '|', ContractNumber);
//        Mainsalary = Mjonot.erota(sb, '|', Mainsalary);
//        Month = Mjonot.erota(sb, '|', Month);
//        Year = Mjonot.erota(sb, '|', Year);
//        Bounce = Mjonot.erota(sb, '|', Bounce);
//        Loance = Mjonot.erota(sb, '|', Loance);
//        TransportaitionAllowance = Mjonot.erota(sb, '|', TransportaitionAllowance);
//        NetSalary = Mjonot.erota(sb, '|', NetSalary);       
    }
    
    
    public boolean equals(Salary salary) {
  	  if ( salary == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(salary.anna(k)) ) return false;
        return true;
    }
      
    
    @Override
    public boolean equals(Object salary) {
        if ( salary == null ) return false;
        return this.toString().equals(salary.toString());
    }


    @Override
    public int hashCode() {
        return EmployeeNumber;
    }
    
    
    /**
     * Make an identical clone of a salary
     * @return Object cloned salary
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Salary salary = new Salary();
     *   salary.parse("1|32132131|1000.0|5|2018|20.0|2121.0|2222.0|1121.0");
     *   Salary kopio = salary.clone();
     *   kopio.toString() === salary.toString();
     *   salary.parse("2|32132131|1000.0|5|2018|20.0|2121.0|2222.0|1121.0");
     *   kopio.toString().equals(salary.toString()) === false;
     * </pre>
     */
     @Override
     public Salary clone() throws CloneNotSupportedException {
         Salary New = (Salary) super.clone();
         return New;
     }	   
}
