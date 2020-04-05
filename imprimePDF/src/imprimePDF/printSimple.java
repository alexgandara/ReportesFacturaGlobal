package imprimePDF;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

public class printSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
	    
	    
	    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
	 
	    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);

	    
	    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
	    
	  
	    System.out.println("Tu impresora por default es--->: " + service.getName());

	    //Creamos un arreglo de tipo byte
	    byte[] bytes;

	    //Aca convertimos el string(cuerpo del ticket) a bytes tal como
	    //lo maneja la impresora(mas bien ticketera :p)
	    bytes = "Perrito".getBytes();

	    //Creamos un documento a imprimir, a el se le appendeara
	    //el arreglo de bytes
	    Doc doc = new SimpleDoc(bytes,flavor,null);
	      
	    //Creamos un trabajo de impresión
	    DocPrintJob job = service.createPrintJob();

	    //Imprimimos dentro de un try de a huevo
	    try {
	      //El metodo print imprime
	      job.print(doc, null);
	      
	      
	    } catch (Exception er) {
	      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
	    }
	    
	    
	    
	    
	  }



	}


