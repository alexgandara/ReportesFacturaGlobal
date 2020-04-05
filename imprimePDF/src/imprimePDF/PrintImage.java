package imprimePDF;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JOptionPane;




	

	
	public class PrintImage {
		
		private final static char ESC_CHAR = 0x1B;
		private final static char GS = 0x1D;
		private final static byte[] LINE_FEED = new byte[]{0x0A};
		private final static byte[] CUT_PAPER = new byte[]{GS, 0x56, 0x00};
		private final static byte[] INIT_PRINTER = new byte[]{ESC_CHAR, 0x40};
		private static byte[] SELECT_BIT_IMAGE_MODE = {0x1B, 0x2A, 33};
		private final static byte[] SET_LINE_SPACE_24 = new byte[]{ESC_CHAR, 0x33, 24};
		
		  static public void main(String args[]) throws Exception {
			
			  
			  File imgPath = new File("c://temp//alex//20505552670-01-F001-00000444.png");
			  BufferedImage bi = ImageIO.read(imgPath);
			  
			  int[][] pixels = getPixelsSlow(bi);
			  
			  

		
				    
		    
		
		    byte[] bytes;
		    byte[] newbyte;
		    
		    bytes = SET_LINE_SPACE_24;
		    
		    for (int y = 0; y < pixels.length; y += 24) {
		    	
		    	
				   // Like I said before, when done sending data, 
				   // the printer will resume to normal text printing
		    		bytes = new byte[bytes.length + SELECT_BIT_IMAGE_MODE.length];
		    		
		    		
				//   printPort.writeBytes(SELECT_BIT_IMAGE_MODE);
		    		
				   // Set nL and nH based on the width of the image
				//   printPort.writeBytes(new byte[]{(byte)(0x00ff & pixels[y].length), (byte)((0xff00 & pixels[y].length) >> 8)});
				   newbyte=new byte[]{(byte)(0x00ff & pixels[y].length), (byte)((0xff00 & pixels[y].length) >> 8)};
				   bytes = new byte[bytes.length + newbyte.length];
				   			
				   
				   for (int x = 0; x < pixels[y].length; x++) {
				    // for each stripe, recollect 3 bytes (3 bytes = 24 bits)
				  //  printPort.writeBytes(recollectSlice(y, x, pixels));
				    
				    newbyte = recollectSlice(y, x, pixels);
				    bytes = new byte[bytes.length + newbyte.length];
				    
				   }

		    
		    



				    
				    
	  }

				  
				  
		    bytes = "hola perrito".getBytes();

		    
			  DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			    
			    
			    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
					 
			    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
				
					    
			    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
					    
				    
		    
    Doc doc = new SimpleDoc(bytes,flavor,null);
    System.out.println("lista para imprimir");
    
    
    //Creamos un trabajo de impresión
	DocPrintJob job = service.createPrintJob();

	try {
	      //El metodo print imprime
	      job.print(doc, null);
		      
		      
	   } catch (Exception er) {
	      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
	  }
		    
		    
			  
			
			  
			  
			  
			  System.out.println("Done...");
			  
		  }
		  
		  
		  
		  private static int[][] getPixelsSlow(BufferedImage image) {
			    int width = image.getWidth();
			    int height = image.getHeight();
			    int[][] result = new int[height][width];
			    for (int row = 0; row < height; row++) {
			        for (int col = 0; col < width; col++) {
			            result[row][col] = image.getRGB(col, row);
			        }
			    }

			    return result;
			}
	
		
		  private static byte[] recollectSlice(int y, int x, int[][] img) {
			    byte[] slices = new byte[] {0, 0, 0};
			    for (int yy = y, i = 0; yy < y + 24 && i < 3; yy += 8, i++) {
			        byte slice = 0;
			        for (int b = 0; b < 8; b++) {
			            int yyy = yy + b;
			            if (yyy >= img.length) {
			            	continue;
			            }
			     int col = img[yyy][x]; 
			     boolean v = shouldPrintColor(col);
			     slice |= (byte) ((v ? 1 : 0) << (7 - b));
			 }
			        slices[i] = slice;
			    }
			 
			    return slices;
			}
		  
		  
		  
		  private static boolean shouldPrintColor(int col) {
			    final int threshold = 127;
			    int a, r, g, b, luminance;
			    a = (col >> 24) & 0xff;
			    if (a != 0xff) {// Ignore transparencies
			        return false;
			    }
			    r = (col >> 16) & 0xff;
			    g = (col >> 8) & 0xff;
			    b = col & 0xff;

			    luminance = (int) (0.299 * r + 0.587 * g + 0.114 * b);

			    return luminance < threshold;
			}


}



