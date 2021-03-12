
import java.io.*;
import java.util.*;

import edu.stanford.nlp.coref.CorefCoreAnnotations;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

// Sentiment Analyser Object creates one CoreNLP pipeline, and then has one method to take in textual feedback and return a value for sentiment
public class SentimentAnalyser {

  public Properties props = new Properties();     // sentiment analyser object has properties
  public StanfordCoreNLP pipeline;                // and a pipeline used for annotations

  public SentimentAnalyser(){
    props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");   // properties needed to provide sentiment analysis
    pipeline = new StanfordCoreNLP(props);                                        // creates pipeline object used for analysis
  }
    // takes in text, gives an integer value (0-4) representing sentiment for each sentence, and then returns the average
  public float Analyse(String text){
    float totalSentiment = 0;
    float sum = 0;
    Annotation annotation = pipeline.process(line);
    for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
      Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
      totalSentiment += RNNCoreAnnotations.getPredictedClass(tree);
      sum += 1;
    }
    if (sum == 0){
      return 2.0;
    }
    return (totalSentiment/sum);
  }

    
}