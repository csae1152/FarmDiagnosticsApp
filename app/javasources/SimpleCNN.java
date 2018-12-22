package javasources;


import org.apache.commons.io.FilenameUtils;




/**
 * Convolutional Neural Networks for Sentence Classification - https://arxiv.org/abs/1408.5882
 *
 * Specifically, this is the 'static' model from there
 *
 * @author Alex Black
 */
public class SimpleCNN {

  /** Data URL for downloading */
  public static final String DATA_URL = "http://ai.stanford.edu/~amaas/data/sentiment/aclImdb_v1.tar.gz";
  /** Location to save and extract the training/testing data */
  public static final String DATA_PATH = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), "dl4j_w2vSentiment/");
  /** Location (local file system) for the Google News vectors. Set this manually. */
  public static final String WORD_VECTORS_PATH = "/PATH/TO/YOUR/VECTORS/GoogleNews-vectors-negative300.bin.gz";

  public static void main(String[] args) throws Exception {
    if (WORD_VECTORS_PATH.startsWith("/PATH/TO/YOUR/VECTORS/")) {
      throw new RuntimeException("Please set the WORD_VECTORS_PATH before running this example");
    }

  }
}
