import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class decompressor {

  private static final int R = 256;

  private decompressor() { }

  private static class Node implements Comparable<Node> {
      private final char character;
      private final int frequency;
      private final Node left, right;

      Node(char ch, int freq, Node left, Node right) {
          this.character  = ch;
          this.frequency  = freq;
          this.left       = left;
          this.right      = right;
      }

      private boolean isLeaf() {
          assert ((left == null) && (right == null)) || ((left != null) && (right != null));
          return (left == null) && (right == null);
      }

      public int compareTo(Node that) {
          return this.frequency - that.frequency;
      }
  }


  public static void expand() {

      Node root = readTrie();

      int length = BitInput.readInt();

      for (int i = 0; i < length; i++) {
          Node x = root;
          while (!x.isLeaf()) {
              boolean bit = BitInput.readBoolean();
              if (bit) x = x.right;
              else     x = x.left;
          }
          BitOutput.write(x.character, 8);
      }
      BitOutput.close();
  }


  private static Node readTrie() {
      boolean isLeaf = BitInput.readBoolean();
      if (isLeaf) {
          return new Node(BitInput.readChar(), -1, null, null);
      }
      else {
          return new Node('\0', -1, readTrie(), readTrie());
      }
  }

  public static void main(String[] args) {
      expand();
  }

}
