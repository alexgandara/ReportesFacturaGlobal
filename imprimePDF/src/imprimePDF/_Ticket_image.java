package imprimePDF;

/*
 * Ticket.java
 * 
 * Copyright 2013 Josue Camara <picharras@picharras-HP-Folio>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 */

import java.awt.*;
import java.awt.print.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.ServiceUI;
import javax.print.attribute.*;





public class _Ticket_image {
	 
	  
	byte[] comecoTextoCondensado = {0x45, 0x0F};  
	byte[] fimTextoCondensado = {0x12};  
	char[] cortePapel = new char[]{0x1d, 'V', 1};
	
	
	// variable para imprimir imagen
	private final static char ESC_CHAR = 0x1B;
	private final static char GS = 0x1D;
	private final static byte[] LINE_FEED = new byte[]{0x0A};
	private final static byte[] CUT_PAPER = new byte[]{GS, 0x56, 0x00};
	private final static byte[] INIT_PRINTER = new byte[]{ESC_CHAR, 0x40};
	private static byte[] SELECT_BIT_IMAGE_MODE = {0x1B, 0x2A, 33};
	private final static byte[] SET_LINE_SPACE_24 = new byte[]{ESC_CHAR, 0x33, 24};
	
	
	
	String corte = String.valueOf(cortePapel);
	

	
	
//	GS "(k"  4 0 49 65 50 0
	char[] _qr_01 = new char[]{0x1d, '(', 'k', 'p','L','p','H',49,80};
//	char[] _qr_02 = new char[]{0x1d, '(', 'k', 3, 0, 49, 67, 5};
//	char[] _qr_03 = new char[]{0x1d, '(', 'k', 3, 0, 49, 69, 48};
//	char[] _qr_04 = new char[]{0x1d, '(', 'k', 28, 0, 49, 80, 48};
//	char[] _qr_final = new char[]{0x1d, '(', 'k', 3, 0, 49, 81, 48};

	//  GS ( k 	p L p H cn fn [parameters]
	
	
//	GS "(k"  4 0 49 65 50 0
//	GS "(k"  3 0 49 67  5
//	GS "(k"  3 0 49 69 48 
//	GS "(k" 28 0 49 80 48 "https://stackoverflow.com/"
//	GS "(k"  3 0 49 81 48
	
	
	
	
	
	
	String qrdata="hora NIlo";
	
	
	
	/////////////////////////////////////
	
	
	
	
	
	
	
//	byte[] _inicializa_qr = {0x1B, 0x2A};
	
//	char[] _esc_init = new char[]{0x1B, 'V', 1};  
//	char[] _qr_init = new char[]{0x2A, 'V', 1};
	
//	byte[] _iniclializa_printer = {0x1B,0x40};
	
	
	
//	String texto_bottom =   new String(_qr_01) +  qrdata.getBytes()    
//		    + new String(comecoTextoCondensado)  
//		    + new String(" \n\n\n\n\n")  
//		    + new String(fimTextoCondensado) + new String(cortePapel);

	
	
	
	String texto_bottom = "";  //   new String(_qr_01) +  qrdata.getBytes()    
//		    + new String(comecoTextoCondensado)  
//		    + new String(" \n\n\n\n\n")  
//		    + new String(fimTextoCondensado) 
//			  "{{_ruc}}\n"
			
			
//	+ new String(cortePapel);

	
	
	//   + String(cortePapel);
	
	  //Ticket attribute content
	private String contentTicket = "\f\n"+ 
		"================================================"+"\n"+
		"{{nameLocal}}\n"+
	    "{{expedition}}\n"+
	    "{{dateTime}}\n"+
	    "{{cajero}}\n"+
	    "{{ticket}}\n"+
	    "================================================"+"\n"+
	    "{{_ruc}}\n"+
	    "{{_tipo_doc_descripcion}}\n"+
	    "{{_folio}}\n"+
	    "================================================"+"\n"+   		
		"{{_ruc_receptor}}\n"+
		"{{_razon_social_receptor}}\n"+
		"{{_direccion_receptor}}\n"+
		"{{_fecha_emision}}\n"+
		"{{_tipo_moneda}}\n"+
		"{{_tipo_igv}}\n"+
    	"================================================"+"\n"+	
	    "Descripcion               Cant. Precio     Total"+"\n"+
	    "================================================"+"\n"+
	    "{{items}}\n"+
		"================================================"+"\n"+
		"{{_total_letras}}\n\n"+
	    "                           SUBTOTAL: {{subTotal}}\n"+
	    "                                IGV: {{tax}}\n"+
	    "                              TOTAL: {{total}}\n\n"+
		"================================================"+"\n"+
		"{{_hash}}\n\n";
		
//		"{{_leyenda}}\n\n\n\n\n\n\n\n\n"+
		
		
	//	texto_bottom+"\n\n"+corte;
	
	 public String _cadena_ticket=contentTicket;
	
	 
	
	  
	    //El constructor que setea los valores a la instancia
	    _Ticket_image( String linea01,
	    		String linea02,
	    		String box,
	    		String linea05,
	    		String linea04,
	    		String linea03,
	    		String items,
	    		String subTotal,
	    		String tax,
	    		String total,
	    		String recibo,
	    		String change,	
	    		String _ruc,
	    		String _tipo_doc_descripcion,
	    		String _folio, 
	    		String _ruc_receptor,
	    		String _razon_social_receptor,
	    		String _direccion_receptor,
	    		String _fecha_emision,
	    		String _tipo_moneda,
	    		String _tipo_igv,
	    		String _total_letras,
	    		String _hash,
	    		String _leyenda,
	    		String _ruta_tickets) throws IOException {
	
	
	this.contentTicket = this.contentTicket.replace("{{nameLocal}}", linea01);
    this.contentTicket = this.contentTicket.replace("{{expedition}}", linea02);
    this.contentTicket = this.contentTicket.replace("{{box}}", box);
    this.contentTicket = this.contentTicket.replace("{{ticket}}", linea05);
    this.contentTicket = this.contentTicket.replace("{{cajero}}", linea04);
    this.contentTicket = this.contentTicket.replace("{{dateTime}}", linea03);
    this.contentTicket = this.contentTicket.replace("{{items}}", items);
    this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
    this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
    this.contentTicket = this.contentTicket.replace("{{total}}", total);
    this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
    this.contentTicket = this.contentTicket.replace("{{change}}", change);
    
    this.contentTicket = this.contentTicket.replace("{{_ruc}}", _ruc);
    this.contentTicket = this.contentTicket.replace("{{_tipo_doc_descripcion}}", _tipo_doc_descripcion);
    this.contentTicket = this.contentTicket.replace("{{_folio}}", _folio);
    this.contentTicket = this.contentTicket.replace("{{_ruc_receptor}}", _ruc_receptor);
    this.contentTicket = this.contentTicket.replace("{{_razon_social_receptor}}", _razon_social_receptor);
    this.contentTicket = this.contentTicket.replace("{{_direccion_receptor}}", _direccion_receptor);
    this.contentTicket = this.contentTicket.replace("{{_fecha_emision}}", _fecha_emision);
    this.contentTicket = this.contentTicket.replace("{{_tipo_moneda}}", _tipo_moneda);
    this.contentTicket = this.contentTicket.replace("{{_tipo_igv}}", _tipo_igv);
    this.contentTicket = this.contentTicket.replace("{{_total_letras}}", _total_letras);
    this.contentTicket = this.contentTicket.replace("{{_hash}}", _hash);
    this.contentTicket = this.contentTicket.replace("{{_leyenda}}", _leyenda);
    
    
	byte[] bytes;

	// creamos el data con bytes donde ira el QR
	byte[] bytes_qr = qrCode("datos para el qr");

	// Aca convertimos el string(cuerpo del ticket) a bytes tal como
	// lo maneja la impresora(mas bien ticketera :p)
	bytes = this.contentTicket.getBytes();

	_imprime_bytes(bytes);
	_imprime_bytes(bytes_qr);

    
   // print(_hash);
    
	// imprimir a Disco
	
    String _file_name_ticket=_ruta_tickets+_folio+".txt";
    System.out.println("Ruta Ticket: "+_file_name_ticket);
    
 //   System.out.println(_cadena_ticket);
//	File archivo_ticket=new File(_file_name_ticket);
//	archivo_ticket.delete();
//	FileWriter chanel_write;

//	chanel_write = new FileWriter(archivo_ticket,true);
	
		
//	chanel_write.write(_cadena_ticket);
//	chanel_write.close();


  }
  

/*
 * 
 * 
 * 
 * 
 public void printImage() {


    // find the printService of name printerName
    DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;
    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService printServic = findPrintService(printerName, printService);
    DocPrintJob job = printServic.createPrintJob();

    try {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("logo.png");

        Doc doc = new SimpleDoc(is, flavor, null);

        job.print(doc, null);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
 
 * 
 * 
 * 
 * 
 * 	    
 */
	    
	    
	    
	    
	    
	    
	    
	    
//	public void print(String _qr_data) {

		// Especificamos el tipo de dato a imprimir
		// Tipo: bytes; Subtipo: autodetectado

//		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

//		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

//		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
//				flavor, pras);
//		PrintService defaultService = PrintServiceLookup
//				.lookupDefaultPrintService();

		// esta linea se activa para preguntar la impresora
		// PrintService service = ServiceUI.printDialog(null, 700, 200,
		// printService, defaultService, flavor, pras);

		// esta es la linea que se activa para mandar sin preguntar
//		PrintService service = PrintServiceLookup.lookupDefaultPrintService();

//		System.out.println("Tu impresora por default es: " + service.getName());

		// Creamos un arreglo de tipo byte para la primera parte de ticket que
		// con letras
//		byte[] bytes;

		// creamos el data con bytes donde ira el QR
//		byte[] bytes_qr = qrCode(_qr_data);

		// Aca convertimos el string(cuerpo del ticket) a bytes tal como
		// lo maneja la impresora(mas bien ticketera :p)
//		bytes = this.contentTicket.getBytes();

//		_imprime_bytes(bytes);
//		_imprime_bytes(bytes_qr);

//	}
  
public void _imprime_bytes(byte[] _bytes) {

	PrintService service = PrintServiceLookup.lookupDefaultPrintService();
	// System.out.println("Tu impresora por default es: " +
	// service.getName());
	DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
	Doc doc_qr = new SimpleDoc(_bytes, flavor, null);
	DocPrintJob job_qr = service.createPrintJob();

	try {
		job_qr.print(doc_qr, null);
	} catch (Exception er) {
		JOptionPane.showMessageDialog(null,	"Error al imprimir: " + er.getMessage());
	}

}   
  
  public static byte[] qrCode(String content) {
      HashMap commands = new HashMap();
      String[] commandSequence = {"model", "size", "error", "store", "content", "print"};
      int contentLen = content.length();
      int resultLen = 0;
      byte[] command;

      // QR Code: Select the model
      //              Hex     1D      28      6B      04      00      31      41      n1(x32)     n2(x00) - size of model
      // set n1 [49 x31, model 1] [50 x32, model 2] [51 x33, micro qr code]
      // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=140
      command = new byte[]{(byte) 0x1d, (byte) 0x28, (byte) 0x6b, (byte) 0x04, (byte) 0x00, (byte) 0x31, (byte) 0x41, (byte) 0x32, (byte) 0x00};
      commands.put("model", command);
      resultLen += command.length;

      // QR Code: Set the size of module
      // Hex      1D      28      6B      03      00      31      43      n
      // n depends on the printer
      // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=141
      command = new byte[]{(byte) 0x1d, (byte) 0x28, (byte) 0x6b, (byte) 0x03, (byte) 0x00, (byte) 0x31, (byte) 0x43, (byte) 0x06};
      commands.put("size", command);
      resultLen += command.length;

      //          Hex     1D      28      6B      03      00      31      45      n
      // Set n for error correction [48 x30 -> 7%] [49 x31-> 15%] [50 x32 -> 25%] [51 x33 -> 30%]
      // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=142
      command = new byte[]{(byte) 0x1d, (byte) 0x28, (byte) 0x6b, (byte) 0x03, (byte) 0x00, (byte) 0x31, (byte) 0x45, (byte) 0x33};
      commands.put("error", command);
      resultLen += command.length;

      // QR Code: Store the data in the symbol storage area
      // Hex      1D      28      6B      pL      pH      31      50      30      d1...dk
      // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=143
      //                        1D          28          6B         pL          pH  cn(49->x31) fn(80->x50) m(48->x30) d1�dk
      int storeLen = contentLen + 3;
      byte store_pL = (byte) (storeLen % 256);
      byte store_pH = (byte) (storeLen / 256);
      command = new byte[]{(byte) 0x1d, (byte) 0x28, (byte) 0x6b, store_pL, store_pH, (byte) 0x31, (byte) 0x50, (byte) 0x30};
      commands.put("store", command);
      resultLen += command.length;

      // QR Code content
      command = content.getBytes();
      commands.put("content", command);
      resultLen += command.length;

      // QR Code: Print the symbol data in the symbol storage area
      // Hex      1D      28      6B      03      00      31      51      m
      // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=144
      command = new byte[]{(byte) 0x1d, (byte) 0x28, (byte) 0x6b, (byte) 0x03, (byte) 0x00, (byte) 0x31, (byte) 0x51, (byte) 0x30};
      commands.put("print", command);
      resultLen += command.length;

      int cnt = 0;
      int commandLen = 0;
      byte[] result = new byte[resultLen];
      for (String currCommand : commandSequence) {
          command = (byte[]) commands.get(currCommand);
          commandLen = command.length;
          System.arraycopy(command, 0, result, cnt, commandLen);
          cnt += commandLen;
      }

      return result;
  }
}