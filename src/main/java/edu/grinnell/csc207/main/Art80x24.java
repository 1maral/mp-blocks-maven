package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Maral Bat-Erdene
 * @author Jake Bell
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    AsciiBlock example = new Grid(new Line("."),80,24);
    AsciiBlock.print(pen, example);

    // Creating the essential blocks to use
    AsciiBlock black = new Rect('#', 10, 3);
    AsciiBlock skin = new Rect(' ', 10, 3);
    AsciiBlock white = new Boxed(new Rect(' ', 8, 1));
    AsciiBlock nose = new Rect('_', 10, 3);

    // Creating the hair + forehead of the character
    // First Layer
    AsciiBlock hair1 = new Grid(black,8,1);
    // Second Layer
    AsciiBlock hair2 = new HComp(VAlignment.CENTER, new AsciiBlock[] {black, new Grid(skin,6,1), black});
    // Third layer
    AsciiBlock forehead = new Grid(skin,8,1);
    AsciiBlock foreheadFull = new VComp(HAlignment.CENTER, new AsciiBlock[] {hair1, hair2, forehead});

    // Creating the eye layer of the character
    AsciiBlock eye1 = new HComp(VAlignment.CENTER, new AsciiBlock[] {skin, white, black, skin});
    // Flipping the first eye to create second eye
    AsciiBlock eye2 = new HFlip(eye1);
    AsciiBlock eyeFull = new HComp(VAlignment.CENTER, new AsciiBlock[] {eye1, eye2});

    // Creating the nose layer of the character
    AsciiBlock nose1 = new HComp(VAlignment.CENTER, new AsciiBlock[] {new Grid(skin,3,1), nose});
    // Flipping the first nose to create second nose
    AsciiBlock nose2 = new HFlip(nose1);
    AsciiBlock noseFull = new HComp(VAlignment.CENTER, new AsciiBlock[] {nose1, nose2});

    // Creating the mouth layer of the character
    AsciiBlock mouth1 = new HComp(VAlignment.CENTER, new AsciiBlock[] {new Grid(skin,2,1), black, skin});
    // Flipping the first nose to create second nose
    AsciiBlock mouth2 = new HFlip(mouth1);
    AsciiBlock mouth3 = new HComp(VAlignment.CENTER, new AsciiBlock[] {skin, skin, new Grid(black,4,1), skin, skin});
    AsciiBlock mouth4 = new HComp(VAlignment.CENTER, new AsciiBlock[] {mouth1, mouth2});
    AsciiBlock mouthFull = new VComp(HAlignment.CENTER, new AsciiBlock[] {mouth4, mouth3});

    AsciiBlock art = new VComp(HAlignment.CENTER, new AsciiBlock[] {foreheadFull, eyeFull, noseFull, mouthFull, forehead});

    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
