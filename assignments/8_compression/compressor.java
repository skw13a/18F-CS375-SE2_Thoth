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

public class compressor {

  private static final int R = 256;

  private compressor() { } //compressor is a singleton.

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

  /**
   * Reads a sequence of 8-bit bytes from standard input; compresses them
   * using Huffman codes with an 8-bit alphabet; and writes the results
   * to standard output.
   */
  public static void compress() {
      // read the input
      String s = BitInput.readString();
      char[] input = s.toCharArray();

      // tabulate frequency counts
      int[] frequency = new int[R];
      for (int i = 0; i < input.length; i++)
          frequency[input[i]]++;

      // build Huffman trie
      Node root = buildTrie(frequency);

      // build code table
      String[] st = new String[R];
      buildCode(st, root, "");

      // print trie for decoder
      writeTrie(root);

      // print number of bytes in original uncompressed message
      BitOutput.write(input.length);

      // use Huffman code to encode input
      for (int i = 0; i < input.length; i++) {
          String code = st[input[i]];
          for (int j = 0; j < code.length(); j++) {
              if (code.charAt(j) == '0') {
                  BitOutput.write(false);
              }
              else if (code.charAt(j) == '1') {
                  BitOutput.write(true);
              }
              else throw new IllegalStateException("Illegal state");
          }
      }

      // close output stream
      BitOutput.close();
  }

  // build the Huffman trie given frequencies
  private static Node buildTrie(int[] frequency) {

      // initialze priority queue with singleton trees
      MinPQ<Node> pq = new MinPQ<Node>();
      for (char i = 0; i < R; i++)
          if (frequency[i] > 0)
              pq.insert(new Node(i, frequency[i], null, null));

      if (pq.size() == 1) {
          if (frequency['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
          else                 pq.insert(new Node('\1', 0, null, null));
      }

      // merge two smallest trees
      while (pq.size() > 1) {
          Node left  = pq.delMin();
          Node right = pq.delMin();
          Node parent = new Node('\0', left.frequency + right.frequency, left, right);
          pq.insert(parent);
      }
      return pq.delMin();
  }

  private static void writeTrie(Node x) {
      if (x.isLeaf()) {
          BitOutput.write(true);
          BitOutput.write(x.character, 8);
          return;
      }
      BitOutput.write(false);
      writeTrie(x.left);
      writeTrie(x.right);
  }

  private static void buildCode(String[] st, Node x, String s) {
      if (!x.isLeaf()) {
          buildCode(st, x.left,  s + '0');
          buildCode(st, x.right, s + '1');
      }
      else {
          st[x.character] = s;
      }
  }

  public static void main(String[] args) {
      compress();
  }

}
